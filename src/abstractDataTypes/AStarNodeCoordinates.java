package abstractDataTypes;

import java.util.HashMap;

public class AStarNodeCoordinates implements Comparable<AStarNodeCoordinates> {
	private double x, y, heuristic;
	private HashMap<AStarNodeCoordinates, Double> map;
	private boolean marked;
	private AStarNodeCoordinates prevNode;
	private Object data;
	
	public AStarNodeCoordinates(Object data) {
		this.x = 0;
		this.y = 0;
		this.setData(data);
	}
	
	public AStarNodeCoordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public AStarNodeCoordinates(double x, double y, Object data) {
		this.x = x;
		this.y = y;
		this.setData(data);
	}

	
	public void calculateHeuristic(AStarNodeCoordinates node) {
		this.heuristic = Math.sqrt((node.x - x) * (node.x - x) + (node.y - y) * (node.y - y));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getHeuristic() {
		return heuristic;
	}
	
	public int compareTo(AStarNodeCoordinates o) {
		if (o.getHeuristic() == this.heuristic)
			return 0;
		else if (o.getHeuristic() < this.heuristic)
			return -1;
		else 
			return 1;
	}

	public HashMap<AStarNodeCoordinates, Double> getMap() {
		return map;
	}

	public void setMap(HashMap<AStarNodeCoordinates, Double> map) {
		this.map = map;
	}
	
	public void mark() {
		marked = true;
	}
	
	public boolean getMarked() {
		return marked;
	}

	public AStarNodeCoordinates getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(AStarNodeCoordinates prevNode) {
		this.prevNode = prevNode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
