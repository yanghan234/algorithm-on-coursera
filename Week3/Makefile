CC=icc
CFLAGS = -I .

all: select.x insert.x

select.x : Selection_Sort.cpp Sort.cpp
	$(CC) $(CFLAGS) $^ -o $@

insert.x : Insertion_Sort.cpp Sort.cpp
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm *.x *.csv
