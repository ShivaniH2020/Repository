import java.util.ArrayList;
import java.util.Scanner;

class SubstitutionCipher{

	public static int findIndex(int arr[], int t)
	{
		ArrayList<Integer> clist = new ArrayList<>();
		for (int i : arr)
			clist.add(i);
		return clist.indexOf(t);
	}

	public void encrypt(String s, int[] key){
		int l=s.length();
		char arr[]=s.toCharArray();
		System.out.print("Your encrypted text is: ");
		for(int i=0;i<l;i++){
			int location = arr[i]-65;
			int out_location = key[location] + 65;
			System.out.print((char)out_location);
		}
	}

	public void decrypt(String s, int[] key){
		int l=s.length();
		char a[]=s.toCharArray();
		System.out.print("Your decrypted text is: ");
		for(int i=0;i<l;i++){
			int ch=(a[i]-65);
			int index = findIndex(key,ch);
			char out = (char) (index + 65);
			System.out.print(out);
		}
	}

	public static void main(String []args){
		SubstitutionCipher c=new SubstitutionCipher();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Key(0-25): ");
		int key[]=new int[26];
		for(int i=0;i<26;i++)
		{
			key[i]=sc.nextInt();
			if(key[i]>25) 
			{
				key[i]=key[i]%26;
			}
		}
		while(true){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			int choice = sc.nextInt();
			String s;
			if(choice==1){
				sc.nextLine();
				System.out.print("Enter text to encrypt: ");

				s=sc.nextLine();
				s=s.replaceAll("\\s", "").toUpperCase();
				//System.out.print(" text to encrypt: "+ s);
				c.encrypt(s,key);
			}
			else if(choice==2){
				System.out.print("Enter cipher to decrypt: ");
				s=sc.next();
				s.toUpperCase().replaceAll("\\s", "");
				c.decrypt(s,key);
			}
			else{
				break;
			}

		} 

	}
}


/*

Enter Key(0-25): 24
25
22
23
21
20
15
16
17
18
19
14
13
12
11
10
2
3
4
5
6
7
8
9
1
0

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: welcome to vit vellore
Your encrypted text is: IVOWLNVFLHRFHVOOLDV
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: IVOWLNVFLHRFHVOOLDV
Your decrypted text is: WELCOMETOVITVELLORE
Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3
 
*/

