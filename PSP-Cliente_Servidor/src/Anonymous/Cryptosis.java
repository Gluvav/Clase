package Anonymous;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class Cryptosis {
    //
    public static void main(String[] args) throws Exception {
        //
        MessageDigest md = MessageDigest.getInstance("SHA");
        FileInputStream fis = new FileInputStream("C:\\Users\\7N\\Documents\\GitHub\\Clase\\PSP-Cliente_Servidor\\src\\Anonymous\\file.txt");

        byte[] dataBytes = new byte[1024];
        int nread = 0;
        System.out.println("File content: ");
        while ((nread = fis.read(dataBytes)) != -1){
            System.out.println(new String(dataBytes));
            md.update(dataBytes, 0, nread);

            byte[] mdbytes = md.digest();
            System.out.println("Number of bytes: " + md.getDigestLength());
            System.out.println("Algorithm: " + md.getAlgorithm());
            System.out.println("Text summary: " + new String(mdbytes));

            StringBuffer hexstring = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
                hexstring.append(Integer.toHexString(0xff & mdbytes[i]));
            }

            System.out.println("File in hexadecimal: " + hexstring.toString());
            Provider provider = md.getProvider();
            System.out.println("Provider: " + md.getProvider());

            fis.close();
        }
        //
    }
    //
}
