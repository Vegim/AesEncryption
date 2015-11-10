package aesencryption;
import java.util.Scanner;
import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        int [][]stateshembull = {{0x19,0xa0,0x9a,0xe9},
                {0x3d,0xf4,0xc6,0xf8},
                {0xe3,0xe2,0x8d,0x48},
                {0xbe,0x2b,0x2a,0x08}};
         int [][]celsesishembull = {{0xa0,0x88,0x23,0x2a},
                {0xfa,0x54,0xa3,0x6c},
                {0xfe,0x2c,0x39,0x76},
                {0x17,0xb1,0x39,0x05}};
        Aes AesInstance = new Aes();
       
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jepni tekstin!");
        String hyrjaString = scanner.nextLine();
        System.out.println("Jepni celesin!");
        String CelesiString = scanner.nextLine();
        String HyrjaStringHex = ToHexString(hyrjaString.getBytes());
        String CelesiStringHex = ToHexString(CelesiString.getBytes());
        
        int gjatesiahyrja = hyrjaString.length();
        int gjatesiacelesi = CelesiString.length();
        int [][]MatricaState = KtheMatricenHex(HyrjaStringHex,gjatesiahyrja);//futete state matrix ne subbytes
        int [][] MatricaCelesi = KtheMatricenHex(CelesiStringHex,gjatesiacelesi);
        
        AesInstance.subBytes(MatricaState);
        AesInstance.ShiftRows(MatricaState);
        AesInstance.MixColumns(MatricaState);
        AesInstance.AddRoundKey(MatricaState, MatricaCelesi);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(Integer.toHexString(MatricaState[i][j])+" ");
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
 public static int[][] KtheMatricenHex(String HexString, int inputlength) {
        int[][] state = new int[4][4];
        int k = 0, m = -2, c = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                k += 2;
                m += 2;
                c++;
                state[j][i] = Integer.parseInt(HexString.substring(m, k), 16);
                if (c == inputlength) {
                    return state;
                }

            }
        }
        return state;
    }

}
