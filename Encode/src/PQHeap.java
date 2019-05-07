public class PQHeap implements PQ {
    int lastHeapIndex = 0;
    HuffmanNode[] elements;

    public PQHeap(int maxElms) {
        elements = new HuffmanNode[maxElms];
    }
    // Method for taking out the smallest element in the heap
    public HuffmanNode extractMin() {
        // creating a local variable
        HuffmanNode min = elements[0];
        lastHeapIndex--;
        elements[0] = elements[lastHeapIndex];
        // restructure the heap after extracting the minimum have been swapped and extracted
        heapify(lastHeapIndex, 0);
        return min;
    }
    
    public void heapIncreaseKey(int i){
        
        while(i > 0 && elements[getParent(i)].getKey() > elements[i].getKey()){
            HuffmanNode temp = elements[getParent(i)];
            elements[getParent(i)] = elements[i];
            elements[i] = temp;
            i = getParent(i);
        }
    }

    //inserts the elements into the array at the index after the last non-empty index.
    public void insert(HuffmanNode e) {
        //setting the element into the lastHeapIndex
        elements[lastHeapIndex] = e;
        int i = lastHeapIndex;
        lastHeapIndex = lastHeapIndex + 1;
        //runs the method for inserting the increasing the key
        heapIncreaseKey(i);
    }

    //used to create the sudo ordered heapstructure.
    private void heapify(int length, int index) {
        int smallest = index;
        int leftChild = getLeftChildIndex(index);
        int rightChild = getRightChildIndex(index);
        if (leftChild < length && elements[leftChild].getKey() < elements[smallest].getKey()) {
            smallest = leftChild;
        }
        if (rightChild < length && elements[rightChild].getKey() < elements[smallest].getKey()) {
            smallest = rightChild;
        }
        
        if (smallest != index) {
            HuffmanNode temp = elements[index];
            elements[index] = elements[smallest];
            elements[smallest] = temp;
            //recursive call
            heapify(length, smallest);
        }
    }
    // returns the parent
    public int getParent(int index){
        index = index - 1;
        return index / 2;
    }

    // returns -1 if the heap index is empty
    public boolean isEmpty() {
        return lastHeapIndex == -1;
    }

    // returns the left child of a parrent in accordance to the heap structure
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    // returns the right child of a parrent in accordance to the heap structure
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
}