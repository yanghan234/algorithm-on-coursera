# Algorithms
This repository follows Princeton's class, Algorithms, part I and part II on coursera.

## Chapter I: Fundamentals
This part introduced two basic data structures, queues and stacks. Detailed description of the two data structures and their implementations can be found [here](https://yanghan234.github.io/2019/01/05/2018_01_05_Queue_and_Stack/).
- [x] Queue
- [x] Stack

### A case study: Union-Find
* Problem description:

    Given a set of numbers, implement at least two features: union() and find(). Union() method connects two numbers in the set while find() tells if two numbers in the set are connected or not.

- [x] Quick find algorithm
- [x] Quick union algorithm
- [x] Weighted quick union algorithm
- [x] Weighted quick union algorithm with path compression
The codes can be found [here](https://github.com/yanghan234/algorithm-on-coursera/tree/master/Week1_1).
- [ ] Detailed description of the algorithms needs to be added. Here I only summarize the complexity of the algorithms.

| Algorithm  | Constructor  | union() | find() |
|:---:|:---:|:---:|:---:|
| quick find | *O*(N) | *O*(N) | *O*(1) |
| union find | *O*(N)  | tree height  | tree height  |
| Weighted quick union  | *O*(N)  | *O*(lgN)  | *O*(lgN)  |
| Weighted quick union with path compression   | *O*(N)  | ~*O*(1)  | ~*O*(1)  |

## Chapter II: Sorting Algorithms
* Elementary sorting algorithms:

    - [ ] Bubble sort
    - [x] Selection sort
    - [x] Insertion sort
    - [x] Shell sort
* Advanced sorting algorithms:

    - [x] Merge sort
    - [x] Quick sort
    - [x] Heapsort ( Introduced later in chapter III )

You might want to read my article on these methods here: [Sorting Algorithms](https://yanghan234.github.io/2018/12/21/Sorting-Algorithms/).
## Chapter III: Searching Algorithms
- [x] Binary tree
- [ ] 2-3-4 tree
- [x] Red-black tree
- [x] Hash table
    - [x] Chaining
    - [x] Linear probing

## Chapter IV: Graph Algorithms
1. Graph representation
    - [ ] Sparse matrix
    - [x] Adjacency list
2. Undirected graph
    - [x] Depth-first search (DFS)
    - [x] Broadth-first search (BFS)
    - [x] Applications:
        - [x] Connected components
3. Directed graph (aka Digraph)
    - [x] BFS & DFS
    - [x] Applications:
        - [x] Topological graph
        - [x] Strong connected components
        - [x] DAG & How to check DAG
4. Edge Weighted Graphs & Minimum spanning tree
    - [x] Kruskal algorithm
    - [x] Prim algorithm
        - [x] Lazy implementation
        - [x] Eager implementation
5. Edge Weighted Digraph & Shortest Path
    - [x] Dijkstra algorithm
    - [ ] SP in positive-weight DAG
    - [ ] Bellman-Ford algorithm
        - [ ] Negative cycle & how to check negative cycle
    - [ ] Longest path

6. String Algorithms
    - [ ] To be added
