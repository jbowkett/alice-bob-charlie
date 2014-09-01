Alice Bob Charlie
=================

Coding assignment submission by James Bowkett `james@bowkett.info`.

Built using Java 8 and Gradle.

Clone this repo, then run:

`% gradle run`

This will then drop into an interactive shell for the Alice, Bob & Charlie 
coding assignment.  However, Gradle does not play nicely with the System.in 
stream.  Therefore, the best way to run the code is:

`$ gradle clean build`
`$ java -jar build/libs/alice-bob-charlie-1.0.jar`

The main source is in `src/main/java`, the Junit tests in `src/test/java`.  
There are also cucumber scenarios in `test/features`.

The main class is `info.bowkett.abc.Main`.

Outside of the commands specified in the assignment, The shell will also respond 
to `quit` to quit from the shell.

If you have any problems running the code and/or the tests then please do not 
hesitate to get in touch.
