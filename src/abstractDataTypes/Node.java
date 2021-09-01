package abstractDataTypes;

public class Node<T> {
	private T val;

	public Node(T val) {
		this.val = val;
	}

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
	
	
}
