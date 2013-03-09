Evernote-NoteLister
===================

**Show untagged notes that should be tagged, or that ended up in the wrong notebook.**

This is a small project to teach myself the Evernote API, Maven stuff, Java command-line-options processing, Eclipse-Maven interactions, and others.

The command line application lists the titles of all notes in a Notebook, and how many tags there are. 
Read-Only access is required, no Read-write necessary.
Therefore, it suffices to get a developer token for the *Evernote Sandbox API*. 


If you want to clone this script and use it, then, obviously, 
it is required that you need to have an acount with Evernote, and get a token for *your* account.



Check out the (Eclipse) project, run the jar file inside the "target" directory, or build it from source and then run it as a java application.
There is only a couple of files that matter, in the knbknb namespace. All other .java files are dependencies, most of them from the Evernote SDK.


**Command line call**

    -n: Notebookname
    -t: Your Token (a long password)

    java -jar .\target\Evernote-Notelister-0.0.1-RELEASE.jar -v -t S-devtoken:H=d3abf... -n Ideen

**Sample Output**
    
    Notebook: Nicht Getagged
     --skipped!
    
    Notebook: Unwichtig-oder-kann-weg
     --skipped!
    
    Notebook: Confirm-and-Config
     --skipped!
    
    Notebook: Getagged
     --skipped!
    
    Notebook: Ideen
     ---- contains notes without any tags:
     * Bruce Eckel TIJ 1st ed: Java operators code als Junit tests
     * Gute Vorsõtze f³r 2013
     * interessante Kaffeebars berlin, mal besuchen
     * Abstract EGU 2013 - Ideen
