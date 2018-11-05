import java.util.*;

public class Node {

    private String name; // name of the node

    private List<Node> shortestPath = new LinkedList<>(); // keeps track of shortest path

    private Integer distance = Integer.MAX_VALUE; // simulates infinite starting distance

    // map the node to it's distance from neighbors
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance){
        adjacentNodes.put(destination, distance);
        // associates immediate neighbors with edge length
    }

    public Node(String name){
        this.name = name;
    }

    /****** getters and setters ******/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    }
