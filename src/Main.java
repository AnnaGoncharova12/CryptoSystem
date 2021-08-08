/*
 * Class purpose: makes the necessary initialization and function calls to
 * demonstrate the work of the cryptosystem
 */

public class Main {
	private static String key = "ssshhhhhhhhhhh!!";
  public static void main(String[] args) {
	  Recipient Jenny=new Recipient("AES", "AES/ECB/PKCS5Padding");
	 Sender Sam = new Sender("AES/ECB/PKCS5Padding", key, "AES", Jenny);
	 Sam.send("Test message");
  }
}
