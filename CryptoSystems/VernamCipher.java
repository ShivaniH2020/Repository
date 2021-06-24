
import java.util.Random;
import java.util.Scanner;
public class VernamCipher {

	public static String givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(int length) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = length;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		return generatedString;
	}

	public  String stringEncryption(String text, String key)
	{
		String cipherText = "";

		int cipher[] = new int[key.length()];

		for (int i = 0; i < key.length(); i++)
		{
			cipher[i] = ((char)(((text.charAt(i) -'a'+ key.charAt(i)-'a')%26)+'a'));
			cipherText=cipherText+(char)cipher[i];
		}
		System.out.println("cipherText generated: "+cipherText);
		return cipherText;
	}

	public String stringDecryption(String s,String key)
	{
		String plainText = "";
		int plain[] = new int[key.length()];

		for (int i = 0; i < key.length(); i++)
		{
			plain[i]= s.charAt(i) - 'a' - (key.charAt(i) - 'a');
		}

		for (int i = 0; i < key.length(); i++)
		{
			if (plain[i] < 0)
			{
				plain[i] = plain[i] + 26;
			}
		}

		for (int i = 0; i < key.length(); i++)
		{
			int x = plain[i] + 'a';
			plainText += (char)x;
		}
		System.out.print("Decrypted to: "+plainText);
		return plainText;
	}

	public static void main(String[] args)
	{

		VernamCipher vc=new VernamCipher();
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			int choice = sc.nextInt();
			String s;
			if(choice==1){
				System.out.print("Enter text to encrypt: ");
				sc.nextLine();
				s=sc.nextLine();
				s=s.toLowerCase().replaceAll("\\s", "");
				String 	key=givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(s.length());
				System.out.println("Key generated: "+key);
				vc.stringEncryption(s,key);
			}
			else if(choice==2){
				System.out.print("Enter cipher to decrypt: ");
				sc.nextLine();
				s=sc.nextLine();
				s=s.replaceAll("\\s", "");
				System.out.print("Enter key: ");
				String 	keyentered=sc.nextLine();
				System.out.print("Decrypted to: ");
				vc.stringDecryption(s,keyentered);
			}
			else{
				break;
			}
		}
	}
}




/*

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: vit Vellore
Key generated: iowmvixhon
cipherText generated: dwphztivfr

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: dwphztivfr
Enter key: iowmvixhon
Decrypted to: Decrypted to: vitvellore
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3

*/
