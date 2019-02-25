JC = javac
JVM = java
JFLAGS = -cp .:../algs4.jar
SRC = $(wildcard *.java)
CLASS = $(SRC:.java=.class)

all : $(CLASS)

%.class : %.java
	$(JC) $(JFLAGS) $^

run :
	$(JVM) $(JFLAGS) MoveToFront - < test/abra.txt | $(JVM) $(JFLAGS) MoveToFront +
	$(JVM) $(JFLAGS) Test
	$(JVM) $(JFLAGS) BurrowsWheeler - < test/abra.txt
	$(JVM) $(JFLAGS) BurrowsWheeler - < test/abra.txt | java -cp .:../algs4.jar edu.princeton.cs.algs4.HexDump 16
	$(JVM) $(JFLAGS) BurrowsWheeler - < test/abra.txt | $(JVM) $(JFLAGS) BurrowsWheeler +

clean :
	rm *.class
