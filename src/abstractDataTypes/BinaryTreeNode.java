package abstractDataTypes;

public class BinaryTreeNode<T> extends Node<T> {
	private BinaryTreeNode<T> leftChild, rightChild;
	private BinaryTreeNode<T> parent;
	
	public BinaryTreeNode(T val) {
		super(val);	
	}
	
	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public BinaryTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<T> parent) {
		this.parent = parent;
	}

	public BinaryTreeNode(T val, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild) {
		super(val);
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	 
	
}
