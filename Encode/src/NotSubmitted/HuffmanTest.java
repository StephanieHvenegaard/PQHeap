//package NotSubmitted;
//
//
//import java.io.FileOutputStream;
//import java.util.HashMap;
//import java.util.Set;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author Stephanie
// */
//public class HuffmanTest {
//
//    public static void main(String[] args) throws Exception {
//        HuffmanAlgorithm huffman = new HuffmanAlgorithm();
//        int[] byteFreq = {
//            13,  // 0000 0000  
//            16,  // 0000 0001
//            45,  // 0000 0010
//            5,   // 0000 0011
//            9,   // 0000 0100
//            12,  // 0000 0101
//            50   // 0000 0110
//            
//        };
//
//        HashMap<Integer, String> hm = huffman.Encrypt(byteFreq);
//        Set<Integer> keys = hm.keySet();
//        for (Integer key : keys) {
//            System.out.println(key + " : " + hm.get(key));
//        }
//        
//        System.out.println("---");
//        
//        FileOutputStream outFile = new FileOutputStream("test.huff");
//        BitOutputStream out = new BitOutputStream(outFile);
//        
//       
//        for (int i = 0; i < byteFreq.length; i++) {
//            out.writeByte(i);            
//            out.writeByte(byteFreq[i]);
//        }
//        
//        String code = "";
//        for (int i = 0; i < byteFreq.length; i++) {
//            code = hm.get(byteFreq[i]);
//            if (!code.equals("")) {                
//                for (int c = 0; c < code.length(); c++) {
//                    String sbit = code.substring(c, c+1);
//                    int bit = Integer.parseInt(sbit);
//                    System.out.println(code + " : sbit = "+sbit+" : bit = " + bit);
//                    out.writeBit(bit);
//                }
//                //System.out.println("---");
//            }
//        }                  
//        out.close();
//    }
//}
