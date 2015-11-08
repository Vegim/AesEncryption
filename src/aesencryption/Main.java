package aesencryption;
//metodat qe mungojne mixcolumns addroundkey
import java.util.Scanner;
import java.math.BigInteger;
public class Main {
//SubBytes po punon
    public static void main(String[] args) {
        int [][]matrix = {{0x19,0xa0,0x9a,0xe9},
                {0x3d,0xf4,0xc6,0xf8},
                {0xe3,0xe2,0x8d,0x48},
                {0xbe,0x2b,0x2a,0x08}};
         int [][]keyexample = {{0xa0,0x88,0x23,0x2a},
                {0xfa,0x54,0xa3,0x6c},
                {0xfe,0x2c,0x39,0x76},
                {0x17,0xb1,0x39,0x05}};
        Aes AesInstance = new Aes();
       
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jepni tekstin!");
        String inputString = scanner.nextLine();
        System.out.println("Jepni celesin!");
        String Key = scanner.nextLine();
        String inputStringHex = ToHexString(inputString.getBytes());
        String inputStringKey = ToHexString(Key.getBytes());
        
        int length = inputStringHex.length()/2;
        int lengthkey = inputStringHex.length()/2;
        int [][]state = ReturnStateMatrix(inputStringHex,length);//futete state matrix ne subbytes
        int [][] key = ReturnStateMatrix(inputStringKey,lengthkey);
        
        AesInstance.subBytes(matrix);
        AesInstance.ShiftRows(matrix);
        AesInstance.MixColumns(matrix);
        AesInstance.AddRoundKey(matrix, keyexample);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(Integer.toHexString(matrix[j][i]));
            }
        }
        System.out.println("Shkrimi i enkriptuar eshte "+sb.toString());


    }
private static String ToHexString(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<bytes.length; i++) {
        sb.append(String.format("%02X",bytes[i]));
    }
    return sb.toString();
}
public static String toHex(String arg) {
    return String.format("%02X", new BigInteger(1,arg.getBytes()));
}
public static int[][] ReturnStateMatrix (String HexString,int inputlength)
{
     int[][] state = new int[4][4];
        int k=0,m=-2,c=0;
        for (int i = 0; i < 4; i++) 
        {
            for (int j = 0; j < 4; j++) {
                k+=2;m+=2;c++;                
                state[j][i] = Integer.parseInt(HexString.substring(m,k), 16);
                   if(c==inputlength)
                {
                    return state;
                }
              
            }
        }
        return state;
}

}
