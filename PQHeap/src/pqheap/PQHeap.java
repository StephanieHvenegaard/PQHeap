/*
 * @author Stephanie sthve16@student.sdu.dk
 * @author Simon sije817@student.sdu.dk
 * @author Joakim joala09@student.sdu.dk
 */
public class PQHeap implements PQ {
    int lastHeapIndex = -1;
    Element[] elements;

    public PQHeap(int maxElms) {
        elements = new Element[maxElms];
    }
    
    public Element extractMin() {
        // Swapper first and last element
        Element temp = elements[0];
        elements[0] = elements[lastHeapIndex];
        elements[lastHeapIndex] = null; // Removes last elemt.
        // tick down heap size 
        lastHeapIndex--;
        // resort the list
        Sort();
        return temp;
    }

    //inserts the elements into the array at the index after the last non-empty index.
    public void insert(Element e) {
        lastHeapIndex++;
        elements[lastHeapIndex] = e;
        Sort();
    }

    //used to create the sudo ordered heapstructure.
    private void makeHeap(int length, int index) {
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
            makeHeap(length, smallest);
        }
    }

    //sorts the array in accordance to the heap structure.
    private void Sort() {
        int length = lastHeapIndex+1;
        for (int i = length / 2 - 1; i >= 0; i--) {
            makeHeap(length, i);
        }
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