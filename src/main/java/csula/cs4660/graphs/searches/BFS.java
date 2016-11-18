package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


/**
 * Breadth first search
 */

public class BFS implements SearchStrategy {
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
    	Node startNode = source;
    	Queue<Node> frontier = new LinkedList<>();
    	Collection<Node> exploredSet = new LinkedList<>();
    	HashMap<Node , Edge> parents = new HashMap<Node,Edge>(); 
    	frontier.add(source);
    	exploredSet.add(source);
    	parents.put(source,null);
    	while(!frontier.isEmpty()){
    		Node firstNodeFromQueue = frontier.poll();
    		for(Node eachNeibhour : graph.neighbors(firstNodeFromQueue)){
    			if(!exploredSet.contains(eachNeibhour)){
    				parents.put(eachNeibhour, new Edge(firstNodeFromQueue, eachNeibhour, graph.distance(firstNodeFromQueue, eachNeibhour)));
    				exploredSet.add(eachNeibhour);
    				frontier.add(eachNeibhour);
    			}
    		}
    	}
    	Stack<Edge> edges = new Stack<>();
    	Edge currentEdge = parents.get(dist);
    	while(currentEdge != null){
    		edges.push(currentEdge);
    		currentEdge = parents.get(currentEdge.getFrom());
    	}
    	
    	List<Edge> results = new ArrayList<>();
    	while(!edges.empty()){
    		results.add(edges.pop());
    	}
        return results;
    }
}
