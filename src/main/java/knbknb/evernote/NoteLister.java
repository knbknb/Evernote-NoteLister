/*
  Evernote API sample code, structured as a simple command line
  application that demonstrates several API calls.
  
  To compile (Unix):
    javac -classpath .:../../lib/libthrift.jar:../../lib/evernote-api-*.jar EDAMDemo.java
 
  To run:
     java -classpath .:../../lib/libthrift.jar:../../lib/evernote-api-*.jar EDAMDemo
 
  Full documentation of the Evernote API can be found at 
  http://dev.evernote.com/documentation/cloud/
 */
package knbknb.evernote;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import knbknb.helpformatter.SimpleHelpParserFactory;

//import org.apache.thrift.protocol.TBinaryProtocol;
//import org.apache.thrift.transport.THttpClient;
//import org.apache.thrift.transport.TTransportException;
//import com.evernote.thrift.*;
import com.evernote.thrift.protocol.*;
import com.evernote.thrift.transport.*;

import com.evernote.edam.type.*;
import com.evernote.edam.userstore.*;
import com.evernote.edam.error.*;
import com.evernote.edam.userstore.Constants;
import com.evernote.edam.notestore.*;

public class NoteLister {

	/***************************************************************************
	 * This code based on sample code from Evernote.com *
	 ***************************************************************************/

	// Real applications authenticate with Evernote using OAuth, but for the
	// purpose of exploring the API, you can get a developer token that allows
	// you to access your own Evernote account. To get a developer token, visit
	// https://sandbox.evernote.com/api/DeveloperToken.action
	private static String authToken;
	private static String notebook;
	// "S=s110:U=b40910:E=1434f65dc43:C=13bf7b4b043:P=1cd:A=en-devtoken:H=someMD5?Value";

	// Initial development is performed on our sandbox server. To use the
	// production
	// service, change "sandbox.evernote.com" to "www.evernote.com" and replace
	// your
	// developer token above with a token from
	// https://www.evernote.com/api/DeveloperToken.action
	private static final String evernoteHost = "www.evernote.com";
	private static final String userStoreUrl = "https://" + evernoteHost
			+ "/edam/user";

	// In a real application, you would change the User Agent to a string that
	// describes
	// your application, using the form company name/app name and version. Using
	// a unique
	// user agent string helps us provide you with better support.
	private static final String userAgent = "knbknb/EvernoteNoteLister (Java) "
			+ Constants.EDAM_VERSION_MAJOR + "." + Constants.EDAM_VERSION_MINOR;

	private NoteStore.Client noteStore;
	private int cnt;
	private String[] args;

	/**
	 * Console entry point.
	 */
	public static void main(String args[]) throws Exception {
		if ("your developer token".equals(authToken)) {
			System.err.println("Please fill in your developer token");
			System.err
					.println("To get a developer token, go to https://sandbox.evernote.com/api/DeveloperToken.action");
			return;
		}
		

		NoteLister demo = new NoteLister();
		if (demo.initialize(args)) {
			try {
				demo.listNotes();
				// demo.createNote();
				// demo.searchNotes();
				// demo.updateNoteTag();
			} catch (EDAMUserException e) {
				// These are the most common error types that you'll need to
				// handle
				// EDAMUserException is thrown when an API call fails because a
				// paramter was invalid.
				if (e.getErrorCode() == EDAMErrorCode.AUTH_EXPIRED) {
					System.err.println("Your authentication token is expired!");
				} else if (e.getErrorCode() == EDAMErrorCode.INVALID_AUTH) {
					System.err.println("Your authentication token is invalid!");
				} else if (e.getErrorCode() == EDAMErrorCode.QUOTA_REACHED) {
					System.err.println("Your authentication token is invalid!");
				} else {
					System.err.println("Error: " + e.getErrorCode().toString()
							+ " parameter: " + e.getParameter());
				}
			} catch (EDAMSystemException e) {
				System.err.println("System error: "
						+ e.getErrorCode().toString());
			} catch (TTransportException t) {
				System.err.println("Networking error: " + t.getMessage());
			}
		}
	}

