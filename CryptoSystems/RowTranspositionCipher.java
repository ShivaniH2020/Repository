import java.util.ArrayList;
import java.util.Scanner;

class RowTranspositionCipher{

	public static int findIndex(int arr[], int t)
	{
		ArrayList<Integer> clist = new ArrayList<>();
		for (int i : arr)
			clist.add(i);
		return clist.indexOf(t);
	}

	public void encrypt(String s, int[] key, int key_l){
		int l=s.length();
		int cols=key_l,rows;
		if(l%key_l==0)
			rows=(l/key_l);
		else
			rows=(l/key_l)+1;
		for(int i=l;i<key_l*cols;i++)
		{
			s=s+'x';
		}
		char arr[]=s.toCharArray();
		int rail[][]=new int[key_l][cols];
		int k=0;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				rail[i][j]=arr[k++];
			}
		}
		System.out.println("CipherText: ");
		int kindex=0;
		while(kindex != key_l){
			int col = key[kindex] -1;
			for(int i = 0; i<rows; i++){
				System.out.print((char)rail[i][col]);
			}
			kindex++;
		}
		System.out.println();
	}
	public void decrypt(String s, int[] key, int key_l){
		int l=s.length();
		char a[]=s.toCharArray();
		System.out.print("Your decrypted text is: ");
		int cols = key_l, k = 0;
		char plain[][]=new char [20][20];
		int rows = l / cols;


		int m=0;
		while(k != cols){
			int s1 = key[k]-1;
			for(int i = 0; i<rows; i++){
				plain[i][s1] = a[m];
				m++;
			}
			k++;
		}

		for(int i = 0; i<rows; i++){
			for(int j = 0; j<cols; j++){
				System.out.print(plain[i][j]);
			}
		}
		System.out.println();
	}

	public static void main(String []args){
		RowTranspositionCipher rtc=new RowTranspositionCipher();
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Key size: ");
		int key_l=sc. nextInt();
		int key[]=new int[key_l];
		System.out.print("Enter Key : ");
		for(int i=0;i<key_l;i++)
			key[i]=sc.nextInt();

		while(true){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			int choice = sc.nextInt();
			String s;
			if(choice==1){
				sc.nextLine();
				System.out.print("Enter text to encrypt: ");
				s=sc.nextLine();
				s=s.replaceAll("\\s", "");
				rtc.encrypt(s,key,key_l);
			}
			else if(choice==2){
				System.out.print("Enter cipher to decrypt: ");
				s=sc.next();
				rtc.decrypt(s,key,key_l);
			}
			else{
				break;
			}
		} 
	}
}


/*

Enter Key size: 4
Enter Key : 2
3
4
1

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: hellothere
CipherText: 
etelhxlexhor

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: etelhxlexhor
Your decrypted text is: hellotherexx

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3

*/
