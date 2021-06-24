/*
 
 Write a program to encrypt and decrypt messages using modified version of playfair cipher algorithm described as follows:
  Key is string without any repeation of letter as in playfair algorithm and k be a positive integer, 0<k<26. 
  Generate a key matrix using key string as in playfair algorithm. The perform encryption as given below:
Let ab be the plain text to be encrypted.
(i) If ab lie on the same column, then E(ab)=(y+k)%26 (z+k)%26, where y is a’s column
successor and z is b’s column successor
(ii) If ab lie on the same row, then E(ab)=(y+k)%26 (z+k)%26, where y is a’s row
successor and z is b’s row successor
(iii) Else, ab lie diagonally, then E(ab)=(y+k)%26 (z+k)%26, where y is intersection of
a’s row and b’s column and z is is intersection of b’s row and a’s column
 
 */

import java.util.Arrays;
import java.util.Scanner;

public class ModifiedPlayfairCipher {

	static String alphabets[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "l", "m", "n", "o", "p", "q",
			"r", "s", "t", "u", "v", "w", "x", "y", "z" };
	static String alphabets1[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
			"r", "s", "t", "u", "v", "w", "x", "y", "z" };
	static String[][] matrix = new String[6][6];
	public static boolean checkInArray(String[] arr, String element) {
		Boolean flag = false;
		for (String el : arr) {
			if (el.equals(element)) {
				flag = true;
			}
		}
		return flag;
	}
	public static String checkKey(String key) {
		String[] keyArray = key.split("");
		String[] sanitizedKey = new String[keyArray.length];
		Arrays.fill(sanitizedKey, "");
		String keyString = "";
		for (int i = 0; i < keyArray.length; i++) {
			if (!checkInArray(sanitizedKey, keyArray[i])) {
				if(keyArray[i] == "j") {
					keyArray[i] = "i";
				}
				sanitizedKey[i] = keyArray[i];
				keyString += keyArray[i];
			}
		}
		System.out.println(keyString);
		return keyString;
	}
	public static String findIndex(String s) {
		String pos = "";
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++) {
				if(matrix[i][j].equals(s)) {
					pos = Integer.toString(i).concat(Integer.toString(j));
				}
			}
		}
		return pos;
	}
	public static void generateMatrix(String key){
		String[] keyArray = key.split("");
		int keyIndex = 0, alphaIndex = 0;
		for (int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(keyIndex < keyArray.length) {
					matrix[i][j] = keyArray[keyIndex++];
				} else {
					if(!checkInArray(keyArray, alphabets[alphaIndex])){
						matrix[i][j] = alphabets[alphaIndex++];
					} else {
						alphaIndex++;
						if(j>0) {
							j--;
						} else if(j == 0) {
							if(i != 0 ){
								i--;
								j = 4;
							}
						}
					}
				}
			}
		}              
		for (int ii = 0; ii < 5; ii++) {
			for (int jj = 0; jj < 5; jj++) {
				System.out.print(matrix[ii][jj] + "      ");
				if(ii == 4){
					matrix[ii+1][jj] = matrix[0][jj];
				} else if(jj == 4){
					matrix[ii][jj+1] = matrix[ii][jj];
				}
			}
			System.out.println("");
		}
	}
	public static int getIndexOf(String item) {

		for (int i = 0; i < alphabets1.length; i++) {
			if (item.equals(alphabets1[i])) return i;
		}
		return -1;
	}
	public static void encrypt(String pt, String key, int k) {
		System.out.println("pt is "+pt);
		String[] ptArray = pt.split("");        
		String cipher = "";

		for(int i=0; i<ptArray.length-1; i++) {
			String pos1 = findIndex(ptArray[i]);
			String pos2 = findIndex(ptArray[i+1]);
			int[] posArr1 = { Integer.parseInt(Character.toString(pos1.charAt(0))), Integer.parseInt(Character.toString(pos1.charAt(1)))};
			int[] posArr2 = { Integer.parseInt(Character.toString(pos2.charAt(0))), Integer.parseInt(Character.toString(pos2.charAt(1)))};
			if(posArr1[0] == posArr2[0]) {
				cipher += alphabets1[((getIndexOf(matrix[posArr1[0]][(posArr1[1]+1)%5])+k)%26)];
				cipher += alphabets1[((getIndexOf(matrix[posArr2[0]][(posArr2[1]+1)%5])+k)%26)];
			} else if(posArr1[1] == posArr2[1]) {
				cipher += alphabets1[((getIndexOf(matrix[(posArr1[0]+1)%5][posArr1[1]])+k)%26)];
				cipher += alphabets1[((getIndexOf(matrix[(posArr2[0]+1)%5][posArr2[1]])+k)%26)];
			} else {
				cipher += alphabets1[((getIndexOf(matrix[posArr1[0]][posArr2[1]])+k)%26)];
				cipher += alphabets1[((getIndexOf(matrix[posArr2[0]][posArr1[1]])+k)%26)];

			}
			i++;
		}
		System.out.println("Cipher text: "+cipher);
	}

	public static void decrypt(String ct,int k) {
		System.out.println("ct is "+ct);
		String[] ctArray = ct.split("");        
		String plainText = "";
		for(int i=0; i<ctArray.length-1; i++) {
			String pos1 = findIndex(alphabets1[((getIndexOf(ctArray[i])-k)>0)?(getIndexOf(ctArray[i])-k):(getIndexOf(ctArray[i])-k+26)]);
			String pos2 = findIndex(alphabets1[((getIndexOf(ctArray[i+1])-k)>0)?(getIndexOf(ctArray[i+1])-k):(getIndexOf(ctArray[i+1])-k+26)]);
		
			int[] posArr1 = { Integer.parseInt(Character.toString(pos1.charAt(0))), Integer.parseInt(Character.toString(pos1.charAt(1)))};
			int[] posArr2 = { Integer.parseInt(Character.toString(pos2.charAt(0))), Integer.parseInt(Character.toString(pos2.charAt(1)))};
			if(posArr1[1] == posArr2[1]) {
				plainText += matrix[(posArr1[0]+4)%5][posArr1[1]];
				plainText += matrix[(posArr2[0]+4)%5][posArr2[1]];
			} else if(posArr1[0] == posArr2[0]) {
				plainText += matrix[posArr1[0]][(posArr1[1]+4)%5];
				plainText += matrix[posArr2[0]][(posArr2[1]+4)%5];
			} else {
				plainText += matrix[posArr1[0]][posArr2[1]];
				plainText += matrix[posArr2[0]][posArr1[1]];
			}
			i++;
		}
		System.out.println("plain text: "+plainText);
	}

	public static void main(String[] args) {
    
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the key");

		String key=sc.nextLine();
		System.out.println("Key is: "+key);
		System.out.println("Enter value of k");
		int k= sc.nextInt();
		int matrix[][] = new int[5][5];
		generateMatrix(key);
		int choice = 0;
		while (choice != 3) {
			System.out.println("\nEnter your choice\n1.Encryption\n2.Decryption\n3.Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the plaintext:");
				Scanner ptScanner = new Scanner(System.in);
				String pt = ptScanner.nextLine();
				StringBuffer newPt = new StringBuffer(pt);
				for(int i=0; i<pt.length()-1; i++){
					if(newPt.charAt(i)=='j') {
						newPt.replace(i, i+1, "i");
					}

				}
				for(int i=0; i<pt.length()-1; i++){
					if(newPt.charAt(i) == newPt.charAt(i+1)) {
						newPt.insert(i+1, 'x');
					}
					i++;
				}

				if(newPt.length()%2 != 0){
					newPt.append('x');
				}
				System.out.println("new pt = "+newPt);
				encrypt(newPt.toString(), key, k);
				break;
			case 2:
				System.out.println("Enter the cipher text:");
				Scanner ctScanner = new Scanner(System.in);
				String ct = ctScanner.nextLine();
				decrypt(ct,k);
				break;
			case 3:
				break;
			}
		}
	}
}




/*

Enter the key
monarchy
Key is: monarchy
Enter value of k
2
m      o      n      a      r      
c      h      y      b      d      
e      f      g      i      k      
l      p      q      s      t      
u      v      w      x      z      

Enter your choice
1.Encryption
2.Decryption
3.Exit
1
Enter the plaintext:
butterfly
new pt = butxterfly
pt is butxterfly
Cipher text: ezubnmqmse

Enter your choice
1.Encryption
2.Decryption
3.Exit
2
Enter the cipher text:
ezubnmqmse
ct is ezubnmqmse
plain text: butxterfly

Enter your choice
1.Encryption
2.Decryption
3.Exit
3

*/
