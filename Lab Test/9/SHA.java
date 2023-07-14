import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA{
    public static void main(String[] args){
        String input = "Alamin";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] mb = md.digest(input.getBytes());
            BigInteger bi = new BigInteger(1, mb);

            System.out.println("Sign number: " + bi);

            String hashText = bi.toString(16);
            System.out.println("HashText: " + hashText);

        }
        catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
        
    }
}
