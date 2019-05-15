
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

    public static void main(String[] args) {
        HuffmanAlgorithm huffman = new HuffmanAlgorithm();
        int[] byteFreq = {
            13, 16, 45, 5, 9, 12 // 00 01 10 11 ...
        };

        HashMap<Integer, String> hm = huffman.Encrypt(byteFreq);
        Set<Integer> keys = hm.keySet();
        for (Integer key : keys) {
            System.out.println(key + " : " + hm.get(key));
        }
    }
}
