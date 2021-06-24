import java.util.Scanner;

class ModifiedShiftCipher{

	public void encrypt(String s, int key){
		int l=s.length();
		char a[]=s.toCharArray();
		System.out.print("Your encrypted text is: ");
		char convertascii = 0;
		for(int i=0;i<l;i++){
			if(i==32)
				continue;
			int ch = a[i];
			int prevch;
			if(ch>='a'&&ch<='z')
			{
				ch=ch-97;
				if(i==0)
					prevch=0;
				else 
					prevch=a[i-1]-97;
				ch=ch+prevch+key;
				ch=ch+97;
				if(ch>'z')
					ch=ch-'z'+'a'-1;
				convertascii=(char)ch;
			}
			else if(ch>='A'&&ch<='Z')
			{
				ch=ch-65;
				if(i==0)
					prevch=0;
				else
					prevch=a[i-1]-65;
				ch=prevch+ch+key;
				ch=ch+65;
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
		char pt[]=new char[l];
		for(int i=0;i<l;i++){
			int ch=a[i];
			int prevch;
			if(ch>='a'&&ch<='z')
			{	
				ch=ch-97;
				if(i==0)
					prevch=0;
				else
					prevch=pt[i-1]-97;
				ch=ch-prevch-key;
				if(ch<0)
					ch=ch+26;
				ch=ch+97;
				if(ch<'a')
					ch=ch+'z'-'a'+1;
				convertplain = (char)ch;

			}
			else if(ch>='A'&&ch<='Z')
			{
				ch=ch-65;
				if(i==0)
					prevch=0;
				else
					prevch=pt[i-1]-65;
				ch=ch-prevch-key;
				if(ch<0)
					ch=ch+26;
				ch=ch+65;
				if(ch<'A')
					ch=ch+'Z'-'A'+1;
				convertplain=(char)ch;
			}
			pt[i]=convertplain;
			System.out.print(convertplain);
		}
	}


	public static void main(String []args){
		ModifiedShiftCipher c=new ModifiedShiftCipher();
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		System.out.print("Enter Key(0-25): ");
		int key=sc.nextInt();
		while(true){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			int choice = sc.nextInt();
			String s;
			if(choice==1){
				System.out.print("Enter text to encrypt: ");
				s=sc.next();
				s=s.replaceAll(" ", "");
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


Enter Key(0-25): 3

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: Hi
Your encrypted text is: KS
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: KS
Your decrypted text is: HI
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3

*/
