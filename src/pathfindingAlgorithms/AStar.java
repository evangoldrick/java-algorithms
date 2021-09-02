package pathfindingAlgorithms;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

import abstractDataTypes.AStarNodeCoordinates;

public class AStar {

	AStarNodeCoordinates[] nodes;
	AStarNodeCoordinates startNode, finalNode;

	public AStar(double[][] adjacencyMatrix, AStarNodeCoordinates[] nodes, int startNodeIndex, int finalNodeIndex) {
		if (nodes.length != adjacencyMatrix.length)
			throw new ArrayIndexOutOfBoundsException("Array is not square");
		
		for (int i = 0; i < adjacencyMatrix.length; ++i)
			if (adjacencyMatrix.length != adjacencyMatrix[i].length)
				throw new ArrayIndexOutOfBoundsException("Array is not square");
		
		for (int i = 0; i < nodes.length; ++i) {
			HashMap<AStarNodeCoordinates, Double> map = new HashMap<AStarNodeCoordinates, Double>();
			for (int j = 0; j < nodes.length; ++j) {
				if (adjacencyMatrix[i][j] > 0) {
					map.put(nodes[j], adjacencyMatrix[i][j]);
				}
			}
			nodes[i].setMap(map);
		}
		
		startNode = nodes[startNodeIndex];
		finalNode = nodes[finalNodeIndex];
		this.nodes = nodes;
		for (AStarNodeCoordinates node : this.nodes) {
			node.calculateHeuristic(this.nodes[finalNodeIndex]);
		}
	}
	
	public AStar(AStarNodeCoordinates[] nodes, int startNodeIndex, int finalNodeIndex) {
		startNode = nodes[startNodeIndex];
		finalNode = nodes[finalNodeIndex];
		this.nodes = nodes;
		for (AStarNodeCoordinates node : this.nodes) {
			node.calculateHeuristic(this.nodes[finalNodeIndex]);
		}
	}

	public AStar(AStarNodeCoordinates[] nodes, AStarNodeCoordinates startNode, AStarNodeCoordinates finalNode) {
		this.startNode = startNode;
		this.finalNode = finalNode;
		this.nodes = nodes;
		for (AStarNodeCoordinates node : this.nodes) {
			node.calculateHeuristic(finalNode);
		}
	}

	public AStarNodeCoordinates[] findPath() {
		PriorityQueue<AStarNodeCoordinates> queue = new PriorityQueue<AStarNodeCoordinates>();
		Stack<AStarNodeCoordinates> stack = new Stack<AStarNodeCoordinates>();

		queue.add(startNode);

		while (!queue.isEmpty()) {
			AStarNodeCoordinates currentNode = queue.poll();
			currentNode.mark();

			if (currentNode == finalNode) { // Reached the end
				// Backtrack for path
				while (currentNode != startNode) {
					stack.add(currentNode);
					currentNode = currentNode.getPrevNode();
				}
				stack.add(currentNode);
				
				AStarNodeCoordinates[] returnArray = new AStarNodeCoordinates[stack.size()];
				int stackSize = stack.size();
				
				for (int i = 0; i < stackSize; ++i) {
					returnArray[i] = stack.pop();
				}
				
				return returnArray;
			}

			for (AStarNodeCoordinates node : currentNode.getMap().keySet()) {
				if (!node.getMarked()) {
					queue.add(node);
					node.setPrevNode(currentNode);
				}
			}

		}

		return null;
	}
	
	
	public static void main(String[] args) {
		double[][] adjMatrix = {
				{0, 1, 5},
				{1, 0, 2},
				{5, 2, 0}
		};
		
		
		AStarNodeCoordinates[] nodes = {new AStarNodeCoordinates(0, 0, 0), new AStarNodeCoordinates(10, 10, 1), new AStarNodeCoordinates(100, 100, 2)};
		
		AStar test = new AStar(adjMatrix, nodes, 0, 2);
		AStarNodeCoordinates[] path = test.findPath(); 
		for (int i = 0; i < path.length; ++i) {
			System.out.println(path[i].getData());
		}
	}
}
