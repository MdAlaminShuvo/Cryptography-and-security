import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Polygram {
    private Map<String, String> substitutionMap;

    public Polygram(Map<String, String> substitutionMap){
        this.substitutionMap = substitutionMap;
    }

    public String encrypt(String plaintext){
        String ciphertext = "";
        int PolygramSize = 2;
        for (int i=0; i<plaintext.length(); i += PolygramSize ){
            if(i + PolygramSize <= plaintext.length()){
                String polygram = plaintext.substring(i, i + PolygramSize);
                String substitution = substitutionMap.get(polygram);
                if(substitution != null){
                    ciphertext += substitution;
                } else{
                    ciphertext += polygram;
                }

            } else{
                ciphertext += plaintext.substring(i);

            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String plaintext){
        String ciphertext = "";
        int PolygramSize = 2;
        for( int i=0; i<plaintext.length(); i+= PolygramSize){
            if(i+PolygramSize <= plaintext.length()){
                String polygram = plaintext.substring(i, i + PolygramSize);
                String substitution = null;
                for(Map.Entry<String, String> entry : substitutionMap.entrySet()){
                    if(entry.getValue().equals(polygram)){
                        substitution = entry.getKey();
                        break;
                    }
                }
                if(substitution != null){
                    ciphertext += substitution;
                } else{
                    ciphertext += polygram;
                }
            } else{
                ciphertext += plaintext.substring(i);
            }
        }
        return ciphertext.toString();
    }

    
    public static void main(String[] args){
        Map<String, String> substitutionMap = new HashMap<>();
        substitutionMap.put("AB", "xY");
        substitutionMap.put("CD", "WZ");
        substitutionMap.put("MN", "TR");
        substitutionMap.put("ST", "OP");

        Polygram cipher = new Polygram(substitutionMap);

        String result;
        String plaintext = readFile("./plaintext.txt");
        result = cipher.encrypt(plaintext);
        writeFile("./chipertext.txt", result);
        result = cipher.decrypt(result);
        writeFile("./recovered.txt", result);
    }

    public static String readFile(String filename){
        String content = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine()) != null){
                content += line;
            }
        } catch(IOException e){
            System.out.println("content not found");
        }
        return content.toString();

    }
    public static void writeFile(String filename, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename) )){
            writer.write(content);
        } catch(IOException e){
            System.out.print("File not found");
        }
    }
    
}
