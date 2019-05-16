
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class Decode {

    public static void main(String[] args) throws Exception {
        String inName;  // String name for input file.
        String outName; // String name for output file.

        // Check for input or use default values for test.        
        if (args.length < 2) {
            outName = "dehuff.text";
        } else {
            outName = args[1];
        }
        if (args.length < 1) {
            inName = "Test.huff";
        } else {
            inName = args[0];
        }

        // Make in and out streams.
        FileInputStream inFile = new FileInputStream(inName);
        FileOutputStream outFile = new FileOutputStream(outName);
        BitOutputStream out = new BitOutputStream(outFile);
        BitInputStream in = new BitInputStream(inFile);

        // initiate array
        int[] byteFreq = new int[256];

        int iByte = -1;
        int ifreq = -1;

        // reads freqs back.
        for (int i = 0; i < byteFreq.length; i++) {
            iByte = inFile.read();
            ifreq = inFile.read();
            if (ifreq >= 0) {
                if (ifreq <= 256) {
                    byteFreq[iByte] = ifreq;
                } else {
                    // for making it easyer to debug we put a limint of one bytes worth of frequenzies. 
                    // this substancially decreased the outfile size and made it easyer to read the binary data for testing
                    throw new Exception("File is to big, exeted 255 occuranses for bytes. dum dum, di dum dum dum... dum");
                }
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
        HashMap<Integer, String> hm = huffman.Encrypt(byteFreq);

        System.out.println(
                "ended writing bytes and codes table.");
        System.out.println(
                "2/3 Succes");
        // Writing file using huffman compression

        String code = "";
        String sByte = "";
        int iBit;        
        while ((iBit = in.readBit()) != -1) {           
            code += iBit + "";
            sByte = hm.get(code);
            if (!(sByte == null)) {
                iByte = Integer.parseInt(sByte);
                outFile.write(iByte);
                code = "";
            }
        }

        System.out.println("ended huffman codes.");
        System.out.println("3/3 Succes");
        out.close();
        inFile.close();

    }

}
