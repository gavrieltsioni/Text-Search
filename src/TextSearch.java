import java.util.Scanner;
public class TextSearch {
	public static void main(String[] args){
		System.out.println("Please enter a file name:");
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.next();
		Trie trie = new Trie();		
		if(!trie.convertToTrie(filename)){ 
			System.out.println("Invalid File Name. Program Closing."); 
			System.exit(0);			
		}
		int choice = 0;
		while(choice != 1 && choice != 2 && choice !=3){
			System.out.print("File loaded. Would you like to...\n1) Search for a word?\n2) View all words and word counts?\n3) Compare this file to another file?\n");
			choice = scanner.nextInt();
		}
		
		switch(choice){
			case 1: System.out.println("What word would you like to search for?"); 
					String search = scanner.next();
					int num = trie.search(search);
					if(num != -1){
						System.out.print("The string " + search + " appears " + num + " time");
						if(num != 1){
							System.out.print("s");
						}
						System.out.println(".");	
					} else{
						System.out.print("Invalid string.");
					}
							
					break;
			case 2:
					trie.printWords(trie.getRoot(), "");			
					break;					
			case 3:
					System.out.println("What file would you like to compare it to? (Output formatted as [word in File 1]: [occurrences in File 2])");
					String filename2 = scanner.next();
					Trie trie2 = new Trie();		
					if(!trie2.convertToTrie(filename2)){ 
						System.out.println("Invalid File Name. Program Closing."); 
						System.exit(0);			
					}
					trie.compareTries(trie.getRoot(), trie2.getRoot(), "");
					break;
		}
		
	}
}