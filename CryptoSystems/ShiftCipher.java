import java.util.Scanner;

class ShiftCipher{

	public void encrypt(String s, int key){
		int l=s.length();

		char a[]=s.toCharArray();
		System.out.print("Your encrypted text is: ");
		char convertascii = 0;
		for(int i=0;i<l;i++){

			int ch = a[i];

			if(ch>='a'&&ch<='z')
			{
				ch=ch+key;
				if(ch>'z')
					ch=ch-'z'+'a'-1;
				convertascii=(char)ch;
			}
			else if(ch>='A'&&ch<='Z')
			{
				ch=ch+key;
				if(ch>'Z')
					ch=ch-'Z'+'A'-1;
				convertascii=(char)ch;
			}
			System.out.print(convertascii);
		}
	}

	public void decrypt(String s, int key){
		int l=s.length();
		char a[]=s.toCharArray();
		System.out.print("Your decrypted text is: ");
		char convertplain=0;
		for(int i=0;i<l;i++){
			int ch=a[i];
			if(ch>='a'&&ch<='z')
			{
				ch=ch-key;
				if(ch<'a')
					ch=ch+'z'-'a'+1;
				convertplain = (char)ch;
			}
			else if(ch>='A'&&ch<='Z')
			{
				ch=ch-key;
				if(ch<'A')
					ch=ch+'Z'-'A'+1;
				convertplain=(char)ch;
			}
			System.out.print(convertplain);
		}
	}


	public static void main(String []args){
		ShiftCipher c=new ShiftCipher();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Key(0-25): ");
		int key=sc.nextInt();
		key=key%26;
		while(true){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			int choice = sc.nextInt();
			String s;
			if(choice==1){
				System.out.print("Enter text to encrypt: ");
				s=sc.next();
				c.encrypt(s,key);
			}
			else if(choice==2){
				System.out.print("Enter cipher to decrypt: ");
				s=sc.next();
				c.decrypt(s,key);
			}
			else{
				break;
			}
		} 
	}
}






/*

Enter Key(0-25): 56

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: hi
Your encrypted text is: lm
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: lm
Your decrypted text is: hi
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3

*/
