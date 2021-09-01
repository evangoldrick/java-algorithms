package abstractDataTypes;
/**
 * Class which implements both a min and max heap
 * @author Evgol
 *
 */
public class MyHeap {
	private int[] array;
	private boolean maxHeap;
	private int numElements;
	/**
	 * Constructor method
	 * @param maxHeap determines if this should be a max heap or min heap. true = max, false = min
	 */
	MyHeap(boolean maxHeap) {
		this.maxHeap = maxHeap;
	}
	
	/**
	 * Function to insert elements into the heap
	 * @param element new element to add to the heap
	 */
	public void insert(int element) {
		if (array == null) {
			array = new int[1];
			numElements = 0;
		}

		if (!hasSpace()) {
			increaseArraySize();
		}

		array[numElements] = element;

		siftUp(numElements);

		++numElements;
	}
	
	/**
	 * function to peek at the top value
	 * @return the max or min value in the heap
	 */
	public int find() {
		return array[0];
	}

	/**
	 * Function to pop the value off the top of the heap
	 * @return the max or min value in the heap
	 */
	public int extract() {
		int val = array[0];
		delete();
		return val;
	}

	/**
	 * Function to remove the value off the top of the heap
	 */
	public void delete() {
		array[0] = array[numElements - 1];
		array[numElements - 1] = -100;
		--numElements;
		siftDown(0);
	}

	/**
	 * Function to calculate the parent index
	 * @param c child index
	 * @return parent index
	 */
	private int getParentIndex(int c) {
		return (c - 1) / 2;
	}

	/**
	 * Function to find the index of the left child
	 * @param p parent index
	 * @return left child index
	 */
	private int getLeftChildIndex(int p) {
		return p * 2 + 1;
	}

	/**
	 * Function to find th eindex of the right child
	 * @param p parent index
	 * @return right child index
	 */
	private int getRightChildIndex(int p) {
		return p * 2 + 2;
	}
	
	/**
	 * Sift up the value at the provided index if needed
	 * @param index index of the child which may need to sift up
	 */
	private void siftUp(int index) {
		int parentIndex = getParentIndex(index);
		if (((array[index] < array[parentIndex]) ^ maxHeap) && index != 0) {
			int temp = array[index];
			array[index] = array[parentIndex];
			array[parentIndex] = temp;
			siftUp(parentIndex);
		}
	}

	/**
	 * Sift down the value at the provied index if needed
	 * @param index index of the parent which may need to shift up
	 */
	private void siftDown(int index) {
		int temp;
		int leftChild = getLeftChildIndex(index);
		int rightChild = getRightChildIndex(index);

		if (leftChild >= numElements) return; // If both children are outside the heap, no sifting is needed
		
		if (((array[leftChild] < array[rightChild]) ^ maxHeap) || rightChild >= numElements) { 
			// Check for sifting down left branch if it is the lower value or the right branch is outside the heap
			if ((array[index] > array[leftChild]) ^ maxHeap) { // Check if the values need to be swapped
				temp = array[index];
				array[index] = array[leftChild];
				array[leftChild] = temp;
				if (getLeftChildIndex(leftChild) < numElements) // Check if more sifting may be possible
					siftDown(leftChild);
			}
		} else {
			if ((array[index] > array[rightChild]) ^ maxHeap) { // Check if the values need to be swapped
				temp = array[index];
				array[index] = array[rightChild];
				array[rightChild] = temp;
				if (getRightChildIndex(rightChild) < numElements) // Check if more sifting may be possible
					siftDown(rightChild);
			}
		}
	}
	
	/**
	 * Check if there is space for another element to be added on the heap
	 * @return true if there is room, false otherwise
	 */
	private boolean hasSpace() {
		return numElements < array.length;
	}

	/**
	 * Increases the array size to fit all of the elements. The tree will always be perfectly balanced 
	 */
	private void increaseArraySize() {
		int[] temp = array;
		array = new int[(int) (Math.pow(2, 1 + Math.floor(Math.log(numElements + 1) / Math.log(2))) - 1)];
		for (int i = 0; i < temp.length; ++i) {
			array[i] = temp[i];
		}
	}

	/**
	 * Get the number of elements stored in the heap
	 * @return number of elements
	 */
	public int getSize() {
		return numElements;
	}

	/**
	 * Check if the heap is empty
	 * @return true if there are no items in the heap, false otherwise
	 */
	public boolean isEmpty() {
		return numElements == 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyHeap testHeap = new MyHeap(true);

		for (int i = 1; i < 10000000; ++i) {
			testHeap.insert(i);
		}
		while (!testHeap.isEmpty()) {
			System.out.println(testHeap.extract());
		}
	}

}
