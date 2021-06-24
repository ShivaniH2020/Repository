/*
Write a program to encrypt and decrypt messages using modified version of Hill cipher algorithm described as follows: 
Let K be a matrix of size m x n containing positive integers >0 and <26. Let X be a plain text matrix of same size i.e., m x n. 
The E(X) = X+K % 26. Eg., if 𝐾 = (𝑎 𝑏) 
	                               (𝑐 𝑑)
and message is computerscience, then plain text matrix is 𝑋 = ( 𝑐 𝑜 ) and  
												                                      ( 𝑚 𝑝 )
encryption of Xi s 𝐸(𝑋)= (𝑎+𝑐 𝑏+𝑜) 
				                 (𝑐+𝑚 𝑑+𝑝) %26 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ModifiedHillCipher
{
	static int keymatrix[][];
	int[][] linematrix;
	int resultmatrix[];

	public void divide(String temp, int s, char op)		//Divide plain-text into chunks according to given matrix dime
	{	int r=s;
	s=s*s;
	while (temp.length() > s)
	{
		String sub = temp.substring(0, s);
		temp = temp.substring(s, temp.length());
		perform(sub,r,op);
	}
	if (temp.length() == s)
		perform(temp,r,op);
	else if (temp.length() < s)
	{
		for (int i = temp.length(); i < s; i++)
			temp = temp + 'x';
		perform(temp,r,op);
	}
	}

	public void perform(String line,int r,char op)
	{
		linetomatrix(line,r);
		lineaddkey(line.length(),r,op);
		result(line.length());
	}

	public void linetomatrix(String line,int r)
	{

		linematrix = new int[r][r];
		int k=0;
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < r; j++)
			{
				linematrix[i][j] = ((int) line.charAt(k)) - 97;
				k++;
			}
		}

	}

	public void lineaddkey(int len, int r, char op)
	{
		resultmatrix = new int[len];
		if(op=='e') {
			int k=0;
			for (int i = 0; i < r; i++)
			{
				for (int j = 0; j < r; j++)
				{
					resultmatrix[k] = linematrix[i][j] + keymatrix[i][j];
					resultmatrix[k] %= 26;
					k++;
				}
			}
		}
		else
		{
			int k=0;
			for (int i = 0; i < r; i++)
			{
				for (int j = 0; j < r; j++)
				{
					resultmatrix[k] = linematrix[i][j] - keymatrix[i][j];
					if( resultmatrix[k]<0)
					{
						resultmatrix[k]+=26;
					}
					resultmatrix[k] %= 26;
					k++;
				}
			}
		}
	}

	public void result(int len)
	{
		String result = "";
		for (int i = 0; i < len; i++)
		{
			result += (char) (resultmatrix[i] + 97);
		}
		System.out.print(result);
	}




	public static void main(String args[]) throws IOException
	{
		Scanner sc= new Scanner(System.in);
		ModifiedHillCipher obj = new ModifiedHillCipher();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int choice;
		// System.out.println("Menu:\n1: Encryption\n2: Decryption");
		// choice = Integer.parseInt(in.readLine());

		int rows,cols;
		System.out.println("Enter the key dimensions: ");
		rows=sc.nextInt();
		cols=sc.nextInt();
		keymatrix = new int [rows][cols];
		if(rows!=cols)
		{
			System.out.println("Invalid key dimensions ");
		}
		else
		{
			System.out.println("Enter the key matrix ");
			for(int i=0;i<rows;i++)
			{
				for(int j=0;j<cols;j++)
				{
					keymatrix[i][j]=sc.nextInt();
				}
			}
		}

		int s = (int) rows;

		while(true){
			System.out.println("\nEnter your choice\n1:Encrypt\n2:Decrypt\n3:Exit");
			int ch = sc.nextInt();
			//String s;
			if(ch==1){
				System.out.println("Enter text to be encrypted: ");
				String line = in.readLine();
				System.out.println("Encrypted text: ");
				obj.divide(line, s,'e');
				System.out.println();
			}
			else if(ch==2){
				System.out.println("Enter text to be decrypted: ");
				String line = in.readLine();
				System.out.println("Decrypted text: ");
				obj.divide(line, s,'d');
				System.out.println();
			}
			else{
				break;
			}
		} 
	}
}




/*

Enter the key dimensions: 
2
2
Enter the key matrix 
12 27
36 5

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to be encrypted: 
computer
Encrypted text: 
opwuguow

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter text to be decrypted: 
opwuguow
Decrypted text: 
computer

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3



Enter the key dimensions: 
2
2
Enter the key matrix 
12 27 
36 5

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
1
Enter text to be encrypted: 
computerscience
Encrypted text: 
opwuguowedsjzdoc

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
2
Enter text to be decrypted: 
opwuguowedsjzdoc
Decrypted text: 
computersciencex

Enter your choice
1:Encrypt
2:Decrypt
3:Exit
3

*/
