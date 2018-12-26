JC = javac
JVM = java
JFLAGS = -cp .:../algs4.jar
SRCS = $(wildcard *.java)
CLASS = $(SRCS:.java=.class)

all : $(CLASS)

%.class : %.java
	$(JC) $(JFLAGS) $^

run :
	$(JVM) $(JFLAGS) SeparateChainingHashST
