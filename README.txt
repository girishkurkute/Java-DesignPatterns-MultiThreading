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

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.”

[Date: 11/03/2017]
I have done this assignment with a teammate - Girish Kurkute as the specification was to work in teams of two.
-----------------------------------------------------------------------
Justify your choice of tree in the README.txt file in terms of time complexity for each of the three operations, and for the best, worst, and average case.
-> I chose RED-BLACK Tree because it being a self-balancing bst tree it has a time complexity of O(log n) for all three operations(insert, delete and search) in all, the best, worst, and average cases.
It is particularly helpful in case of frequent inserts.

The choice of data structure used in the Results class should also be justified in the README.txt in terms of time complexity (average, best, worst case).
-> Hashmap best and average case for Search, Insert and Delete is O(1) and worst case is O(n). So, HashMap is used in the Results class so that searching could be done faster.

DEBUG_VALUE=2
-> Print to stdout the line and thread name reading that line everytime a line is read from input file.

DEBUG_VALUE=1
-> Print to stdout the word and its new count everytime a word is successfully deleted.
-----------------------------------------------------------------------