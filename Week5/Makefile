JC = javac
JVM = java
JFLAGS = -cp .:../algs4.jar
CLASSES = UnorderedLinkedListSymbolTable.class \
		  OrderedArraySymbolTable.class \
		  BinarySearchTree.class \
		  RedBlackTree.class


all : $(CLASSES)

UnorderedLinkedListSymbolTable.class : UnorderedLinkedListSymbolTable.java
	$(JC) $(JFLAGS) $^

OrderedArraySymbolTable.class : OrderedArraySymbolTable.java
	$(JC) $(JFLAGS) $^

BinarySearchTree.class : BinarySearchTree.java
	$(JC) $(JFLAGS) $^

RedBlackTree.class : RedBlackTree.java
	$(JC) $(JFLAGS) $^

run :
	$(JVM) $(JFLAGS) RedBlackTree

clean :
	rm *.class
