JC = javac
JVM = java
JFLAGS = -cp .:../../algs4.jar
SRC = $(wildcard *.java)
CLASS = $(SRC:.java=.class)

all : $(CLASS)

%.class : %.java
	$(JC) $(JFLAGS) $^

run :
	#$(JVM) $(JFLAGS) Digraph input.txt
	#$(JVM) $(JFLAGS) DFSPath input.txt
	#$(JVM) $(JFLAGS) BFSPath input.txt
	#$(JVM) $(JFLAGS) CheckDAG input.txt
	#$(JVM) $(JFLAGS) TopologicalSort input2.txt
	$(JVM) $(JFLAGS) StrongComponent input2.txt

clean :
	rm *.class
