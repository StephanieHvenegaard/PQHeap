/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2.dictbintree;

import java.util.ArrayList;

/**
 *
 * @author Stephanie
 */
public class DictBinTree implements Dict {

    //HashMap<Integer, Integer> Tree = new HashMap<>();
    private Node root;
    int treesize = 0;

    public DictBinTree() {
        root = null;
    }

    @Override
    public void insert(int k) {
        Node z = new Node(k);
        Node y = null;                                                          // modsvare Sudo linje 1       
        Node x = root;                                                          // modsvare Sudo linje 2 x=T.root;       
        while (x != null) // modsvare Sudo linje 3;       
        {
            y = x;                                                              // modsvare Sudo linje 4      
            if (z.key < x.key) // modsvare Sudo linje 5
            {
                x = x.left;                                             // modsvare Sudo linje 6
            } else //modsvare Sudo linje 7
            {
                x = x.right;                                            //modsvare Sudo linje 7
            }
        }
        //z.parrent = y;                                                      //modsvare Sudo linje 8
        if (y == null) //modsvare Sudo linje 9
        {
            root = z;                  // tree T was empty               //modsvare Sudo linje 10
        }
        else if (z.key < y.key) //modsvare Sudo linje 11               
        {
            y.left = z;                                                  //modsvare Sudo linje 12                                          
        } 
        else {
            y.right = z;
        }                                             //modsvare Sudo linje 13
        treesize++;
    }

    @Override
    public int[] orderedTraversal() {
        // first try did not work
        int[] returnedArray = new int[treesize];
        //orderedTreeWalk(returnedArray, 0, root);
        
        // not the right way. need fix
        ArrayList<Integer> a = new ArrayList<>();
        ALTorderedTreeWalk(a, root);
        for (int i = 0; i < a.size();i++)
        {
            returnedArray[i]= a.get(i);
        }
        return returnedArray;

    }

       private void ALTorderedTreeWalk(ArrayList<Integer> a, Node x) {
        // if this is wrong please show a better method.
        if (x != null)                          //1 if x != NIL
        {
            ALTorderedTreeWalk(a,  x.left);      //2 INORDER-TREE-WALK(.x: left)/
            a.add(x.key);                       //3 print x:key       
            ALTorderedTreeWalk(a,  x.right);     //4 INORDER-TREE-WALK.(x:right)/
        }
    }

    private void orderedTreeWalk(int[] a, int i, Node x) {
        // if this is wrong please show a better method.
        if (x != null)                          //1 if x != NIL
        {
            orderedTreeWalk(a, i, x.left);      //2 INORDER-TREE-WALK(.x: left)/
            a[i] = x.key;                       //3 print x:key
            i++;
            orderedTreeWalk(a, i, x.right);     //4 INORDER-TREE-WALK.(x:right)/
        }
    }

    @Override
    public boolean search(int k) {
        return search(root, k) != null; // return true if node is found.
    }

    private Node search(Node x, int k) {
        if (x == null || k == x.key) //1 if x == NIL or k == x:key
        {
            return x;                       //2 return x
        }
        if (k < x.key) //3 if k < x:key
        {
            return search(x.left, k);           //4 return TREE-SEARCH.x: left; k/
        } else {
            return search(x.right, k);       //5 else return TREE-SEARCH.x:right; k/
        }
    }

    /* A binary tree node has key, pointer to  
    left child and a pointer to right child */
    class Node {

        int key;
        Node left, right, parrent;

        // constructor 
        Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }
}
