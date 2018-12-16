CC=icc
CFLAGS = -I .

all : UnorderedMaxPQ.x OrderedMaxPQ.x Heap.x

UnorderedMaxPQ.x : UnorderedMaxPQ.cpp PQ.cpp
	$(CC) $(CFLAGS) $^ -o $@

OrderedMaxPQ.x : OrderedMaxPQ.cpp PQ.cpp
	$(CC) $(CFLAGS) $^ -o $@

Heap.x : Heap.cpp PQ.cpp
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm *.x
