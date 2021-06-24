
import java.util.Scanner;

class ShiftCipherBruteForce{

	public void decrypt(String s, int key){
		int l=s.length();
		char a[]=s.toCharArray();
		System.out.print("Your decrypted text is: ");
		char convertplain=0;
		for(int i=0;i<l-1;i++){
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
			convertplain='\0';
		}
	}


	public static void main(String []args){
		ShiftCipher c=new ShiftCipher();
		Scanner sc = new Scanner(System.in);
			System.out.println("\nBruteforce attack on shift cipher");
			
				System.out.print("Enter cipher to decrypt: ");
				String s=sc.nextLine();
				for(int i=0;i<26;i++)
				{
					System.out.print("\nFor key "+i+":");
					c.decrypt(s,i);
				}
		} 
	}




/*

Bruteforce attack on shift cipher
Enter cipher to decrypt: lm

For key 0:Your decrypted text is: lm
For key 1:Your decrypted text is: kl
For key 2:Your decrypted text is: jk
For key 3:Your decrypted text is: ij
For key 4:Your decrypted text is: hi
For key 5:Your decrypted text is: gh
For key 6:Your decrypted text is: fg
For key 7:Your decrypted text is: ef
For key 8:Your decrypted text is: de
For key 9:Your decrypted text is: cd
For key 10:Your decrypted text is: bc
For key 11:Your decrypted text is: ab
For key 12:Your decrypted text is: za
For key 13:Your decrypted text is: yz
For key 14:Your decrypted text is: xy
For key 15:Your decrypted text is: wx
For key 16:Your decrypted text is: vw
For key 17:Your decrypted text is: uv
For key 18:Your decrypted text is: tu
For key 19:Your decrypted text is: st
For key 20:Your decrypted text is: rs
For key 21:Your decrypted text is: qr
For key 22:Your decrypted text is: pq
For key 23:Your decrypted text is: op
For key 24:Your decrypted text is: no
For key 25:Your decrypted text is: mn
 
*/
