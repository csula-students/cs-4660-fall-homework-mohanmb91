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



public class DijkstraSearch implements SearchStrategy {
	List<Node> exploredSet;
	PriorityQueue frontier; 
	HashMap<Node , Edge> parents ;
	
	public DijkstraSearch(){
		exploredSet = new ArrayList<>();
		frontier = new PriorityQueue();
		parents = new HashMap<Node , Edge>(); 
	}
    @SuppressWarnings("unchecked")
	@Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
    	
    	frontier.enqueue(source, 0);
    	exploredSet.add(source);
    	
    	while(!frontier.isEmpty()){
    	Node<Integer> firstNodeOfPriorityQueue = frontier.dequeue();
    	exploredSet.add(firstNodeOfPriorityQueue);
    	for(Node<Integer> eachNeibhour : graph.neighbors(firstNodeOfPriorityQueue)){
			if(!exploredSet.contains(eachNeibhour)){
				int neibhourWeight = graph.distance(firstNodeOfPriorityQueue, eachNeibhour);
				int currentNodeWeight = frontier.getPriotiyOfNode(firstNodeOfPriorityQueue);
				int combinedWeight = neibhourWeight + currentNodeWeight;
				if(frontier.getPriotiyOfNode( eachNeibhour) == -1){
					frontier.enqueue(eachNeibhour, combinedWeight);
					parents.put(eachNeibhour, new Edge(firstNodeOfPriorityQueue, eachNeibhour, neibhourWeight));
				}else{
					if(combinedWeight < frontier.getPriotiyOfNode(eachNeibhour)){
						frontier.remove(eachNeibhour);
						frontier.enqueue(eachNeibhour, combinedWeight);	
						parents.put(eachNeibhour, new Edge(firstNodeOfPriorityQueue, eachNeibhour, neibhourWeight));
					}
				}
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
