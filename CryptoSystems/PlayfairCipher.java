import java.util.Arrays;
import java.util.Scanner;

public class PlayfairCipher {

	static String alphabets[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "l", "m", "n", "o", "p", "q",
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
	public static void encrypt(String pt, String key) {
		System.out.println("pt is "+pt);
		String[] ptArray = pt.split("");        
		String cipher = "";

		for(int i=0; i<ptArray.length-1; i++) {
			String pos1 = findIndex(ptArray[i]);
			String pos2 = findIndex(ptArray[i+1]);
			int[] posArr1 = { Integer.parseInt(Character.toString(pos1.charAt(0))), Integer.parseInt(Character.toString(pos1.charAt(1)))};
			int[] posArr2 = { Integer.parseInt(Character.toString(pos2.charAt(0))), Integer.parseInt(Character.toString(pos2.charAt(1)))};
			if(posArr1[0] == posArr2[0]) {
				cipher += matrix[posArr1[0]][(posArr1[1]+1)%5];
				cipher += matrix[posArr2[0]][(posArr2[1]+1)%5];
			} else if(posArr1[1] == posArr2[1]) {
				cipher += matrix[(posArr1[0]+1)%5][posArr1[1]];
				cipher += matrix[(posArr2[0]+1)%5][posArr2[1]];
			} else {
				cipher += matrix[posArr1[0]][posArr2[1]];
				cipher += matrix[posArr2[0]][posArr1[1]];
			}
			i++;
		}
		System.out.println("Cipher text: "+cipher);
	}

	public static void decrypt(String ct) {
		System.out.println("ct is "+ct);
		String[] ctArray = ct.split("");        
		String plainText = "";
		for(int i=0; i<ctArray.length-1; i++) {
			String pos1 = findIndex(ctArray[i]);
			String pos2 = findIndex(ctArray[i+1]);
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
		// TODO Auto-generated method stub

		Scanner sc= new Scanner(System.in);
		System.out.println("Enter thye key");

		String key=sc.nextLine();
		System.out.println("Key is: "+key);
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
				encrypt(newPt.toString(), key);
				break;
			case 2:
				System.out.println("Enter the cipher text:");
				Scanner ctScanner = new Scanner(System.in);
				String ct = ctScanner.nextLine();
				decrypt(ct);
				break;
			case 3:
				break;
			}
		}
	}
}




/*

Enter thye key
monarchy
Key is: monarchy
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
Cipher text: cxszlkokqc

Enter your choice
1.Encryption
2.Decryption
3.Exit
2
Enter the cipher text:
cxszlkokqc
ct is cxszlkokqc
plain text: butxterfly

Enter your choice
1.Encryption
2.Decryption
3.Exit
1
Enter the plaintext:
instruments
new pt = instrumentsx
pt is instrumentsx
Cipher text: gatlmzclrqxa

Enter your choice
1.Encryption
2.Decryption
3.Exit
2
Enter the cipher text:
gatlmzclrqxa
ct is gatlmzclrqxa
plain text: instrumentsx

Enter your choice
1.Encryption
2.Decryption
3.Exit
3

*/
