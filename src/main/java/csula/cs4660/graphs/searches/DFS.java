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
 * Depth first search
 */
public class DFS implements SearchStrategy {
	Stack<Node> frontier;
	Collection<Node> exploredNodes;
	HashMap<Node , Edge> parents ;
	HashMap<Node , Edge> resultParents ;
	boolean nodeFound;
	public DFS(){
		nodeFound = false;
		frontier = new Stack<>();
		parents = new HashMap<Node,Edge>();
		resultParents = new HashMap<Node,Edge>();
		exploredNodes = new ArrayList<>();
	}
	
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
    	dfs(graph,source,dist);
    	parents.put(source,null);
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

	private void dfs(Graph graph, Node source, Node dist) {
		frontier.push(source);
		exploredNodes.add(source);
		boolean hasLegitNeibhours = false;
		if(!frontier.empty()){
		for(Node eachNode : graph.neighbors(source)){
			if(!exploredNodes.contains(eachNode)){
				if(!nodeFound){
					parents.put(eachNode, new Edge(source, eachNode, graph.distance(source, eachNode)));
				}
				if(eachNode.getData() == dist.getData()){
					nodeFound = true;
				}
				if(!nodeFound){
					dfs(graph,eachNode,dist);
				}
				
				hasLegitNeibhours =  true;
			}
		}
		if(!hasLegitNeibhours){
			Node poppedNode = frontier.pop();
			if(!nodeFound){
				parents.remove(poppedNode);	
			}
		}
	}
	}
}
