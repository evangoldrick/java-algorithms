package pathfindingAlgorithms;

import java.util.HashMap;
import java.util.PriorityQueue;
import abstractDataTypes.AStarNodeCoordinates;

public class AStar {

	AStarNodeCoordinates[] nodes;
	AStarNodeCoordinates startNode, finalNode;

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
		queue.add(startNode);

		while (!queue.isEmpty()) {
			AStarNodeCoordinates currentNode = queue.poll();
			currentNode.mark();
			currentNode.calculateHeuristic(finalNode);

			for (AStarNodeCoordinates node : currentNode.getMap().keySet()) {
				if (!node.getMarked())
					queue.add(node);
			}

		}

		return null;
	}

	public static void main(String[] args) {
		AStarNodeCoordinates[] testNodes = { new AStarNodeCoordinates(0, 0), new AStarNodeCoordinates(10, 10),
				new AStarNodeCoordinates(100, 100) };

		HashMap<AStarNodeCoordinates, Double> testMap = new HashMap<AStarNodeCoordinates, Double>();

		testMap.put(testNodes[1], 1d);
		testMap.put(testNodes[2], 5d);
		testNodes[0].setMap(testMap);

		testMap = new HashMap<AStarNodeCoordinates, Double>();

		testMap.put(testNodes[0], 1d);
		testMap.put(testNodes[2], 2d);
		testNodes[1].setMap(testMap);

		testMap = new HashMap<AStarNodeCoordinates, Double>();

		testMap.put(testNodes[0], 5d);
		testMap.put(testNodes[1], 2d);
		testNodes[2].setMap(testMap);

		AStar test = new AStar(testNodes, testNodes[0], testNodes[2]);
		System.out.println(test.findPath());
	}

}
