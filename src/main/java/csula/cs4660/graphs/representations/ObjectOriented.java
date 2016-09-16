package csula.cs4660.graphs.representations;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Object oriented representation of graph is using OOP approach to store nodes
 * and edges
 *
 * TODO: Please fill the body of methods in this class
 */
public class ObjectOriented implements Representation {
    private Collection<Node> nodes;
    private Collection<Edge> edges;

    public ObjectOriented(File file) {
    nodes = new ArrayList<Node>();
    edges = new ArrayList<Edge>();
   	 try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(line -> {
               if(line.split(":").length == 1){
               	
            	   int totalNoOfNodes = Integer.parseInt(line);
            	   for (int i = 0; i < totalNoOfNodes; i++) {
					nodes.add(new Node(i));   
				}
            	   
            	   
               }
               else{
            	   String[] fromToValue = line.split(":");
            	   edges.add(new Edge(new Node(Integer.parseInt(fromToValue[0])),new Node(Integer.parseInt(fromToValue[1])),Integer.parseInt(fromToValue[2])));
               }
                //System.out.println(line);
               
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectOriented() {

    }

    @Override
    public boolean adjacent(Node x, Node y) {
    	for (Edge edge : edges) {
			if(edge.getFrom().getData().equals(x.getData()) &&
				edge.getTo().getData().equals(y.getData())){
				return true;
				
			}
		}
        return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
    	List<Node> expectedlist= new ArrayList<Node>();
    	for (Edge edge : edges) {
			if(edge.getFrom().getData().equals(x.getData())){
				expectedlist.add(new Node(edge.getTo().getData()));
				
			}
		}
        return expectedlist;
    }

    @Override
    public boolean addNode(Node x) {
    	if(!nodes.contains(x)){
    		nodes.add(x);
    		return true;
    	}
        return false;
    }

    @Override
    public boolean removeNode(Node x) {
    	if(nodes.contains(x)){
    		nodes.remove(x);
    		Collection<Edge> newEdge = new ArrayList<Edge>();
    		for (Edge edge : edges) {
				if(!edge.getTo().getData().equals(x.getData())){
					newEdge.add(edge);
				}
			}
    		edges = newEdge;
    		return true;
    	}
        return false;
    }

    @Override
    public boolean addEdge(Edge x) {
    	if(!edges.contains(x)){
    		edges.add(x);
    		return true;
    	}
        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
        if(edges.contains(x)){
        	edges.remove(x);
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
}
