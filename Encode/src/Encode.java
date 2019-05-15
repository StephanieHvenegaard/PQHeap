
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;





/**
 *
 * @author Simon Nyborg Jensen sije817@student.sdu.dk
 * @author Joakim Sjurman Larsen joala09@student.sdu.dk
 * @author Stephanie Rømer Hvenegaard sthve16@student.sdu.dk
 */
public class Encode {
    
    public Encode(){
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream inFile = new FileInputStream(args[0]);
        //FileOutputStream outFile = new FileOutputStream(args[1]);        
        int[] byteFreq = new int[256];
       
        int i = inFile.read();
        byteFreq[i]++;
        while((i = inFile.read())>-1){
            byteFreq[i]++;
        }
        System.out.println("ended");
        inFile = new FileInputStream(args[0]);
        
        HuffmanAlgorithm huffman = new HuffmanAlgorithm();
        
        HashMap<Integer,String> hm= huffman.Encrypt(byteFreq);     
        
        
    }    
}
