import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AffineCipher{

	public int inverse(int a)
	{
		int a_inverse=0;
		while(true){
			if((a*a_inverse)%26 == 1){
				break;
			}
			else{
				a_inverse++;
			}
		}
		return a_inverse;
	}

	public void encrypt(String s,int a, int b){
		int l=s.length();

		char arr[]=s.toCharArray();
		System.out.print("Your encrypted text is: ");
		char convertascii = 0;
		for(int i=0;i<l;i++){

			int ch = arr[i];

			if(ch>='a'&&ch<='z')
			{
				ch=ch-'a';
				ch=((((ch*a)+b)%26)+'a');

				if(ch>'z')
					ch=ch-'z'+'a'-1;
				convertascii=(char)ch;
			}
			else if(ch>='A'&&ch<='Z')
			{
				ch=ch-'A';
				ch=((((ch*a)+b)%26)+'a');
				if(ch>'Z')
					ch=ch-'Z'+'A'-1;
				convertascii=(char)ch;
			}
			System.out.print(convertascii);
		}
	}

	public void decrypt(String s,int a,int b){
		int l=s.length();
		char arr[]=s.toCharArray();
		int a_inverse=inverse(a);
		//System.out.print("a inverse is "+a_inverse);
		System.out.print("Your decrypted text is: ");
		char convertplain=0;
		for(int i=0;i<l;i++){
			int ch=arr[i];
			if(ch>='a'&&ch<='z')
			{
				ch=ch-'a';
				ch=((a_inverse*(ch-b))%26+'a');
				if(ch<'a')
					ch=ch+'z'-'a'+1;
				convertplain = (char)ch;
			}
			else if(ch>='A'&&ch<='Z')
			{
				ch=ch-'A';
				ch=((a_inverse*(ch-b))%26+'A');
				if(ch<'A')
					ch=ch+'Z'-'A'+1;
				convertplain=(char)ch;
			}
			System.out.print(convertplain);
		}
	}


	public static void main(String []args){
		List<Integer> list  = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(15);
		list.add(17);
		list.add(19);
		list.add(21);
		list.add(23);
		list.add(25);

		AffineCipher c = new AffineCipher();
		Scanner sc = new Scanner(System.in);
		int a;
		do {
			System.out.print("Enter a: (from 1,3,5,7,9,11,15,17,19,21,23,25) ");
			a=sc.nextInt();
		}while(list.contains(a)==false);
		System.out.print("Enter b: ");
		int b=sc.nextInt();
		int choice=0;
		while(choice!=3){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			choice = sc.nextInt();

			if(choice==1){
				System.out.print("Enter text to encrypt: ");
				sc.nextLine();
				String s=sc.nextLine();
				c.encrypt(s,a,b);
			}
			else if(choice==2){
				System.out.print("Enter cipher to decrypt: ");
				sc.nextLine();
				String s=sc.nextLine();
				c.decrypt(s,a,b);
			}
		} 
	}
}



/*

Enter a: (from 1,3,5,7,9,11,15,17,19,21,23,25) 2
Enter a: (from 1,3,5,7,9,11,15,17,19,21,23,25) 3
Enter b: 4

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: hi
Your encrypted text is: zc
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: zc
Your decrypted text is: hi
Enter your choice
1:Encrypt
2:Decrypt
3:Exit

*/
