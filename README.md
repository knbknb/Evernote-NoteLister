Evernote-NoteLister
===================

This is a small project to teach myself the Evernote API, Maven stuff, Java command-line-options processing, Eclipse-Maven interactions, and others.

The command line application lists the titles of all notes in a Notebook, and how much tags there are. There is only Read-Only access. 
Therefore, it suffices to get a developer token from the Sandbox API.



Check out the (Eclipse) project, run it as a jar file, or build it from source run it as a java application

**Command line call**

    java -jar .\target\Evernote-Notelister-0.0.1-RELEASE.jar -v -t S-devtoken:H=d3abf -n Ideen

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
