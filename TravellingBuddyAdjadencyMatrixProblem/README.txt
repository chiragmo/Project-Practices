-----------------------------------------------------------------------
-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on the project.
#### Note: build.xml is present in travellingBuddy/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile travellingBuddy/src/build.xml clean

Description: It cleans up all the .class files that were generated from previous complilation

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile travellingBuddy/src/build.xml all

Description: Compiles the code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile travellingBuddy/src/build.xml run -Darg0=input.txt

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Problem:

input list-> 
alisa<\t>larry<\t>binghamton,brooklyn,albany<\n>
tom<\t>moore<\t>binghamton,manhanten,jersey city<\n>
titan<\t>noore<\t>binghamton,albany<\n>

(A) Find all the matched up groups of travelling buddies?
---- Source-to-destination must be same (OR) destination1 and destination2 must be < 15 miles
1.1 Assumptions:
 alisa binghamton-to-brooklyn
   tom binghamton-to-manhantan
 Since manhantan and brooklyn are < 15mi, alisa and tom will go to brooklyn first and then alisa will go to manhantan. Travel buddies: tom and alisa for binghamton-to-brooklyn 

(B) What cities the buddies are travelling together?
1.2 Assumptions:
As per (1.1) scenario; Travelling together cities will be represented as: 
[alisa and tom] binghamton-to-brooklyn ; because alisa will be travelling with tom until brooklyn and then take the < 15 mile city journey from brooklyn-to-manhantten

(C) Distance buddy group travelled together?
1.3 Assumptions:
Since each group [alisa and tom] OR [alisa and XYZ and ABC] have travelled together differently,
results is represented as:  

[BUDDY_GROUP] : [sourceX->destY,sourceN->destN]  [DISTANCE_BY_THIS_GROUP]
[alisa, tom] : binghamton->brooklyn  TotalDistance: 174
[alisa, XYZ, ABC] : someCity1->someCity2, someCity4->someCity8, someCity8,someCity10  TotalDistance: 850

1.4 Other Assumptions:
* In input list of cities , a pair can be travelled anytime
* The time is not considered
* Every traveller does not have the first city in their respective list as the starting point

-----------------------------------------------------------------------

## Algorithm:

The graph is taken as the nodal way of solving the problem. Adjadency matrix is used, although the most optimum would be using Ajadency list but faster query was choosen with the first option.

The cities are choosen as the vertex and two matrix is created with distance(after filtering only) as the edges in the first one and using the edge for storing the buddypairs in the second one.

Time completity: O(V^2) ; V -> number of cities

-----------------------------------------------------------------------