JC = javac
JVM = java
JFLAGS = -cp .:../algs4.jar
SRCS = $(wildcard *.java)
CLASSES = $(SRCS:.java=.class)


all : $(CLASSES)

%.class : %.java
	$(JC)  $(JFLAGS) $^

run :
	$(JVM) $(JFLAGS) RedBlackTree

clean :
	rm *.class
