
public class PQHeap implements PQ {

    int lastHeapIndex = 0;
    Element[] elements;

    public PQHeap(int maxElms) {
        elements = new Element[maxElms];
    }

    // Method for taking out the smallest element in the heap
    @Override
    public Element extractMin() {
        // creating a local variable
        Element min = elements[0];
        lastHeapIndex--;
        elements[0] = elements[lastHeapIndex];
        // restructure the heap after extracting the minimum have been swapped and extracted
        heapify(lastHeapIndex, 0);
        return min;
    }

    public void heapIncreaseKey(int i) {

        while (i > 0 && elements[getParent(i)].getKey()> elements[i].getKey()) {
            Element temp = elements[getParent(i)];
            elements[getParent(i)] = elements[i];
            elements[i] = temp;
            i = getParent(i);
        }
    }

    //inserts the elements into the array at the index after the last non-empty index.
    @Override
    public void insert(Element e) {
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
            Element temp = elements[index];
            elements[index] = elements[smallest];
            elements[smallest] = temp;
            //recursive call
            heapify(length, smallest);
        }
    }

    // returns the parent
    public int getParent(int index) {
        index = index - 1;
        return index / 2;
    }

    // returns -1 if the heap index is empty
    public boolean isEmpty() {
        return lastHeapIndex == -1;
    }

    public int size() {
        return lastHeapIndex;
    }

    // returns the left child of a parrent in accordance to the heap structure
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    // returns the right child of a parrent in accordance to the heap structure
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
    // Prints out the elemenst in the Heap, nice for debugging
    public void printElements()
    {
        for (int i = 0; i < elements.length; i++) {
            Element hn = elements[i];
            if(hn == null)
                System.out.println("[     ]");
            else{
            System.out.println("["+ hn.getKey()+":"+ hn.getData()+"]");
            
            }
            }
    }
}
