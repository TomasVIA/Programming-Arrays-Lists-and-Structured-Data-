package week1;

import java.util.Arrays;
import java.util.Iterator;

import edu.duke.FileResource;

public class CeasarBreaker {
	
		//COUNT OCCURENCES OF EVERY CHARACTER IN A STRING (with 'a' being stored in the first or index 0 location in the array)
		public int[] countLetters(String message) {
			String alph = "abcdefghijklmnopqrstuvwxyz";
			int [] counts = new int[26]; //array will have 26 empty spaces {0,0,0...}
			for(int k=0; k < message.length(); k++) {
				char ch = Character.toLowerCase(message.charAt(k)); 
				int dex = alph.indexOf(ch);
				if(dex != -1) {
					counts[dex] += 1; //now the letters counted will increment the numbers in the array
				}	
			}
			return counts;
			//return Arrays.toString(counts);
		}
		
		//DECRYPTS ENCRYPTED MESSAGE AND RETURN THE ORIGINAL STRING
		public String decrypt(String encrypted) {
			Ceasar_Cipher cc = new Ceasar_Cipher();
			int[] freqs = countLetters(encrypted); //will count the occurrences of every character in the string encrypted
			int maxDex = maxIndex(freqs);  //will return the index of the largest frequency letter
			System.out.println(maxDex);
			int dkey = maxDex - 4;        //distance from 'e' which has index 4
			if (maxDex < 4) {      
				dkey = 26 - (4 -maxDex); //wrap around from 26, to find the shift used for 'e'
			}
			return cc.encrypt(encrypted, 26-dkey); //if the value dkey was used to encrypt, then 26 minus dkey is used to decrypt, and we return the decryptive string
		}
		
		//RETURNS THE BIGGEST INDEX IN AN ARRAY
		public int maxIndex(int[] vals) {
			int maxDex = 0;
			int maxVal = 0;
			for(int k=0; k < vals.length; k++) {
				
				int currVal = vals[k];
				if (maxDex == 0 && currVal>0){
		               maxDex = k;
		               maxVal = currVal;
		        }
		        if (currVal>maxVal){
		               maxDex = k;
		               maxVal = currVal;
		        }  
		        }
		        return maxDex;
		    }
				
		public void testDecrypt() {
			FileResource fr = new FileResource();
			String message = fr.asString();
			String decrypted = decrypt(message);
			System.out.println(decrypted);
		}
		
		//RETURN A NEW STRING THAT IS EVERY OTHER CHARACTER FROM MESSAGE STARTING WITH THE START POSITION
		public String halfOfString(String message, int start) {  //START POSITION IS 0 OR 1
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = start; i < message.length(); i+=2) {
				
				char ch = message.charAt(i);
				sb.append(ch);
				
			}
			return sb.toString();
		}
		
		public int getKey (String s) {
			int[] frequencies = countLetters(s);
			int key = maxIndex(frequencies);
			/*
			int transKey = key - 4;        
			if (key > 4) {      
				transKey = 26 - (key - 4); 
			}*/
			return key;
		}
		
		public void decryptTwoKeys(String encrypted) {
			
			String firstChar = halfOfString(encrypted, 0);
			String secondChar = halfOfString(encrypted, 1);
			int key1 = getKey(firstChar);
			int key2 = getKey(secondChar);
	
			Ceasar_Cipher cipher = new Ceasar_Cipher();
			System.out.println("Keys are " + key1 + " and " + key2 + "\n" + cipher.encryptTwoKeys(encrypted, 26 - key1, 26 - key2));
		}
		
		public void countAlphabet() {
			String alph = "abcdefghijklmnopqrstuvwxyz";
			for (int i = 0; i < alph.length(); i++) {
				char letter = alph.charAt(i);
				System.out.println(i + "\t" + letter);
			}
			
		}
	
		public static void main(String[] args) {
			CeasarBreaker breaker = new CeasarBreaker();
			
			
			//breaker.countAlphabet();
			//System.out.println(breaker.halfOfString("Tcrgneixdc pcs htrjgxin pgt ujcspbtcipa epgih du idspn'h Xcitgcti tgp du tgtrixdc.", 1));
			
			FileResource fr = new FileResource();
		    String message = fr.asString();
		    breaker.decryptTwoKeys(message);
		    
			//System.out.println(breaker.decrypt("Tcrgneixdc pcs htrjgxin pgt ujcspbtcipattttt"));
			//Ceasar_Cipher encrypt = new Ceasar_Cipher();
			//System.out.println(encrypt.encrypt("Encryption and security are fundamental parts of today's Internet era of erection.", 15));
			//System.out.println(Arrays.toString(breaker.countLetters("Encryption and security are fundamental parts of today's Internet era of erection.")));
			//System.out.println(breaker.getKey("Encryption and security are fundamental parts of today's Internet era of erection."));
			
		}
		
		
		
		
}
