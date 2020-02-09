
import java.util.Scanner;

public class CSCI {
	
	
	public static void main(String [] args) {
		System.out.print("CSCI Cipher Operations: \n-------------------------------------\n1. Decrypt and verify Caesar cipher\n2. Decrypt and verify Vigenere cipher\n3. Display this menu again\n4. Quit\n"); 
		
		Scanner choice;Scanner alphabet; Scanner cipher;Scanner key;Scanner alphabet1;Scanner cipher1;Scanner key1;//out of the loop because, otherwise, causes some weird exception
		int c;
		do {
		System.out.printf("\nChoice : "); 
		 choice = new Scanner(System.in);
		 c = choice.nextInt();
		 while(c<1 || c>4 || c==3){
		 if(c==3){
		 System.out.print("\nCSCI Cipher Operations: \n-------------------------------------\n1. Decrypt and verify Caesar cipher\n2. Decrypt and verify Vigenere cipher\n3. Display this menu again\n4. Quit\n"); 
		 }
		 else{
		 System.out.println("\nInvalid choice! Try again ");
		 }
		 System.out.printf("\nChoice : ");
		 c = choice.nextInt();
	}
		

		 System.out.print("\n");
		 
		 
		 if(c==1){
			 System.out.print("Enter alphabet    : ");
			 alphabet = new Scanner(System.in);//it is scanner, declared at the beginning of the class.
			 String alphabetS = alphabet.nextLine(); 
	
			 System.out.print("Enter cipher text : ");
			 cipher = new Scanner(System.in);
			 String cipherS = cipher.nextLine(); 
		 
			 System.out.print("Enter key         : ");
			 key = new Scanner(System.in);
			 String keyS = key.nextLine(); 
			 
		 
			 Cipher decryptCeaser = new Caesar();
			 String decryptedCeaser = decryptCeaser.decrypt(alphabetS, cipherS, keyS);
          
			 Cipher encryptCeaser = new Caesar();
			 String encryptedCeaser = encryptCeaser.encrypt(alphabetS, decryptedCeaser, keyS);
	
			 String check;
			 	if(cipherS.contentEquals(encryptedCeaser)) {
			 		check = "true";
			 	}
			 	else {
			 		check = "false";
			 	}
			 	
			 System.out.printf("\nPlain text                               : %s\n",decryptedCeaser );
			 System.out.printf("Plain text re-encrypted for verification : %s\n", encryptedCeaser);
			 System.out.printf("Are cipher text and encrypted text equal : %s\n", check);
			 

		 }
		 
		 
		 
		 
		 if(c==2){
			 System.out.print("Enter alphabet    : ");
			 alphabet1 = new Scanner(System.in);
			 String alphabetS = alphabet1.nextLine(); 
			 
		
			 System.out.print("Enter cipher text : ");
			 cipher1 = new Scanner(System.in);
			 String cipherS = cipher1.nextLine(); 
			 
			 System.out.print("Enter key         : ");
			 key1 = new Scanner(System.in);
			 String keyS = key1.nextLine(); 
			 
			 Cipher decryptVigenere = new Vigenere();
	         String decryptedVigenere = decryptVigenere.decrypt(alphabetS, cipherS, keyS);
	         
	         Cipher encryptVigenere = new Vigenere();
	         String encryptedVigenere = encryptVigenere.encrypt(alphabetS, decryptedVigenere, keyS);
		
			 String check;
			 if(cipherS.contentEquals(encryptedVigenere)) {
				 check = "true";
			 }
			 else {
				 check = "false";
			 }
			 
			 System.out.printf("\nPlain text                               : %s\n",decryptedVigenere );
			 System.out.printf("Plain text re-encrypted for verification : %s\n", encryptedVigenere);
			 System.out.printf("Are cipher text and encrypted text equal : %s\n", check);
			 
			 

			 
			 }
		 
		 if(c==4){
			 System.out.print("Have a crime-free day!");
		 
		 
	}
			 
		 }while(c!=4);		

		
	}
}

interface Cipher{
	String encrypt(String alphabet, String plainText, String Key);
	String decrypt(String alphabet, String plainText, String Key);
	
	
}

class Caesar implements Cipher{
	public String encrypt(String alphabet, String plainText, String Key) {
		for(int i = 0; i<plainText.length(); i++) {
			
			for(int j = 0; j<alphabet.length(); j++) {
				
			   if(plainText.charAt(i) == alphabet.charAt(j)) {
				
				 char[] plainChars = plainText.toCharArray();
				 char[] alphabetChars = alphabet.toCharArray();
				 plainChars[i] = alphabetChars[(j+Integer.parseInt(Key))%alphabet.length()];
				 plainText = String.valueOf(plainChars);
				 alphabet = String.valueOf(alphabetChars);
				 break;
			 }
				
			}
			
		}
			
		return plainText;
	}
	public String decrypt(String alphabet, String cipherText, String Key) {
		for(int i = 0; i<cipherText.length(); i++) {
			
			for(int j = 0; j<alphabet.length(); j++) {
				
			  if(cipherText.charAt(i) == alphabet.charAt(j)) {
				  
				 char[] cipherChars = cipherText.toCharArray();
				 char[] alphabetChars = alphabet.toCharArray();
				 cipherChars[i] = alphabetChars[(alphabet.length()+j-Integer.parseInt(Key))%alphabet.length()];
				 cipherText = String.valueOf(cipherChars);
				 alphabet = String.valueOf(alphabetChars);
				 break;
				 
			 }
			}
		}
		return cipherText;
	}
}





class Vigenere implements Cipher{
	public String encrypt(String alphabet, String plainText, String Key) {
		char[] plainChars = plainText.toCharArray();
		char[] alphabetChars = alphabet.toCharArray();
		char[] KeyChars = Key.toCharArray();
		for(int i = 0; i<plainText.length(); i++) {
            int j;
			for(j = 0; j<alphabet.length(); j++) {
			 if(plainChars[i] == alphabetChars[j]) {
				 break;
			 }
			}
			int k;
			for(k = 0; k<alphabet.length(); k++) {
			 if(KeyChars[i % Key.length()] == alphabetChars[k]){
				break;
			}
			}
			int distance = (j+k) % alphabet.length() ;
			
			plainChars[i] = alphabetChars[distance];
		}
		 plainText = String.valueOf(plainChars);
		 alphabet = String.valueOf(alphabetChars);
		 Key = String.copyValueOf(KeyChars);
	
		
		return plainText;
		
	}
	public String decrypt(String alphabet, String cipherText, String Key) {
		char[] cipherChars = cipherText.toCharArray();
		char[] alphabetChars = alphabet.toCharArray();
		char[] KeyChars = Key.toCharArray();
		for(int i = 0; i<cipherText.length(); i++) {
            int j;
			for(j = 0; j<alphabet.length(); j++) {
			 if(cipherChars[i] == alphabetChars[j]) {
				 break;
			 }
			}
			int k;
			for(k = 0; k<alphabet.length(); k++) {
			 if(KeyChars[i % Key.length()] == alphabetChars[k]){
				break;
			}
			}
			int distance = (j-k+alphabet.length()) % alphabet.length();
			
			cipherChars[i] = alphabetChars[distance];
		}
		 cipherText = String.valueOf(cipherChars);
		 alphabet = String.valueOf(alphabetChars);
		 Key = String.copyValueOf(KeyChars);
	
		return cipherText;
	}
}
		
	
	
	


