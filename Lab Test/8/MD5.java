import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5{
    public static void main(String[] args) throws NoSuchAlgorithmException{
        String input = "Alamin";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] mb = md.digest(input.getBytes());
            BigInteger signum = new BigInteger(1, mb);

            System.out.println("Sig Num: "+signum);
            String hash = signum.toString(16);
            while(hash.length()<32){
                hash = "0" + hash;
            }
            System.out.println("Hash : "+ hash);
        }
        catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}