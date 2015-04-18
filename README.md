# Text-Search 

	This project takes a .txt file and
		1. Identifies unique words within the file, ignoring words interrupted by punctuation.
		2. Stores those unique words and how many times they occur in a Trie data structure.
	The user can then
		1. Search the text file for a word. The number of times it occurs is returned.
		2. Get a full list of unique words and occurrences.
		3. Compare the loaded text file with another text file. This will print the number of times each word in the first text file occurs in the second.

	This project is written in Java and implements a Trie data structure, as well as various methods on that structure including
		1. A convertToTrie method, which takes a text file name and creates a Trie structure using the unique words inside the text.
		2. An addWord method, which adds a given String to a Trie recursively.
		3. A cleanWord method, which eliminates punctuation in a word, unless the word is of form [any # of letters][any # of punctuation characters][any # of letters], in which case the word is ignored. Uses regular expressions.
		4. A search method, which searchs the Trie for a given String and returns how many times it occurs.
		5. A printWords method, which prints all the unique words in a Trie in alphabetical order, along with their number of occurences.
		6. A compareTries method, which counts the number of occurences of unique words in Trie 1, in Trie 2, recursively.

-
	Gavriel Tsioni