	/**
	 * Intialize UserStore and NoteStore clients. During this step, we
	 * authenticate with the Evernote web service. All of this code is
	 * boilerplate - you can copy it straight into your application.
	 */
	private boolean initialize(String[] args) throws Exception {
		// Set up the UserStore client and check that we can speak to the server
		THttpClient userStoreTrans = new THttpClient(userStoreUrl);
		userStoreTrans.setCustomHeader("User-Agent", userAgent);
		TBinaryProtocol userStoreProt = new TBinaryProtocol(userStoreTrans);
		UserStore.Client userStore = new UserStore.Client(userStoreProt,
				userStoreProt);

		boolean versionOk = userStore.checkVersion("Evernote EDAMDemo (Java)",
				com.evernote.edam.userstore.Constants.EDAM_VERSION_MAJOR,
				com.evernote.edam.userstore.Constants.EDAM_VERSION_MINOR);
		if (!versionOk) {
			System.err.println("Incomatible Evernote client protocol version");
			return false;
		}

		OptionParser parser = SimpleHelpParserFactory.createParser(null);
		OptionSet options = parser.parse(args);
		String notestoreUrl = "";

		if (options.has("v") == true) {
			System.out.println("Class Path = "
					+ System.getProperty("java.class.path"));
			System.out.println("Working Directory = "
					+ System.getProperty("user.dir"));
		}
		
		if (options.has("t") == true && options.has("f") == true) {
			System.out
					.println("Option t AND Option f have been specified - will ignore -f (File option) ");
		}

		if (options.has("t") == true) {
			System.out.println("Option t = " + options.valueOf("t"));
			try {
				authToken = (String) options.valueOf("t");
				notestoreUrl = userStore.getNoteStoreUrl(authToken.trim());
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		} else if (options.has("f") == true) {
			System.out.println("Option f = " + options.valueOf("f"));
			String strTokenInFilename = (String) options.valueOf("f");

			try {
				authToken = readFileAsString(strTokenInFilename);
				notestoreUrl = userStore.getNoteStoreUrl(authToken);
			} catch (IOException e1) {

			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}

		}

		setNotebook((String) options.valueOf("n"));

		// user didn't specify anything. User token from default. token.txt
		// user tried to specify a token's filename.
		// user tried to specify a token as-is. use that string to authenticate

		// Get the URL used to interact with the contents of the user's account
		// When your application authenticates using OAuth, the NoteStore URL
		// will
		// be returned along with the auth token in the final OAuth request.
		// In that case, you don't need to make this call.

		// Set up the NoteStore client
		THttpClient noteStoreTrans = new THttpClient(notestoreUrl);
		noteStoreTrans.setCustomHeader("User-Agent", userAgent);
		TBinaryProtocol noteStoreProt = new TBinaryProtocol(noteStoreTrans);
		noteStore = new NoteStore.Client(noteStoreProt, noteStoreProt);

		return true;
	}

	/**
	 * Retrieve and display a list of the user's notes.
	 */
	private void listNotes() throws Exception {
		// List the notes in the user's account
		System.out.println("Listing notes:");

		// First, get a list of all notebooks
		List<Notebook> notebooks = noteStore.listNotebooks(authToken);

		System.out.println(" ---- Printing Notebooks ");

		for (Notebook nbook : notebooks) {
			System.out.println(" ");

			System.out.println("Notebook: " + nbook.getName());
			if (!nbook.getName().equalsIgnoreCase(notebook)) {
				System.out.println(" --skipped! ");
				continue;
			} else {
				// System.out.println(" ---- contains notes already tagged although they shouldn't be:");
				System.out.println(" ---- contains notes without any tags:");
			}

			// Next, search for the first 100 notes in this notebook, ordering
			// by creation date
			NoteFilter filter = new NoteFilter();
			filter.setNotebookGuid(nbook.getGuid());
			filter.setOrder(NoteSortOrder.CREATED.getValue());
			// filter.setOrder(NoteSortOrder.TITLE.getValue());

			filter.setAscending(false);

			NoteList noteList = noteStore.findNotes(authToken, filter, 0, 1000);
			List<Note> notes = noteList.getNotes();
//			for (Note note : notes) {
//				if (note.getTagGuidsSize() > 0) {
//					System.out.println(" * " + note.getTitle());
//				}
//			}

			this.cnt = 0;
			NoteCollectionCounts nbcnt = noteStore.findNoteCounts(authToken,
					filter, false);
			Map<String, Integer> m = nbcnt.getNotebookCounts();
			int noteCount = m.get(nbook.getGuid());
			System.out.println(" -- " + noteCount + " -- ");
			SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm", Locale.GERMANY);
			
			for (int i = 0; i < noteCount; i += 50) {

				noteList = noteStore.findNotes(authToken, filter, i, i + 50);
				notes = noteList.getNotes();
				System.out.println(nbook.getName() + " - Notes with tagcount == 0");
				for (Note note : notes) {
					this.cnt++;
					int tgcnt = note.getTagGuidsSize();
					Date crd = new Date(note.getCreated());
					if (tgcnt == 0) {
						
						System.out.println("#" + cnt + ":  " + ISO8601DATEFORMAT.format(crd) + "   " + note.getTitle()
								+ " - tags: " + tgcnt);
					}
		            
				}
				System.out.println("");
				System.out.println(nbook.getName() + " - Notes with tagcount > 0");
				for (Note note : notes) {
					this.cnt++;
					int tgcnt = note.getTagGuidsSize();
					Date crd = new Date(note.getCreated());
					if (tgcnt != 0) {
						
						System.out.println(" " + cnt + ":  " + ISO8601DATEFORMAT.format(crd) + "   " + note.getTitle()
								+ " - tags: " + tgcnt);
					}
		            
				}
			}

		}
		System.out.println();

	}

	private static String readFileAsString(String filename) throws Exception {
		File file = new File(filename);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String text = null;
		String token = "";

		while ((text = reader.readLine()) != null) {
			if (!text.startsWith("#")) {
				token += text.trim();
			}

		}
		return token;
	}


	public static String getNotebook() {
		return notebook;
	}

	public static void setNotebook(String notebook) {
		NoteLister.notebook = notebook;
	}

}
