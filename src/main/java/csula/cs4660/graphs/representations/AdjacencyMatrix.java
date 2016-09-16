package csula.cs4660.graphs.representations;

import csula.cs4660.exercises.FileRead;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * Adjacency matrix in a sense store the nodes in two dimensional array
 *
 * TODO: please fill the method body of this class
 */
public class AdjacencyMatrix implements Representation {
    private Node[] nodes;
    private int[][] adjacencyMatrix;
    private  FileRead fileRead;
    public AdjacencyMatrix(File file) {
    	 try (Stream<String> stream = Files.lines(file.toPath())) {
             stream.forEach(line -> {
                if(line.split(":").length == 1){
             	   int totalNoOfNodes = Integer.parseInt(line);
             	   adjacencyMatrix = new int[totalNoOfNodes][totalNoOfNodes];
             	   nodes = new Node[totalNoOfNodes];
             	   for(int i =0; i< totalNoOfNodes; i++){
             		  nodes[i] = new Node(i);
             	   }
                }
                else{
             	   String[] fromToValue = line.split(":");
             	   adjacencyMatrix[Integer.parseInt(fromToValue[0])][Integer.parseInt(fromToValue[1])] = Integer.parseInt(fromToValue[2]);
             	   
                }
                 //System.out.println(line);
                
             });
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    
//    public static void main (String args[]){
//    	AdjacencyMatrix obj = new AdjacencyMatrix();
//    }

    public AdjacencyMatrix() {
    	ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("graph-1.txt").getFile());
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(line -> {
               if(line.split(":").length == 1){
            	   int totalNoOfNodes = Integer.parseInt(line);
            	   adjacencyMatrix = new int[totalNoOfNodes][totalNoOfNodes];
            	   nodes = new Node[totalNoOfNodes];
            	   
               }
               else{
            	   String[] fromToValue = line.split(":");
            	   adjacencyMatrix[Integer.parseInt(fromToValue[0])][Integer.parseInt(fromToValue[1])] = Integer.parseInt(fromToValue[2]);
               }
                //System.out.println(line);
               
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean adjacent(Node x, Node y) {
        return (adjacencyMatrix[(int) x.getData()][(int) y.getData()] > 0);
    }

    @Override
    public List<Node> neighbors(Node x) {
    	int coloumn = 0;
    	int nodeIndex = -1;
    	for(int i = 0; i < nodes.length; i ++ ){
    		if ((int) nodes[i].getData() == (int) x.getData()){
    			nodeIndex = i;
    		}
    	}
    	List<Node> nodesNeibhours = new ArrayList<>();
    	for(int i : adjacencyMatrix[nodeIndex]){
    		if(i>0){
    			nodesNeibhours.add(nodes[coloumn]);
    		}
    		coloumn += 1;
    	}
    	return nodesNeibhours;
    }

    @Override
    public boolean addNode(Node x) {
    	if(getNodeIndexByData((int) x.getData()) == null){
    		Node[] temp = nodes;
    		nodes = Arrays.copyOf(temp, nodes.length + 1);
    		nodes[nodes.length - 1] = new Node((int) x.getData());
    		return true;
		}
    	return false;
    }

    @Override
    public boolean removeNode(Node x) {
    	try{
    		for(int i = 0; i < nodes.length; i ++){
    			if(nodes[i].getData() == x.getData()){
    				for(int j = i; j < nodes.length -1 ; j ++){
    					nodes[j] = nodes[j+1];
    				}
    				Node[] temp = nodes;
    				nodes = Arrays.copyOf(temp, nodes.length -1);
    				break;
    			}
    		}
    		int[][] tempAdjacencyMatrix = new int[adjacencyMatrix.length - 1][adjacencyMatrix.length - 1];
        	if(adjacencyMatrix[(int) x.getData()] != null){
        		int iCount = 0;
        		int iCountNew = 0;
        		for(int[] i :adjacencyMatrix){
        			
        			int jCount = 0;
        			int jCountNew = 0;
        			for(int j: i){
        				if(iCount != (int) x.getData() && jCount != (int) x.getData()){
        				tempAdjacencyMatrix[iCountNew][jCountNew] = adjacencyMatrix[iCount][jCount];
        				jCountNew += 1;
        				}
        				jCount += 1;
        				//System.out.println(i + " + " + j);
        			}
        			iCount += 1;
        			if(iCount != (int) x.getData() && jCount != (int) x.getData()){
        			iCountNew += 1;
        			}
        		}
        		adjacencyMatrix = new int[adjacencyMatrix.length - 1][adjacencyMatrix.length - 1];
        		adjacencyMatrix = tempAdjacencyMatrix;
        		return true;
        	}
        	}catch(IndexOutOfBoundsException e){
        		return false;	
        	}
            return false;
    }

    @Override
    public boolean addEdge(Edge x) {
    	try{
    	if(adjacencyMatrix[(int) x.getFrom().getData()][(int) x.getTo().getData()] == 0){
    		adjacencyMatrix[(int) x.getFrom().getData()][(int) x.getTo().getData()] = x.getValue();
    		return true;
    	}
    	}catch(IndexOutOfBoundsException e){
    		return false;	
    	}
        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
    	try{
        	if(adjacencyMatrix[(int) x.getFrom().getData()][(int) x.getTo().getData()] != 0){
        		adjacencyMatrix[(int) x.getFrom().getData()][(int) x.getTo().getData()] = 0;
        		return true;
        	}
        	}catch(IndexOutOfBoundsException e){
        		return false;	
        	}
            return false;
    }

    @Override
    public int distance(Node from, Node to) {
        return adjacencyMatrix[(int) from.getData()][(int) to.getData()];
    }

    @Override
    public Optional<Node> getNode(int index) {
        return null;
    }
    
    public Node getNodeIndexByData(int data){
    	for(int i=0; i < nodes.length; i ++){
    		if((int) nodes[i].getData() == (int) data){
    			return nodes[i];
    		}
    	}
    	return null;
    }
    
}
