@tp: ThreadPool
--------------------------
<HashFunction: 2000>
non-tp: doc1 and doc2
12300 milliseconds
14269 milliseconds
15776 milliseconds
13204 milliseconds
12665 milliseconds

tp(10) = doc1 and doc2
11190 milliseconds
11294 milliseconds
11568 milliseconds
12543 milliseconds
11212 milliseconds

tp(100) = doc1 and doc2
8559 milliseconds
9014 milliseconds
8600 milliseconds
8764 milliseconds
8312 milliseconds

tp(1000) = doc1 and doc2
9416 milliseconds
8843 milliseconds
8904 milliseconds
8886 milliseconds
8802 milliseconds
--------------------------
MinHash Processing Done: 4226 milliseconds
Size of HashFunctions: 1000
Similarity: 0.006625891946992864

MinHash Processing Done: 7778 milliseconds
Size of HashFunctions: 2000
Similarity: 0.01485148514851485 (1.48%)

MinHash Processing Done: 39399 milliseconds
Size of HashFunctions: 10000
Similarity: 0.07611804076665653 (7.61%)

MinHash Processing Done: 77181 milliseconds
Size of HashFunctions: 20000
Similarity: 0.13316822925880065 (13.31%)
---------------------------------------------
/*
* Higher ThreadPool numbers, higher CPU usage.
* It shows processing time decreases, 
* however, after a certain numbers of ThreadPool,
* there is no significant differences appeared.
* Higher the number of HashFunctions,
* results show the higher accuracy and exponential time growth
*/