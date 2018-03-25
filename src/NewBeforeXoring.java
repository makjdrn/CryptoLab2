import java.util.regex.Pattern;


public class NewBeforeXoring {
    public static Cryptograms cg = new Cryptograms();; 
    public static Xoring xoring = new Xoring();
    public static String[] Cryptogram = new String [19];
    public static String CipherToDecrypt = "10001001 00111010 01010011 11011000 11010101 01100010 11010100 11011000 00100110 11110100 00110101 10110000 00101011 10101000 00110111 00111100 11111101 00101001 01010000 10001100 10011111 10100010 10111111 00111110 01010111 10101010 01010000 01111111 01101110 11110001 11010010 00011010 11101110 00101010 01101000 11101011 00010010 10001001 00100001 01010001 01100000 11011000 01011011 10110110 01101100 01011010 01000110 11101110 00011101 10011110 10110111 01011001 11000000";
	public static void main(String[] args) {

        
        CipherToDecrypt = CipherToDecrypt.replaceAll("\\s+", "");
        String[][] AfterXor = new String [20][20];

        for(int q = 0; q < 19; q++) {
            Cryptogram[q] = cg.Cryptograms(q);
            Cryptogram[q] = Cryptogram[q].replaceAll("\\s+", "");
        }

        for(int j = 0; j < 19; j++) {
            for (int i = j+1; i < 19; i++) {

                if(Cryptogram[j].length() > Cryptogram[i].length())
                    Cryptogram[j] = Cryptogram[j].substring(0, Cryptogram[i].length());
                else
                    Cryptogram[i] = Cryptogram[i].substring(0, Cryptogram[j].length());
                AfterXor[j][i] = xoring.Xoring(Cryptogram[j], Cryptogram[i]);
            }
        }
        char s;
        boolean[] taken = new boolean[100000];
        String[] FinalKey = new String[100000];
        for(int ii = 0; ii < 19; ii++) {
            for(int jj = ii+1; jj < 19; jj++) {
            	for(int k = 0; k < Cryptogram[ii].length()/8; k++) {
            		s = AfterXor[ii][jj].charAt(k);
            		if((s >= 65 && s <= 90) || (s >= 97 && s <= 122)) {
            			if(!taken[k]) {
            				taken[k] = true;
            				FinalKey[k] = FindKey(Cryptogram[ii], Cryptogram[jj], k, "");
            			}
            		}
            	}
            }
        }
        /*FinalKey[0] = "11010101";
        FinalKey[7] = "10111101";
        FinalKey[34] = "11000000";
        FinalKey[37] = "10101010";
        FinalKey[39] = "11111110";
        FinalKey[16] = BinaryXor(CipherToDecrypt, "01000010", 16);
        FinalKey[23] = BinaryXor(CipherToDecrypt, "01001111", 23);
        FinalKey[54] = BinaryXor(CipherToDecrypt, "01110111", 54);
        FinalKey[56] = BinaryXor(CipherToDecrypt, "01100101", 56);
        FinalKey[57] = BinaryXor(CipherToDecrypt, "01100100", 57);*/
        FinalKey[1] = BinaryXor(CipherToDecrypt, "01000101", 1);
        FinalKey[6] = BinaryXor(CipherToDecrypt, "01001001", 6);
        FinalKey[10] = BinaryXor(CipherToDecrypt, "01101111", 10);
        FinalKey[35] = BinaryXor(CipherToDecrypt, "01010010", 35);
        FinalKey[39] = BinaryXor(CipherToDecrypt, "01001001", 39);
        FinalKey[42] = BinaryXor(CipherToDecrypt, "01001000", 42);
        FinalKey[45] = BinaryXor(CipherToDecrypt, "01101001", 45);
        FinalKey[47] = BinaryXor(CipherToDecrypt, "00100000", 47);
        FinalKey[49] = BinaryXor(CipherToDecrypt, "01101001", 49);
        FinalKey[51] = BinaryXor(CipherToDecrypt, "01100101", 51);
        DecryptMessage(FinalKey);
	}

	private static String FindKey(String Cryptogram1, String Cryptogram2, int k, String result) {
		// TODO Auto-generated method stub
        String key;
        String key2;

        String space = "00100000";
        key = BinaryXor(Cryptogram1, space, k);
        key2 = BinaryXor(Cryptogram2, space, k);
        
        String newkey, newkey2;
        char s1, s2;
        for(int i = 0; i < 19; i++) {
        	if(k* 8 < Cryptogram[i].length()) {
        	newkey = BinaryXor(Cryptogram[i], key, k);
        	newkey2 = BinaryXor(Cryptogram[i], key2, k);
        	s1 = xoring.toAscii(newkey).charAt(0);
        	s2 = xoring.toAscii(newkey2).charAt(0);
        	if(((s1 >= 65 && s1 <= 90) || (s1 >= 97 && s1 <= 122) || s1 == 32 || s1 == 44 || s1 == 46) && !((s2 >= 65 && s2 <= 90) || (s2 >= 97 && s2 <= 122) || s2 == 32 || s2 == 44 || s2 == 46))
        		result = key;
        	else if(!((s1 >= 65 && s1 <= 90) || (s1 >= 97 && s1 <= 122) || s1 == 32 || s1 == 44 || s1 == 46) && ((s2 >= 65 && s2 <= 90) || (s2 >= 97 && s2 <= 122) || s2 == 32 || s2 == 44 || s2 == 46))
        		result = key2;
        	}
        }
		return result;
	}

	private static void DecryptMessage(String[] finalKey) {
		// TODO Auto-generated method stub
		String value = "";
		String message = "";
		String s = "";
			for(int j = 0; j < CipherToDecrypt.length()/8; j++) {
				if(finalKey[j] == null)
					finalKey[j] = "";
	        	if(finalKey[j].length() == 8) {
	        		value = BinaryXor(CipherToDecrypt, finalKey[j], j);
				    //System.out.print("AScii = " + xoring.toAscii(value));
				    message = xoring.toAscii(value);
				    s = s + message;
	        	}
			}
			System.out.println("Decrypted Cipher = "+ s);
			//s ="";
	}

	private static String BinaryXor(String cryptogram1, String space, int k) {
		// TODO Auto-generated method stub
        String key;
        StringBuilder stb = new StringBuilder();
        for(int l = 0; l < 8; l++)
            stb.append(cryptogram1.charAt(l + (k * 8)) ^ space.charAt(l));
        key = stb.toString();
        return key;
	}
}
