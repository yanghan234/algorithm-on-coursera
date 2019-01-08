JC = javac
JCFLAGS = -cp .:../algs4.jar
JVM = java
SRC = $(wildcard *.java)
CLASSES = $(SRC:.java=.class)

all: $(CLASSES)

%.class: %.java
	$(JC) $(JCFLAGS) $^

run:
	$(JVM) $(JCFLAGS) WordNet

clean:
	rm -rf *.class
