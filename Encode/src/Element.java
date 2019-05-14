// node class is the basic structure 
// of each node present in the Huffman - tree. 

public class Element {
    private int key; // parrent 
    private Object data; 

    public Element(int i, Object o) {
        this.key = i;
        this.data = o;
    }

    public int getKey() {
        return this.key;
    }

    public Object getData() {
        return this.data;
    }
}
