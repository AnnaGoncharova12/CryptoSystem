import java.math.BigInteger;
import java.util.HashMap;
/*
 * Class purpose: provides utilities to decrypt an RSA-encrypted message
 */
public class RSADecryptor {

		private int prime1=907;
		private int prime2=773;
		public int n;//modulus
		public int e;
		private int totient;
		int c;
		//BigInteger class assists with calculations
		public BigInteger BIn;
		public BigInteger BIe;
		private BigInteger BItotient;
		private BigInteger d;
		
		
	public RSADecryptor() {
		 //this.totient=lcm(prime1-1, prime2-1);
		  /*
		   //random selection of e value provides better security
		  Random helper= new Random();
		  this.e=helper.nextInt(totient-2)+2;
		  */
		this.e=11;//choosing a smaller value for a more efficient demonstration
		this.totient=lcm(prime1-1, prime2-1);
		this.n=prime1*prime2;
		this.BIn=new BigInteger(Integer.toString(prime1*prime2));
		 BIe= new BigInteger(Integer.toString(e));
		BItotient= new BigInteger(Integer.toString(totient));
	}

	public char decrypt(int c) {
		this.d=BIe.modInverse(BItotient); 
		
		BigInteger BIc = new BigInteger(Integer.toString(c));
		int m=BIc.pow(d.intValue()).mod(BIn).intValue();

		char mToLetter=(char)m;
		return mToLetter;
	}
	//function that finds lcm of two numbers using prime factorization
	public int lcm(int p, int q) {
		  int ans=1;
		  HashMap<Integer, Integer> pMap = new HashMap<>();
		  HashMap<Integer, Integer> qMap = new HashMap<>();
		  primeFactorizer(p, pMap);
		  primeFactorizer(q, qMap);
		  for(Integer i: pMap.keySet()) {
			  if(qMap.containsKey(i)) {
				  ans*=Math.pow(i,  Math.max(pMap.get(i), qMap.get(i)));
				  qMap.remove(i);
			  }
			  else {
				  ans*=Math.pow(i,  pMap.get(i));
			  }
		  }
		  for(Integer i: qMap.keySet()) {
			 
				  ans*=Math.pow(i, qMap.get(i));
			  
		  }
		  return ans;
		  
	}
	//function that factorizes a number into primes and saves 
	//the result in a hashmap
	public void primeFactorizer(int a, HashMap<Integer, Integer> hm) {
		  int prime=2;
		  while(a!=1) {
			  if(a%prime==0) {
				  hm.putIfAbsent(prime, 0);
				  hm.put(prime, hm.get(prime)+1);
				  a/=prime;
			  }
			  else {
				  prime++;
			  }
			  
		  }
	}
}

