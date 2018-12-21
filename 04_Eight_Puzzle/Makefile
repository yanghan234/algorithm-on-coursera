JC = javac
JFLAGS = -cp .:../algs4.jar
JVM = java

all: Board.class Solver.class

Board.class : Board.java
	$(JC) $(JFLAGS) $^

Solver.class : Board.java Solver.java
	$(JC) $(JFLAGS) $^

run:
	#$(JVM) $(JFLAGS) Board
	$(JVM) $(JFLAGS) Solver

clean:
	rm *.class

