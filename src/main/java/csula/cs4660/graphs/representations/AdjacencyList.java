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
import java.util.*;

/**
 * Adjacency list is probably the most common implementation to store the unknown
 * loose graph
 *
 * TODO: please implement the method body
 */
public class AdjacencyList implements Representation {
    private Map<Node, Collection<Edge>> adjacencyList= new HashMap<Node,Collection<Edge>>();

    public AdjacencyList(File file) {
    	 
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
                 //System.out.println(line);
                
             });
         } catch (IOException e) {
             e.printStackTrace();
         }
 }

    protected AdjacencyList() {

    }

    @Override
    public boolean adjacent(Node x, Node y) {
    	if(adjacencyList.containsKey(x)){
   		  Collection<Edge> edges = adjacencyList.get(x);
   		  for(Edge eachEdge: edges){
   			  if(eachEdge.getTo().getData().equals(y.getData()) ){
   				  return true;
   			  }
   		  }
   	  }
        return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
    	List<Node> neighborsNodes = new ArrayList<>();
    	if(adjacencyList.containsKey(x)){
   		  Collection<Edge> edges = adjacencyList.get(x);
   		  for(Edge eachEdge: edges){
   			  neighborsNodes.add(eachEdge.getTo());
   		  }
   		  return neighborsNodes;
   	  }
        return neighborsNodes;
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
				if(edge.getTo().equals(x)){
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
    			//if(eachEdge.getTo().getData() == x.getTo().getData()){
    			if(eachEdge.equals(x)){
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
    			if(eachEdge.getTo().equals(x.getTo())){
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
    	Collection<Edge> edges = adjacencyList.get(from);
    	for(Edge eachEdge : edges){
    		if(eachEdge.getTo().getData() == to.getData()){
    			return eachEdge.getValue();
    		}
    	}
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

    @Override
    public Optional<Node> getNode(Node node) {
        Iterator<Node> iterator = adjacencyList.keySet().iterator();
        Optional<Node> result = Optional.empty();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.equals(node)) {
                result = Optional.of(next);
            }
        }
        return result;
    }
}
