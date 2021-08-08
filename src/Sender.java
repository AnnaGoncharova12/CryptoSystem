import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
/*
 * Class purpose: provides utilities to encrypt a message with AES and have the AES key
 * encrypted with RSA as well as to forward the encrypted message and key to the
 * Recipient class.
 */
public class Sender {
    private SecretKeySpec secretKey;//used for message encryption
    private String encryptMode;//used for message encryption
    private String key;
    private byte[] byteKey;
    private Recipient Jenny;
    RSAEncryptor keyEncryptor;//used for key encryption
    
    public Sender(String encryptMode, String key, String algorithm, Recipient Jenny) {
    	this.encryptMode=encryptMode;
    	this.key=key;
    	this.Jenny=Jenny;
    	this.keyEncryptor= new RSAEncryptor(Jenny.keyDecrytor);
    	try {
    		//transform the key into bytes using the utf-8 charset to make it AES-usable
			byteKey = key.getBytes("UTF-8");
			 secretKey = new SecretKeySpec(byteKey, algorithm);
		} 
    	 catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
    
    /*delegates message encryption to AES and key encryption to RSA*/
  public void send(String message) {
	  String encrypted=encrypt(message);
	  ArrayList<Integer> codedKeyList=keyEncryptor.encrypt(key);
	  if(encrypted!=null) {
		 Jenny.receiveMsg(encrypted, codedKeyList);
	 }
  }
  
 /*provides message encryption with AES*/
    public String encrypt(String message) 
    {
        try{
            Cipher cipher = Cipher.getInstance(encryptMode);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().
           encodeToString(cipher.doFinal(message.getBytes("UTF-8")));
        } 
        catch (Exception e){
            System.out.println("Could not encrypt. Message status: not sent");
        }
        return null;
    }
}

