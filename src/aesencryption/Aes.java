package aesencryption;
//algoritmi
public class Aes {
    int [][] galoismatrix = 
       {{0x02, 0x03, 0x01, 0x01},
        {0x01, 0x02, 0x03, 0x01},
        {0x01, 0x01, 0x02, 0x03},
        {0x03, 0x01, 0x01, 0x02}};
    int[][] sbox = {{0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
        {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
        {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
        {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
        {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
        {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
        {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
        {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
        {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
        {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
        {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
        {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
        {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
        {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
        {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
        {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};

    public void subBytes(int[][] MatricaState) {//Kthen
        for (int i = 0; i < MatricaState.length; i++) 
        {
            for (int j = 0; j < MatricaState[0].length; j++) {
                int hex = MatricaState[i][j];
                
                String HexStringu = Integer.toHexString(hex);
                if(HexStringu.length()==1)
                {
                   HexStringu = "0"+HexStringu;
                }               
                String x1 = HexStringu.substring(0, 1);              
                String x2 = HexStringu.substring(1, 2);
                int rreshti =Integer.parseInt(x1,16);
                int kolona = Integer.parseInt(x2,16);
                MatricaState[i][j] = sbox[rreshti][kolona];
            }
        }
    }
    public void ShiftRows (int [][] MatricaState)
    {
        for(int i=1;i<MatricaState.length;i++)
        {
            MatricaState[i] = zhvendosemajtas(MatricaState[i],i);
        }
    }
    public int []  zhvendosemajtas(int []rreshtistate,int zhvendosjet)
    {      
        while(zhvendosjet>0)
        {
             int temp = rreshtistate[0];
             for(int i=0;i<rreshtistate.length-1;i++)
             {
                 rreshtistate[i] = rreshtistate[i+1];
             }
             rreshtistate[rreshtistate.length-1]= temp;
             zhvendosjet--;
        }
        return rreshtistate;
    }
    public void MixColumns (int [][] MatricaState)
    {
        int[] kolona1 = new int[4];
        int[] kolona2 = new int[4];
        int[] kolona3 = new int[4];
        int[] kolona4 = new int[4];
        for(int i=0;i<4;i++)
        {
            kolona1[i] = MatricaState[i][0];
        }
        for(int i=0;i<4;i++)
        {
            kolona2[i] = MatricaState[i][1];
        }
         for(int i=0;i<4;i++)
        {
            kolona3[i] = MatricaState[i][2];
        }
        for(int i=0;i<4;i++)
        {
            kolona4[i] = MatricaState[i][3];
        }
       
            ShumezoMatricenGaloisMeDy(kolona1);
            ShumezoMatricenGaloisMeDy(kolona2);
            ShumezoMatricenGaloisMeDy(kolona3);
            ShumezoMatricenGaloisMeDy(kolona4);
           int[] array1and4 = new int[kolona1.length + kolona2.length+kolona3.length+kolona4.length];
           System.arraycopy(kolona1, 0, array1and4, 0, kolona2.length);
           System.arraycopy(kolona2, 0, array1and4, kolona1.length, kolona2.length);
           System.arraycopy(kolona3, 0, array1and4, kolona1.length+kolona2.length, kolona3.length);
           System.arraycopy(kolona4, 0, array1and4, kolona1.length+kolona2.length+kolona3.length, kolona4.length);
           
            int k=0;
        for(int i=0;i<4;i++)
        {
            for (int j = 0; j < 4; j++) {
                MatricaState[j][i] = array1and4[k];
                k++;

            }
        }
       
    }
    public void AddRoundKey(int [][] MatricaState,int[][]Celesi)
    {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                MatricaState[j][i] = MatricaState[j][i]^Celesi[j][i];
            }
        }
    }
    public void ShumezoMatricenGaloisMeDy (int  [] kolona)
    {
                   int [] kolonakopje = new int [4];
                   System.arraycopy(kolona, 0, kolonakopje, 0, kolonakopje.length);

        for (int i = 0; i < 4; i++) {
            int result=0;
            for (int j = 0; j < 4; j++) {
                result ^= ShumezimiModulo((byte)galoismatrix[i][j],(byte)kolonakopje[j]); 
            }
            kolona[i] =result; 
        }
    }
    
    public int ShumezimiModulo (byte a,byte b)
    {       
       if(a==1)
       {
           return b&0xFF;
       }
       if(a==2)
       {
          return ShumezimiModuloMeDy(b);        
         
       }
       if(a==3)
       {
           int Mul3 = (ShumezimiModuloMeDy(b) ^ b)&0xFF;// shumezimi me tre shumezimi me 2 xor me veten
           return Mul3;
       }
       return 0;
    
    }

      public int ShumezimiModuloMeDy( byte b)
      {
            if((b&0x80)!=0)
           {
               b<<=1;
               b^=(0x1b) ;
               return b&0xFF ;
           }
           else {
               b<<=1;
               b^=(0x00)&0xFF;
               return b &0xFF; 
           }
      }

}


