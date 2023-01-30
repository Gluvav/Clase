package Anonymous;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class Cryptobro {

    public static void main(String[] args) throws Exception {

        String text = "I'm the content of a text.";
        System.out.println("Original text: " + text);

        MessageDigest md = MessageDigest.getInstance("SHA");

        String key  = "EcriptionKey";
        byte[] dataBytes = text.getBytes();
        md.update(dataBytes);
        byte[] sumary_and_key = md.digest(key.getBytes());

        md.update(text.getBytes());

        byte[] sumary = md.digest();

        System.out.println("Number of bytes: " + md.getDigestLength());
        System.out.println("Algorithm: " + md.getAlgorithm());
        System.out.println("Text sumary: " + sumary_and_key + new String(sumary));

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < sumary.length; i++) {
            String hex = Integer.toHexString(0xff & sumary[i]);
            if (hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }

        System.out.println("Text in Hexadecimal: " + hexString.toString());
        Provider provider = md.getProvider();
        System.out.println("Provider: " + provider.toString());

    }

}
