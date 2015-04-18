import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Trie {
	private TrieNode root;
	public Trie(){
		root = new TrieNode('\0', false);
	}
	
	//This method turns a given file name into a Trie data structure.
	public boolean convertToTrie(String filename){
		File text = new File(filename);
		Scanner scanner = new Scanner(System.in);	
		
		//Ensure the file is valid
		try{
			scanner = new Scanner(text);
		} 
		catch (Exception e) {
			//e.printStackTrace();
			scanner.close();
			return false;
		}
		
		//This loop goes through every word in the text, converts it to lowercase, cleans it, and adds that word to the Trie.
		while(scanner.hasNext()){
			String currentString = scanner.next();
			currentString = currentString.toLowerCase();
			currentString = cleanWord(currentString);
			if(currentString == null) continue;
			addWord(root, currentString);
		}
		return true;
	}
	
	public TrieNode getRoot(){
		return root;
	}
	
	//This method adds a given word to a given root.
	public static void addWord(TrieNode root, String word){
		TrieNode pointer = root;
		int current = 0;
		boolean end = false;
		
		//This loop goes through each char of the word and adds that char to the given node. It then recalls the addWord method, with the new node as an argument.
		while(current < word.length()){
			if(current == word.length()-1) end = true;
			pointer.addLetter(word.charAt(current), end);
			pointer = pointer.getChildren()[word.charAt(current)-'a'];
			current++;
		}
	}
	
	//This method ensures the given word is not letters surrounding punctuation(aaa'a, !thth;!, etc.). It then removes all non letters.
	public String cleanWord(String word){
		//This is a regular expression which ensures that only words following this form are used. ([any nonletters or any numbers][at least one letter][any nonletters or any numbers])
		if(!word.matches("([\\W]*|[0-9]*)[a-z]+([\\W]*|[0-9]*)")){
			return null;
		}
		
		//Removes all nonletters.
		for(int i = 0; i < word.length(); i++){
			if(word.charAt(i)-'a'>25||word.charAt(i)-'a'<0){			
				word = word.substring(0, i) + word.substring(i+1);
				i--;
			}
		}
		if(!word.isEmpty()) return word;
		return null;
	}
	
	//Debug
	public void toString(TrieNode node){
		System.out.println("\n" + node.getLetter() + " has children");
		TrieNode[] nodes = node.getChildren();
		for(int i = 0; i < nodes.length; i ++){
			if(nodes[i] == null) continue;
			System.out.print(nodes[i].getLetter() + ", ");
		}
		System.out.print("[" + node.getOccurrences() + "]");
		for(int i = 0; i < nodes.length; i++){
			if(nodes[i] == null) continue;
			toString(nodes[i]);
		}
	}
	
	//This method searches for a given word in the Trie data structure.
	public int search(String word){
		word = cleanWord(word);
		if(word==null){
			return -1;
		}
		
		int current = 0;
		TrieNode pointer = root;
		
		//This loop goes letter by letter through the word, traversing the Trie by following that word's path down the nodes. If at the end, the last node is an ending node, then the word has occurred in the Trie, with that nodes number of occurrences as the number of times it appears.
		while(current < word.length()){
			TrieNode next = pointer.getChildren()[word.charAt(current)-'a'];
			if(next == null){
				return 0;
			}
			pointer = next;
			current++;
		}
		if(pointer.isEnd()) return pointer.occurrences;
		//If it's not an ending node, then we've just found a node that belongs to the middle of some other word, so the given word doesn't actually occur in the Trie.
		return 0;
	}
	
	//This method prints all the words in the Trie and their number of occurrences, recursively.
	public void printWords(TrieNode node, String currentWord){
		//Concatenate the current nodes letter to the end of the currentWord
		if(node != root) currentWord = currentWord + node.getLetter();
		//If the current node is the end of a word, we can print currentWord, or the concatenation of all letters from the root to this node.
		if(node.isEnd()){
			System.out.print(currentWord + ": " + node.getOccurrences());		
			System.out.print("\n");
		}
		TrieNode[] nodes = node.getChildren();
		//This loop moves through the current nodes children and calls this method on them individually from left to right. This also preserves alphabetical order.
		for(int i = 0; i < nodes.length; i++){
			if(nodes[i] == null) continue;
			printWords(nodes[i], currentWord);
		}
	}
	
	//This method compares this Trie to another Trie, recursively.
	public void compareTries(TrieNode ptr1, TrieNode ptr2, String currentWord){
		//ptr1 moves through this trie, and ptr2 maintains the root of the second file's trie.
		//Concatenate the current node's letter to the currentWord (the sum of all the concatenations from the root to this node)
		if(ptr1 != root) currentWord = currentWord + ptr1.getLetter();
		//If that's the end of a word, check how many times that word occurs in the second file's trie.
		if(ptr1.isEnd()){
			System.out.print(currentWord + ": ");
			TrieNode pointer = ptr2;
			int i;
			//This loop moves through the currentWord, which we know is a full word, and checks if it exists in the second file's trie.
			for(i = 0; i < currentWord.length(); i++){
				if(pointer.getChildren()[currentWord.charAt(i)-'a'] == null){
					break;
				}
				pointer = pointer.getChildren()[currentWord.charAt(i)-'a'];
			}
			//If the node we landed on is the end of a word, print it's occurrences. Otherwise we haven't found the word and it doesn't exist in the second file.
			if(i==currentWord.length() && pointer.isEnd()){
				System.out.print(pointer.getOccurrences() + "\n");
				
			} else {
				System.out.print("0\n");
			}
		}
		TrieNode[] nodes = ptr1.getChildren();
		////This loop moves through the current nodes children and calls this method on them individually from left to right.
		for(int i = 0; i < nodes.length; i++){
			if(nodes[i] == null) continue;
			compareTries(nodes[i], ptr2, currentWord);
		}
	}
}
