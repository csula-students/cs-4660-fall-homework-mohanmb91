package csula.cs4660.graphs.searches;

import csula.cs4660.games.models.Tile;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Perform A* search
 */
public class AstarSearch implements SearchStrategy {
	List<Node> exploredSet;
	PriorityQueue frontier; 
	HashMap<Node , Edge> parents ;
	HashMap<Node , Integer> Gvalue ;
	HashMap<Node , Integer> totalValue ;
	HashMap<Node , Integer> Hvalue ;
	
	boolean nodeFound;
	public AstarSearch(){
		nodeFound = false;
		exploredSet = new ArrayList<>();
		frontier = new PriorityQueue();
		parents = new HashMap<Node , Edge>(); 
		totalValue = new HashMap<Node , Integer>(); 
		Gvalue = new HashMap<Node , Integer>(); 
		Hvalue = new HashMap<Node , Integer>(); 
	}
	@Override
	public List<Edge> search(Graph graph, Node source, Node dist) {
		frontier.enqueue(source, getHeuristic(source,dist));
    	exploredSet.add(source);
    	Hvalue.put(source,getHeuristic(source, dist));
    	Gvalue.put(source,0);
    	totalValue.put(source, Hvalue.get(source) + Gvalue.get(source));
    	while(!frontier.isEmpty()){
    	Node<Tile> firstNodeOfPriorityQueue = frontier.dequeue();
    	exploredSet.add(firstNodeOfPriorityQueue);
    	int neibhourWeight,currentNodeWeight,combinedWeight;
    	for(Node<Tile> eachNeibhour : graph.neighbors(firstNodeOfPriorityQueue)){
    		if(!eachNeibhour.getData().getType().equals("##")){
    			if(!exploredSet.contains(eachNeibhour)){
				if(frontier.getPriotiyOfNode( eachNeibhour) == -1){
					Hvalue.put(eachNeibhour,getHeuristic(eachNeibhour, dist));
			    	Gvalue.put(eachNeibhour,Gvalue.get(firstNodeOfPriorityQueue) + 1);
			    	totalValue.put(eachNeibhour, Hvalue.get(eachNeibhour) + Gvalue.get(eachNeibhour));
					frontier.enqueue(eachNeibhour, totalValue.get(eachNeibhour));
					parents.put(eachNeibhour, new Edge(firstNodeOfPriorityQueue, eachNeibhour, 1));
				}else{
					int newGScore = Gvalue.get(firstNodeOfPriorityQueue) + 1;
					int oldGscore = Gvalue.get(eachNeibhour);
					if(newGScore < oldGscore){
				    	Gvalue.put(eachNeibhour,newGScore);
				    	totalValue.put(eachNeibhour, Hvalue.get(eachNeibhour) + Gvalue.get(eachNeibhour));
				    	frontier.remove(eachNeibhour);
				    	frontier.enqueue(eachNeibhour, totalValue.get(eachNeibhour));
						parents.put(eachNeibhour, new Edge(firstNodeOfPriorityQueue, eachNeibhour, 1));
					}
				}
				if(eachNeibhour.equals(dist)){
					nodeFound = true;
				}
			}
			}
    		if(nodeFound){
    			break;
    		}
		}
    	if(nodeFound){
			break;
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
	private int getHeuristic(Node<Tile> firstNodeOfPriorityQueue, Node<Tile> dist) {
		
	  int dx = Math.abs(firstNodeOfPriorityQueue.getData().getX() - dist.getData().getX());
	  int dy = Math.abs(firstNodeOfPriorityQueue.getData().getY() - dist.getData().getY());
	  return 1 * (dx + dy);
	
	}
}
