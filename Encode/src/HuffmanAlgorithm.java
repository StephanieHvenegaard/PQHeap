
import java.util.HashMap;

public class HuffmanAlgorithm {
    private HashMap<Integer, String> dataTable = new HashMap<>();
    private HashMap<String, Integer> codeTable = new HashMap<>();
    /**
     * generates a key value pair of the tree elements. key = byte value, value
     * = huffman code
     *
     * @param element root of tree
     * @return hashmap of keys and values
     */
    public HashMap<Integer, String> getDataTable(Element element) {
        dataTable.clear();
        generateDataCodeTable(element, "");
        return dataTable;
    }

    /**
     * generates a key value pair of the tree elements. key = human code, value
     * = bytevalue.
     * @param element root of tree
     * @return hashmap of keys and values
     */
    public HashMap<String, Integer> getCodeTable(Element element) {
        codeTable.clear();
        generateCodeDataTable(element, "");
        return codeTable;
    }
    /**
     * recursively wals through the tree and checks each node for data
     *
     * @param element current element to check
     * @param code hoffman code to assign
     */
    private void generateCodeDataTable(Element element, String code) {
        if (element.getData() instanceof Node) {
            Node node = (Node) element.getData();
            // Checks for if we have reached the end of the tree,
            // if not then we fill it with code and element
            if (node.left == null && node.right == null) {
                codeTable.put(code, (int) element.getData());
                return;
            }
            generateCodeDataTable(node.left, code + "0");
            generateCodeDataTable(node.right, code + "1");
        } else {
            codeTable.put(code, (int) element.getData());
            return;
        }
    }
    /**
     * recursively wals through the tree and checks each node for data
     *
     * @param element current element to check
     * @param code hoffman code to assign
     */
    private void generateDataCodeTable(Element element, String code) {
        if (element.getData() instanceof Node) {
            Node node = (Node) element.getData();
            if (node.left == null && node.right == null) {
                dataTable.put((int) element.getData(), code);
                return;
            }
            generateDataCodeTable(node.left, code + "0");
            generateDataCodeTable(node.right, code + "1");
        } else {
            dataTable.put((int) element.getData(), code);
            return;
        }
    }

/**
 * generates the huffman tree compression. 
 * @param bytefreq array of numbers of accurenenses for earch byte pattern
 * @return root element of entire tree.
 */ 
    public Element Encrypt(int[] bytefreq) {
        // creating a priority queue q. 
        // makes a min-priority queue(min-heap). 
        PQHeap q = new PQHeap(bytefreq.length);
        // iterates through frequensies and builds first table of frequencies
        for (int i = 0; i < bytefreq.length; i++) {
            // creating an object 
            Element e = new Element(bytefreq[i], i); //  Bytefreq = key | 0-255 data.
            // and add it to the priority queue. 
            q.insert(e);
        }
        // create a root node 
        Element root = null;
        // Here we will extract the two minimum value 
        // from the heap each time until 
        // its size reduces to 1, extract until 
        // all the nodes are extracted. 
        while (q.size() > 1) {
            // first min extract. 
            Element x = q.extractMin();
            // second min extarct. 
            Element y = q.extractMin();
            // to the sum of the frequency of the two nodes 
            // assigning values to the f node. 
            // saves key for new node 
            int key = y.getKey() + x.getKey();
            // Create new tree struck object            
            Node n = new Node(key);
            // first extracted node as left child. 
            n.left = x;
            // second extracted node as the right child. 
            n.right = y;
            // new node f which is equal 
            Element e = new Element(key, n);
            // marking the f node as the root node. 
            root = e;
            // add this node to the priority-queue. 
            q.insert(e);
        }
        return root;
    }
}
