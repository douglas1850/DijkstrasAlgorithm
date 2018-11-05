import java.util.*;

public class Main {

    public static void main(String[] args){
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 5);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 10);
        nodeB.addDestination(nodeF, 2);

        nodeC.addDestination(nodeE, 9);

        nodeD.addDestination(nodeE, 4);
        nodeD.addDestination(nodeF, 2);

        nodeF.addDestination(nodeE, 10);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = calcShortestPath(graph, nodeA);
        System.out.println();
    }

    public static Graph calcShortestPath(Graph graph, Node source) {
        ArrayList<String> orderOfTraversal = new ArrayList<>();
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>(); // keeps track of visited nodes
        Set<Node> unsettledNodes = new HashSet<>(); // keep track of unvisited nodes

        unsettledNodes.add(source);

        // as long as there are unsettled nodes, continue looping
        while (unsettledNodes.size() != 0) {
            Node currentNode = closestNode(unsettledNodes); // find the closest node and set it to currentNode
            orderOfTraversal.add(currentNode.getName()); //keep track of node order
            unsettledNodes.remove(currentNode); //curNode has been visited to remove it
            for (Map.Entry<Node, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) { // for each adj node
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) { //if other adj nodes haven't been visited, visit them
                    calcMinDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        System.out.println("Order of traversal: ");
        for(String node : orderOfTraversal){
            System.out.println(node);
        }
        return graph;
    }

    private static Node closestNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calcMinDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
