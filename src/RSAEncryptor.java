import java.math.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RSAEncryptor {


  public int n;//modulus recived from recipient
  public int e;//e and n comprise the public key


  public RSAEncryptor(RSADecryptor decr) {
	  this.n=decr.n;

	  this.e=decr.e;

  }
  public ArrayList<Integer> encrypt(String message)  {

	  ArrayList<Integer> toReturn = new ArrayList<>();
	  for(int i=0;i<message.length();i++) {
		  //System.out.println(k);
		  int c = encryptUtil(message.charAt(i));
		  toReturn.add(c);
	  }
	  return toReturn;

  }
  public int encryptUtil(int m) {
	  BigInteger holder=new BigInteger(Integer.toString(m));

	  BigInteger BIn= new BigInteger(Integer.toString(n));
	  return  holder.pow(e).mod(BIn).intValue();
  }


}