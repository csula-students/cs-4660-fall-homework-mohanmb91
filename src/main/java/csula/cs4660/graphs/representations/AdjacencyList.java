package csula.cs4660.graphs.representations;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Adjacency list is probably the most common implementation to store the unknown
 * loose graph
 *
 * TODO: please implement the method body
 */
public class AdjacencyList implements Representation {
    private Map<Node, Collection<Edge>> adjacencyList;

    public AdjacencyList(File file) {
    	adjacencyList = new HashMap<Node,Collection<Edge>>();
    	 try (Stream<String> stream = Files.lines(file.toPath())) {
             stream.forEach(line -> {
                if(line.split(":").length == 1){
                	
             	   int totalNoOfNodes = Integer.parseInt(line);
             	   for (int i = 0; i < totalNoOfNodes; i++) {
             		   adjacencyList.put(new Node(i),new ArrayList<Edge>());
					   
				}
             	   
             	   
                }
                else{
             	   String[] fromToValue = line.split(":");
             	  Node fromNode = getNodeIndexByData(Integer.parseInt(fromToValue[0]));
             	  Node toNode = getNodeIndexByData(Integer.parseInt(fromToValue[1]));
             	  int value = Integer.parseInt(fromToValue[2]);
             	  Edge edge = new Edge(fromNode,toNode,value);
             	  if(adjacencyList.containsKey(fromNode)){
             		  Collection<Edge> edges = adjacencyList.get(fromNode);
             		 edges.add(edge);
             		 adjacencyList.put(fromNode, edges);
             	  }
                }
                 System.out.println(line);
                
             });
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public AdjacencyList() {

    }

    @Override
    public boolean adjacent(Node x, Node y) {
    	Node from = getNodeIndexByData((int) x.getData());
    	if(adjacencyList.containsKey(from)){
   		  Collection<Edge> edges = adjacencyList.get(from);
   		  for(Edge eachEdge: edges){
   			  if(eachEdge.getTo().getData() == y.getData() ){
   				  return true;
   			  }
   		  }
   	  }
        return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
    	List<Node> neighborsNodes = new ArrayList<>();
    	Node from = getNodeIndexByData((int) x.getData());
    	if(adjacencyList.containsKey(from)){
   		  Collection<Edge> edges = adjacencyList.get(from);
   		  for(Edge eachEdge: edges){
   			  neighborsNodes.add(eachEdge.getTo());
   		  }
   		  return neighborsNodes;
   	  }
        return null;
    }

    @Override
    public boolean addNode(Node x) {
    	
    	if(!adjacencyList.containsKey(x)){
    		  adjacencyList.put(x,new ArrayList<Edge>());
    		  return true;
   	  }
        return false;
    }

    @Override
    public boolean removeNode(Node x) {
    	if(adjacencyList.containsKey(x)){
  		  adjacencyList.remove(x);
  		for(Map.Entry<Node, Collection<Edge>> entry : adjacencyList.entrySet()) {
			Node key = entry.getKey();
			Collection<Edge> value = entry.getValue();
			for (Iterator iterator = value.iterator(); iterator.hasNext();) {
				Edge edge = (Edge) iterator.next();
				if(edge.getTo().getData().equals(x.getData())){
					iterator.remove();

				}
			}
			adjacencyList.put(key, value);
			}
  		  return true;
 	  }
        return false;
    }

    @Override
    public boolean addEdge(Edge x) {
    	Node from = x.getFrom();
    	Node to = x.getTo();
    	if(adjacencyList.containsKey(from) && adjacencyList.containsKey(to)){
    		Collection<Edge> edges = adjacencyList.get(from);
    		for(Edge eachEdge : edges){
    			if(eachEdge.getTo().getData() == x.getTo().getData()){
    				return false;
    			}
    		}
    		edges.add(x);
    		adjacencyList.put(from, edges);
    		return true;
    	}
        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
    	Node from = x.getFrom();
    	if(adjacencyList.containsKey(from)){
    		Collection<Edge> edges = adjacencyList.get(from);
    		if(edges == null){
    			return false;
    		}
    		Edge removeEdge = null;
    		boolean edgeFound = false;
    		for(Edge eachEdge : edges){
    			if((int) eachEdge.getTo().getData() == (int) x.getTo().getData()){
    				removeEdge = eachEdge;
    				edgeFound = true;
    				break;
    			}
    		}
    		if(!edgeFound){
    			return edgeFound;
    		}
    		edges.remove(removeEdge);
    		adjacencyList.put(from,edges);
    		return true;
    	}
        return false;
    }

    @Override
    public int distance(Node from, Node to) {
        return 0;
    }

    @Override
    public Optional<Node> getNode(int index) {
        return null;
    }
    
    public Node getNodeIndexByData(int data){
    	for (Map.Entry<Node,Collection<Edge>> entry : adjacencyList.entrySet()) {
    		  Node key = entry.getKey();
    		  if((int) key.getData() == (int) data){
      			return key;
      			}
    		  }
    	return null;
    }
}
