import java.util.Scanner;

public class VigenereCipher
{

	String get_modified_key(int input_length, int key_length, String key){

		int k = 0, s = 0;
		char keyarr[]=key.toCharArray();
		String modified_key="";
		while(s < input_length){
			if(k == key_length){
				k = 0;
			}
			modified_key = modified_key+keyarr[k];
			s++;
			k++;
		}
		modified_key = modified_key+'\0';
		return modified_key;
	}
	public void encrypt(String text, String key)
	{

		char plain_text[]=text.toCharArray();
		int plain_length = text.length();
		int key_length = key.length();
		String modified_key = get_modified_key(plain_length, key_length, key);
		System.out.println("Modified key: "+modified_key+"\n");
		System.out.print("Encrypted Text: ");
		for(int i = 0 ; i < plain_length; i++){
			int ascii_plain_converted = plain_text[i] - 97;
			int ascii_key_converted = modified_key.toCharArray()[i] - 97;
			char output = (char) (((ascii_key_converted + ascii_plain_converted)%26) + 97);
			System.out.print(output);
		}
		System.out.println();
	}

	public void decrypt(String text, final String key)
	{
		char[] cipher_text=text.toCharArray();
		int cipher_length = text.length();
		int key_length = key.length();
		String modified_key = get_modified_key(cipher_length, key_length, key);
		for(int i = 0; i < cipher_length; i++){
			int ascii_cipher_converted = cipher_text[i] - 97;
			int ascii_key_converted = modified_key.toCharArray()[i] - 97;
			int output = (ascii_cipher_converted - ascii_key_converted)%26;
			if(output < 0){
				output = output + 26;
			}
			output = output +97;
			System.out.print((char)output);
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		VigenereCipher vgc=new VigenereCipher();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Key : ");
		String 	key=sc.nextLine();
		key=key.toLowerCase();
		System.out.print("Entered Key : "+key);
		while(true){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			int choice = sc.nextInt();
			String s;
			if(choice==1){
				System.out.print("Enter text to encrypt: ");
				sc.nextLine();
				s=sc.nextLine();
				s=s.toLowerCase().replaceAll("\\s", "");
				System.out.print("E" +s);

				vgc.encrypt(s.replaceAll("\\s", ""),key);
			}
			else if(choice==2){
				System.out.print("Enter cipher to decrypt: ");
				sc.nextLine();
				s=sc.nextLine();
				s=s.replaceAll("\\s", "");
				System.out.print("Decrypted text: ");
				vgc.decrypt(s,key);
			}
			else{
				break;
			}
		}
	}
}




/*

Enter Key : scope
Entered Key : scope
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: hellothere
EhellothereModified key: scopescope 

Encrypted Text: zgzasljsgi

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: zgzasljsgi
Decrypted text: hellothere

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3



Enter Key : scope
Entered Key : scope
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: hi
EhiModified key: sc 

Encrypted Text: zk

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: hello
EhelloModified key: scope 

Encrypted Text: zgzas

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: zgzas
Decrypted text: hello

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: zk
Decrypted text: hi

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3

*/
