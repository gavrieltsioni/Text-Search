import java.util.ArrayList;
public class TrieNode {
	
	private char letter;
	private boolean end;
	int occurrences;
	private TrieNode[] children = new TrieNode[26];
	
	public TrieNode(char letter){
		this.letter = letter;
		occurrences = 0;
		
	}
	public TrieNode(char letter, boolean end){
		this.letter = letter;
		this.end = end;
		occurrences = 0;
	}
	
	//This method gives the node a new child, based on the given arguments.
	public void addLetter(char add, boolean isEnd){
		if(!hasChild(add)){
			children[add-'a'] = new TrieNode(add, isEnd);
		}
		if(isEnd){ 
			children[add-'a'].setEnd(true);
			children[add-'a'].occurrences+=1;
		}
	}
	public boolean hasChild(char letter){
		if(letter-'a'>25||letter-'a'<0) return false;
		if(children[letter-'a'] == null) return false;
		return true;
	}
	public char getLetter(){
		return letter;
	}	
	public void setLetter(char letter){
		this.letter = letter;
	}
	public boolean isEnd(){
		return end;
	}
	public void setEnd(boolean end){
		this.end = end;
	}
	public int getOccurrences(){
		return occurrences;
	}
	public TrieNode[] getChildren(){
		return children;
	}
}
