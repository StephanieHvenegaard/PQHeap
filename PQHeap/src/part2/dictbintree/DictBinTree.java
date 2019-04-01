/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2.dictbintree;

import java.util.ArrayList;

/**
 * @author Stephanie sthve16@student.sdu.dk
 * @author Simon sije817@student.sdu.dk
 * @author Joakim joala09@student.sdu.dk
 */
public class DictBinTree implements Dict {

    //HashMap<Integer, Integer> Tree = new HashMap<>();
    private Node root;                  // root node
    int treesize = 0;                   // number of nodes in tree
    int tranversialIndex = 0;           // current index used in the Ordered transverse method for a accurate transverse

    /**
     * Constructor sets root element to null;
     */
    public DictBinTree() {
        root = null;
    }

    /**
     * create a new node with data k, finds the correct location to insert the
     * node.
     *
     * @param k data key for the node
     */
    @Override
    public void insert(int k) {
        Node z = new Node(k);
        Node y = null;                                                          // modsvare Pseudo linje 1       
        Node x = root;                                                          // modsvare Pseudo linje 2 x=T.root;       
        while (x != null) // modsvare Pseudo linje 3;       
        {
            y = x;                                                              // modsvare Pseudo linje 4      
            if (z.key < x.key) // modsvare Pseudo linje 5
            {
                x = x.left;                                                     // modsvare Pseudo linje 6
            } else //modsvare Pseudo linje 7
            {
                x = x.right;                                                    //modsvare Pseudo linje 7
            }
        }
        //z.parrent = y;                      // not needded                    //modsvare Pseudo linje 8
        if (y == null) //modsvare Pseudo linje 9
        {
            root = z;                         // tree T was empty               //modsvare Pseudo linje 10
        } else if (z.key < y.key) //modsvare Pseudo linje 11               
        {
            y.left = z;                                                         //modsvare Pseudo linje 12                                          
        } else {
            y.right = z;
        }                                                                       //modsvare Pseudo linje 13
        treesize++;                         // increments the treesize now that a node has been added. 
    }

    /**
     * starts the ordered tranversial
     *
     * @return a array of the data keys in order from smallest to largest.
     */
    @Override
    public int[] orderedTraversal() {
        // first try did not work
        int[] returnedArray = new int[treesize];
        //orderedTreeWalk(returnedArray, 0, root);
        tranversialIndex = 0;
        // not the right way. need fix
        ArrayList<Integer> a = new ArrayList<>();

        orderedTreeWalk(returnedArray, root);
//        for (int i = 0; i < a.size();i++)
//        {
//            returnedArray[i]= a.get(i);
//        }
        return returnedArray;

    }

    /**
     * alternative test method, turnes out to be unnessasary and to slow
     *
     * @param a list of returned data
     * @param x current assed node.
     */
    private void ALTorderedTreeWalk(ArrayList<Integer> a, Node x) {
        // if this is wrong please show a better method.
        if (x != null) //1 if x != NIL
        {
            ALTorderedTreeWalk(a, x.left);      //2 INORDER-TREE-WALK(.x: left)/
            a.add(x.key);                       //3 print x:key       
            ALTorderedTreeWalk(a, x.right);     //4 INORDER-TREE-WALK.(x:right)/
        }
    }

    /**
     * recusive walk through the nodes and finds all the data.
     *
     * @param a array, of returned data
     * @param x node to asses
     */
    private void orderedTreeWalk(int[] a, Node x) {
        
        if (x != null) //1 if x != NIL
        {
            orderedTreeWalk(a, x.left);      //2 INORDER-TREE-WALK(.x: left)/
            a[tranversialIndex] = x.key;     //3 print x:key
            tranversialIndex++;              // incrememnt the index for the array 
            orderedTreeWalk(a, x.right);     //4 INORDER-TREE-WALK.(x:right)/
        }
    }
    /**
     * searches for key data k in the tree returns true if found and false if not 
     * @param k data key to look up
     * @return result
     */
    @Override
    public boolean search(int k) {
        return search(root, k) != null;         // return true if node is found.
    }
/**
 * rcursive search that traverses every node to se if the node with k as key can be found 
 * it goes from left to right through the tree.
 * @param x node to asses
 * @param k key to look for.
 * @return 
 */
    private Node search(Node x, int k) {
        if (x == null || k == x.key)            //1 if x == NIL or k == x:key
        {
            return x;                           //2 return x
        }
        if (k < x.key)                          //3 if k < x:key
        {
            return search(x.left, k);           //4 return TREE-SEARCH.x: left; k/
        } else {
            return search(x.right, k);          //5 else return TREE-SEARCH.x:right; k/
        }
    }

/**
 * data object to use in the tree it stores a key and the next left and right node.
 */
    class Node {

        int key;
        Node left, right;

        // constructor 
        Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }
}
