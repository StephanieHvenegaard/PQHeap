
import java.util.Comparator;

// comparator class helps to compare the node 
// on the basis of one of its attribute. 
// Here we will be compared 
// on the basis of data values of the nodes. 
public class HuffmanNodeComparator implements Comparator<Element> { 
        @Override
	public int compare(Element x, Element y) 
	{ 

		return x.getData() - y.getData(); 
	} 
} 
