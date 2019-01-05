JC = javac
JVM = java
JFLAGS = -cp .:../algs4.jar
SRC = $(wildcard *.java)
CLASS = $(SRC:.java=.class)

all : $(CLASS)

%.class : %.java
	$(JC) $(JFLAGS) $^

run :
	$(JVM) $(JFLAGS) DFSPath
	$(JVM) $(JFLAGS) BFSPath
	$(JVM) $(JFLAGS) ConnectedComponent

clean :
	rm *.class
