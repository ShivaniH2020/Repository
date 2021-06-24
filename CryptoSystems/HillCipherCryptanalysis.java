/*
 
 This needs another file, named ModMatrix to run properly.
 To find key of size 3X3, you must know 9 consecutive plaintext-characters and correspoding 9 ciphertext-characters. 
 i.e. To find key of size nXn, you must know (n*n) consecutive plaintext-characters and correspoding (n*n) ciphertext-characters. 
 
 
 */

import java.math.BigInteger;
import java.util.Scanner;

public class HillCipherCryptanalysis 
{

	public static void main(String args[])
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the plaintext you know: ");
		String plaintext=input.nextLine().toLowerCase();
		System.out.println("Enter the corresponding ciphertext you know: ");
		String ciphertext=input.nextLine().toLowerCase();

		System.out.println("P: "+plaintext);
		System.out.println("C: "+ciphertext);

		System.out.println("Enter the size of key ");
		int key_n=input.nextInt();
		int key[][] = new int [key_n][key_n];
		double [][]mat = new double[key_n][key_n];
		double [][]constants = new double[key_n][1];

		for(int k=0; k<key_n; k++) 
		{
			int count=0;
			for(int i=0; i<key_n; i++)
			{
				for(int j=0; j<key_n; j++)
				{
					mat[i][j] = plaintext.charAt((i*key_n)+j)-97;
				}
				constants[i][0] = ciphertext.charAt(k+(count*key_n))-97;
				count++;
			}
			double getresult [][]= find_key(key_n, mat,constants);
			for(int k1=0;k1<key_n;k1++)
			{
				key[k][k1]=(int) getresult[k1][0];
			}
		}
		System.out.println("KEY is: ");
		for(int i=0;i<key_n;i++)
		{
			for(int j=0;j<key_n;j++)
			{
				System.out.print(key[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static double[][] find_key(int n,double mat[][],double constants[][]) 
	{ 
		//char []var = {'x', 'y', 'z', 'w'};

		double inverted_mat[][] = invert(mat,n);

		double result[][] = new double[n][1];
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < 1; j++) 
			{
				for (int k = 0; k < n; k++)
				{	 
					result[i][j] = result[i][j] + inverted_mat[i][k] * constants[k][j];
					result[i][j] = result[i][j] % 26;
				}
			}
		}
		return result;
	}

	public static double[][] invert(double a[][], int n) 
	{
		double x[][] = new double[n][n];
		BigInteger[][] key2 = new BigInteger[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				String temp= String.valueOf((int)a[i][j]);
				key2[i][j]=BigInteger.valueOf(Integer.valueOf(temp));

			}
		}

		ModMatrix obj2= new ModMatrix(key2);
		ModMatrix inverse2 = obj2.inverse(obj2);

		for (int i = 0; i < inverse2.getNrows(); i++) {
			for (int j = 0; j < inverse2.getNcols(); j++) {

				x[i][j]= inverse2.getData()[i][j].doubleValue();
			}
		}
		return x;
	}
}






/*

Enter the plaintext you know: 
dear
Enter the corresponding ciphertext you know: 
hmrz
P: dear
C: hmrz
Enter the size of key 
2
KEY is: 
1 1 
0 3 

*/
