JC = javac
JVM = java
JFLAGS = -cp .:../algs4.jar
CLASSES = UnorderedLinkedListSymbolTable.class \
		  OrderedArraySymbolTable.class \
		  BinarySearchTree.class


all : $(CLASSES)

UnorderedLinkedListSymbolTable.class : UnorderedLinkedListSymbolTable.java
	$(JC) $(JFLAGS) $^

OrderedArraySymbolTable.class : OrderedArraySymbolTable.java
	$(JC) $(JFLAGS) $^

BinarySearchTree.class : BinarySearchTree.java
	$(JC) $(JFLAGS) $^

run :
	$(JVM) $(JFLAGS) BinarySearchTree

clean :
	rm *.class
