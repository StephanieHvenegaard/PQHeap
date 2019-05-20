
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class Decode {

    public static void main(String[] args) throws Exception {
        String inName;  // String name for input file.
        String outName; // String name for output file.

        // Check for input or use default values for test.        
        if (args.length < 2) {
            outName = "TestFile-Dehuff.txt";
        } else {
            outName = args[1];
        }
        if (args.length < 1) {
            inName = "TestFile.huff";
        } else {
            inName = args[0];
        }

        // Make in and out FileStreams.
        FileInputStream inFile = new FileInputStream(inName);
        FileOutputStream outFile = new FileOutputStream(outName);
        BitOutputStream out = new BitOutputStream(outFile);
        BitInputStream in = new BitInputStream(inFile);

        // initiate array
        int[] byteFreq = new int[256];

        int ifreq = -1;

        // reads freqs from first part of.
        for (int i = 0; i < byteFreq.length; i++) {
            ifreq = in.readInt();
            if (ifreq >= 0) {
                byteFreq[i] = ifreq;
            } else {
                throw new Exception("error at " + i);
            }
        }

        System.out.println(
                "ended counting frequencies.");
        System.out.println(
                "1/3 Succes");
        // Resetting the infile counter.
        inFile = new FileInputStream(inName);
        // making new instance of Huffman.
        HuffmanAlgorithm huffman = new HuffmanAlgorithm();
        // Enkrypting data getting a hash map of bytes and codes
        Element element = huffman.Encrypt(byteFreq);
        HashMap<String, Integer> hm = huffman.getCodeTable(element);

        System.out.println("ended writing bytes and codes table.");
        System.out.println("2/3 Succes");

        // Decoding file using huffman compression
        String code = "";
        int iByte = -1;
        int iBit;
        while ((iBit = in.readBit()) != -1) {
            code += iBit + "";
            if (hm.get(code) != null) {
                iByte = hm.get(code);
                if (!(iByte == -1)) {
                    outFile.write(iByte);
                    code = "";
                }
            }
        }

        System.out.println("ended huffman codes.");
        System.out.println("3/3 Succes");
        out.close();
        inFile.close();

    }

}
