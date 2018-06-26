Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: modified build file to compile when run command is executed

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=FIRST -Darg1=SECOND -Darg2=THIRD -Darg3=FOURTH -Darg4=FIFTH
eg: ant -buildfile src/build.xml run -Darg0=/import/linux/home1/hloya1/input_file/input.txt -Darg1=/import/linux/home1/hloya1/output_file/output.txt -Darg2=2 -Darg3="H H" -Darg4=0

#Note: 
1. Make sure input_file.txt is present at appropriate location
2. Output file location doesn't matter but its name should be "output.txt"
-----------------------------------------------------------------------


-> Hashmap best and average case for Search, Insert and Delete is O(1) and worst case is O(n). So, HashMap is used in the Results class so that searching could be done faster.

DEBUG_VALUE=2
-> Print to stdout the line and thread name reading that line everytime a line is read from input file.

DEBUG_VALUE=1
-> Print to stdout the word and its new count everytime a word is successfully deleted.
-----------------------------------------------------------------------
