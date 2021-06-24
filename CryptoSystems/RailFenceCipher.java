import java.util.ArrayList;
import java.util.Scanner;

class RailFenceCipher{

	public static int findIndex(int arr[], int t)
	{
		ArrayList<Integer> clist = new ArrayList<>();
		for (int i : arr)
			clist.add(i);
		return clist.indexOf(t);
	}

	public void encrypt(String s, int key){
		int l=s.length();
		int col;
		if(l%key==0)
			col=(l/key);
		else
			col=(l/key)+1;
		for(int i=l;i<key*col;i++)
		{
			s=s+'x';
		}
		char arr[]=s.toCharArray();
		int rail[][]=new int[key][col];
		int k=0;
		for(int i=0;i<col;i++)
		{
			for(int j=0;j<key;j++)
			{
				rail[j][i]=arr[k++];
			}
		}
		System.out.println("CipherText: ");
		for(int i=0;i<key;i++)
		{
			for(int j=0;j<col;j++)
			{
				System.out.print((char)rail[i][j]);
			}
		}
		System.out.println();
	}

	public void decrypt(String s, int key){
		int l=s.length();
		char a[]=s.toCharArray();
		System.out.print("Your decrypted text is: ");
		int row = key, column, k = 0;
		char plain[][]=new char [20][20];
		column = l/key;
		for(int i = 0; i < row; i++){
			for(int j = 0; j < column; j++){
				plain[i][j] = a[k];
				k++;
			}
		}
		for(int j = 0; j < column; j++){
			for(int i = 0; i < row; i++){
				System.out.print(plain[i][j]);
			}
		}
		System.out.println();
	}

	public static void main(String []args){
		RailFenceCipher rfc=new RailFenceCipher();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Key: ");
		int key=sc.nextInt();

		while(true){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			int choice = sc.nextInt();
			String s;
			if(choice==1){
				sc.nextLine();
				System.out.print("Enter text to encrypt: ");
				s=sc.nextLine();
				s=s.replaceAll("\\s", "");
				rfc.encrypt(s,key);
			}
			else if(choice==2){
				System.out.print("Enter cipher to decrypt: ");
				s=sc.next();
				rfc.decrypt(s,key);
			}
			else{
				break;
			}

		} 

	}
}



/*

Enter Key: 3

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to encrypt: Hello There
CipherText: 
HlheeoexlTrx

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter cipher to decrypt: HlheeoexlTrx
Your decrypted text is: HelloTherexx

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3

*/
