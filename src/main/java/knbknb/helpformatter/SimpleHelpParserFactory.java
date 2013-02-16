package knbknb.helpformatter;

//see http://pholser.github.com/jopt-simple/examples.html

import static java.util.Arrays.*;

import joptsimple.OptionParser;

public class SimpleHelpParserFactory {
	public static OptionParser createParser(String[] args) throws Exception {
		OptionParser parser = new OptionParser() {
			{
				String defaultFilename = "token.txt";
				String tok = "S=s110:U=b400:E=1434f:C=13bf:P=1cd:A=en-devtoken:H=someMD5-Value";

				String descr1 = "Get it from 'http://dev.evernote.com/start/core/authentication.php#devtoken'. "
						+ "If you do not specify a token, will try to read from text file with that name, and then as '"
						+ defaultFilename + "'.";
				acceptsAll(asList("t", "token"), descr1).withOptionalArg()
						.ofType(String.class)
						.describedAs("Evernote Developer token")
						.defaultsTo(tok);
				acceptsAll(asList("f", "filename"),
						"Config filename containing token string")
						.withOptionalArg().ofType(String.class)
						.describedAs("One-Line-File")
						.defaultsTo(defaultFilename);

				accepts("n").withOptionalArg().ofType(String.class)
						.describedAs("Evernote Notebook name")
						.defaultsTo("Nicht getagged");
				;
				acceptsAll(asList("h", "?"), "show help").forHelp();
				acceptsAll(asList("v", "verbose"),
						"be more verbose (show some System Properties)")
						.forHelp();

			}
		};

		// parser.formatHelpWith( new KeyValuePairFormatter() );
		parser.printHelpOn(System.out);
		return parser;
	}
}