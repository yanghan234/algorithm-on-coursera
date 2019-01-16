JC = javac
JVM = java
JFLAGS = -cp .:../../algs4.jar
SRC = $(wildcard *.java) ../MST/MinPQ.java
CLASS = $(SRC:.java=.class)

all : $(CLASS)

%.class : %.java
	$(JC) $(JFLAGS) $^

run :
	$(JVM) $(JFLAGS) LazyBellmanFordSP

clean :
	rm *.class
