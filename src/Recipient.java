import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/*
 * Class purpose: decrypts the message received from the Sender class
 */
public class Recipient {
	private String key;
	private static byte[] byteKey;
	private SecretKeySpec secretKey;
	private String algorithm;
	private String decryptMode;//used for message decryption
	RSADecryptor keyDecrytor;
	
	public Recipient(String algorithm, String decryptMode) {
	this.algorithm=algorithm;
	this.decryptMode=decryptMode;
	keyDecrytor=new RSADecryptor();
	}
	/*delegates tasks to decrypt first the key and then the message*/
    public void receiveMsg(String codedMsg,  ArrayList<Integer> codedKeyList) {
       key=getKey(codedKeyList);
	   try {
   		//transform the key into bytes using the utf-8 charset to make it AES-usable
			byteKey = key.getBytes("UTF-8");
			secretKey = new SecretKeySpec(byteKey, algorithm);
		} 
   	 catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	   decrypt(codedMsg, key);
    }
    /*initiates RSA key decryption*/
    private String getKey(ArrayList<Integer> codedKeyList) {
    	StringBuilder builder= new StringBuilder();
    	for(Integer i: codedKeyList) {
    		builder.append(keyDecrytor.decrypt(i));
    	}
    	return builder.toString();
    }
    /*decrypts the received message through AES*/
   private String decrypt(String strToDecrypt, String secret){
       try { 
         Cipher cipher = Cipher.getInstance(decryptMode);
         cipher.init(Cipher.DECRYPT_MODE, secretKey);
         String msg= new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
         System.out.println("Decrypted : "+ msg);
         return msg;
         
       } 
       catch (Exception e) {
           System.out.println("Error while decrypting: " + e.toString());
       }
       return null;
   }


}
