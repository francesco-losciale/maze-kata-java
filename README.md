# Instruction 

Executable JAR is available here: 

https://github.com/francesco-losciale/maze-kata-java/tree/master/classes/artifacts/maze_technical_test_jar

While input file examples are here:

https://github.com/francesco-losciale/maze-kata-java/tree/master/src/test/resources

Once downloaded the jar file, simply run this command: 

java -jar maze-technical-test.jar /home/osboxes/IdeaProjects/maze-kata-java/src/test/resources/small_input.txt

It will print on the console the maze, if you want to view the output in a file simply add " > output.txt" to the command above.


# Considerations

- Used a bottom up TDD approach, developing the single components first and at last the Acceptance Criteria test. 
- Second guessing an Outside-in approach would have been better, in one occation I spent time debugging for the special case of a rectangular grid (which I missed to consider in the GridTest class). This skipped test case would have been visible if I had started from the Acceptance Test. 
- The testsuite should be self explanatory, without the need of any comment. If you want to understand Grid behavious, should be sufficient to read GridTest. All the unit test are specific for a component, while MazeAcceptanceTest represent the "integration test" 
