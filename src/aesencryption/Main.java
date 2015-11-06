package aesencryption;
//metodat qe mungojne mixcolumns addroundkey
import java.util.Scanner;
public class Main {
//SubBytes po punon
    public static void main(String[] args) {
        Aes AesInstance = new Aes();
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int length = inputString.length();
        String inputStringHex = ToHexString(inputString.getBytes());
        int [][]state = ReturnStateMatrix(inputStringHex,length);//futete state matrix ne subbytes
        AesInstance.subBytes(state);
        AesInstance.ShiftRows(state);

    }
private static String ToHexString(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<bytes.length; i++) {
        sb.append(String.format("%02X",bytes[i]));
    }
    return sb.toString();
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
