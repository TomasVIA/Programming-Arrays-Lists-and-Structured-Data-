package week1;

import edu.duke.*;

import java.io.File;

public class Ceasar_Cipher {
	
	public String encrypt(String input, int key) {
		
		StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet2 = "abcdefghijklmnopqrstuvwxyz";
        
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        String shiftedAlphabet2 = alphabet2.substring(key)+
        alphabet2.substring(0,key);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            int idx2 = alphabet2.indexOf(currChar);
            
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            if(idx2 != -1){
                char newChar2 = shiftedAlphabet2.charAt(idx2);
                encrypted.setCharAt(i, newChar2);
            }
        }
        return encrypted.toString();
	}
	/*
	public String shiftAlphabet(int key) {
		
		String alphUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
        String alphLow = "abcdefghijklmnopqrstuvwxyz";
		
		String shiftAlphLow = alphLow.substring(key)+
		alphLow.substring(0,key);
		String shiftAlphUp = alphUp.substring(key)+
	    alphUp.substring(0,key);
		
		return shiftAlphLow;
	}*/
	
	public String encryptTwoKeys(String input, int key1, int key2) {
		
			String encrypted = encrypt(input, key1);
			StringBuilder encrypted2 = new StringBuilder(encrypted);             
			
			String alphUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	        String alphLow = "abcdefghijklmnopqrstuvwxyz";
	                       
	        String shiftAlphLow = alphLow.substring(key1)+
	        alphLow.substring(0,key1);
	        String shiftAlphUp = alphUp.substring(key1)+
	        alphUp.substring(0,key1);
	        
	        String shiftAlphLow2 = alphLow.substring(key2)+
	        alphLow.substring(0,key2);
	    	String shiftAlphUp2 = alphUp.substring(key2)+
	    	alphUp.substring(0,key2);
	  
	        for(int i = 1; i < encrypted.length(); i+=2) {
	            
	        	char currChar = encrypted.charAt(i);
	            int idx = shiftAlphUp.indexOf(currChar);
	            int idx2 = shiftAlphLow.indexOf(currChar);
	            
	            if(idx != -1 && idx%1 == 0){
	                char newCharUp = shiftAlphUp2.charAt(idx);
	                encrypted2.setCharAt(i, newCharUp);
	            }
	            if(idx2 != -1 && idx2%1 == 0){
	                char newCharLow = shiftAlphLow2.charAt(idx2);
	                encrypted2.setCharAt(i, newCharLow);
	            }
	        }
	        
	        return encrypted2.toString();
	 
		}
	

	public void testCeasar() {
		int key = 15;
		FileResource fr = new FileResource();
		String message = fr.asString();
		String encrypted = encrypt(message, key);
		System.out.println("key is " + key + "\n" + encrypted);
	}
	
	public void testEncryptTwoKeys() {
		int key1 = 23;
		int key2 = 17;
		FileResource fr = new FileResource();
		String message = fr.asString();
		String encryptTwoKeys = encryptTwoKeys(message, key1, key2);
		System.out.println("keys are " + key1 + " " + key2 + "\n" + encryptTwoKeys);
	}
	
	public static void main(String[] args) {
		Ceasar_Cipher test = new Ceasar_Cipher();
		//test.testCeasar();
		test.testEncryptTwoKeys();
	}
}
