
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Stephanie
 */
public class HuffmanTest {

    public static void main(String[] args) throws Exception {
        HuffmanAlgorithm huffman = new HuffmanAlgorithm();
        int[] byteFreq = {
            13, 16, 45, 5, 9, 12 // 00 01 10 11 ...
        };

        HashMap<Integer, String> hm = huffman.Encrypt(byteFreq);
        Set<Integer> keys = hm.keySet();
        for (Integer key : keys) {
            System.out.println(key + " : " + hm.get(key));
        }
        FileOutputStream outFile = new FileOutputStream("test.huff");
        BitOutputStream out = new BitOutputStream(outFile);
        String code = "";
        for (int i = 0; i < byteFreq.length; i++) {
            code = hm.get(i);
            if (!code.equals("")) {                
                for (int c = 0; c < code.length(); c++) {
                    String sbit = code.substring(c, c+1);
                    int bit = Integer.parseInt(sbit);
                    System.out.println(code + " : sbit = "+sbit+" : bit = " + bit);
                    out.writeBit(bit);
                }
                System.out.println("---");
            }
        }
    }
}
