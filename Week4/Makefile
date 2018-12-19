CC=icc
CFLAGS = -I .
JC=javac
JFLAGS = -cp .:../algs4
JVM = java

all : UnorderedMaxPQ.x OrderedMaxPQ.x Heap.x Heapsort.x UnorderedMaxPQ.class OrderedMaxPQ.class Heap.class Heapsort.class

UnorderedMaxPQ.x : UnorderedMaxPQ.cpp PQ.cpp
	$(CC) $(CFLAGS) $^ -o $@

OrderedMaxPQ.x : OrderedMaxPQ.cpp PQ.cpp
	$(CC) $(CFLAGS) $^ -o $@

Heap.x : Heap_test.cpp Heap.cpp PQ.cpp
	$(CC) $(CFLAGS) $^ -o $@

Heapsort.x : Heapsort.cpp Heap.cpp PQ.cpp
	$(CC) $(CFLAGS) $^ -o $@

UnorderedMaxPQ.class : UnorderedMaxPQ.java PQ.java
	$(JC) $(JFLAGS) $^

OrderedMaxPQ.class : OrderedMaxPQ.java PQ.java
	$(JC) $(JFLAGS) $^

Heap.class: Heap.java PQ.java
	$(JC) $(JFLAGS) $^

Heapsort.class : Heapsort.java Heap.java PQ.java
	$(JC) $(JFLAGS) $^

run:
	#$(JVM) $(JFLAGS) OrderedMaxPQ
	#$(JVM) $(JFLAGS) UnorderedMaxPQ
	#$(JVM) $(JFLAGS) Heap
	$(JVM) $(JFLAGS) Heapsort

clean:
	rm *.x *.class
