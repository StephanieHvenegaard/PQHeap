
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

    public Encode() {
    }

    public static void main(String[] args) throws Exception {
        String inName;  // String name for input file.
        String outName; // String name for output file.

        // Check for input or use default values for test.        
        if (args.length < 2) {
            outName = "TestFile.huff";
        } else {
            outName = args[1];
        }
        if (args.length < 1) {
            inName = "TestFile.txt";
        } else {
            inName = args[0];
        }

        // Make in and out streams.
        FileInputStream inFile = new FileInputStream(inName);
        FileOutputStream outFile = new FileOutputStream(outName);
        BitOutputStream out = new BitOutputStream(outFile);

        // initiate array
        int[] byteFreq = new int[256];

        int iByte;
        // increments the frequency.
        while ((iByte = inFile.read()) > -1) {
            byteFreq[iByte]++;
        }
        System.out.println("ended counting frequencies.");
        System.out.println("1/3 Succes");
        // Resetting the infile counter.
        inFile = new FileInputStream(inName);
        // making new instance of Huffman.
        HuffmanAlgorithm huffman = new HuffmanAlgorithm();
        // Enkrypting data getting a hash map of bytes and codes        
        Element element = huffman.Encrypt(byteFreq);
        HashMap<Integer, String> hm = huffman.getDataTable(element);

        // Creating index map of bytes and freqs
        for (int i = 0; i < byteFreq.length; i++) {
            out.writeInt(byteFreq[i]);
        }
        System.out.println("ended writing bytes and codes table.");
        System.out.println("2/3 Succes");
        // Writing file using huffman compression
        String code = "";
        while ((iByte = inFile.read()) > -1) {
            code = hm.get(iByte);
            if (!code.equals("")) {
                for (int c = 0; c < code.length(); c++) {
                    String sbit = code.substring(c, c + 1);
                    int bit = Integer.parseInt(sbit);
                    out.writeBit(bit);
                }
            }
        }
        System.out.println("ended huffman codes.");
        System.out.println("3/3 Succes");
        out.close();
        inFile.close();
    }
}
