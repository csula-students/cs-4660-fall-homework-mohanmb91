package csula.cs4660.graphs.representations;

import csula.cs4660.exercises.FileRead;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;


/**
 * Adjacency matrix in a sense store the nodes in two dimensional array
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
            });
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public AdjacencyMatrix() {
    	adjacencyMatrix = new int[0][0];
    	nodes = new Node[0];
    }

    @Override
    public boolean adjacent(Node x, Node y) {
    	if(ArrayUtils.indexOf(nodes, x) >=0 && ArrayUtils.indexOf(nodes, x) >= 0){
    		return (adjacencyMatrix[ ArrayUtils.indexOf(nodes, x)][ArrayUtils.indexOf(nodes, y)] > 0);	
    	}
        return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
    	int coloumn = 0;
    	int nodeIndex = -1;
    	for(int i = 0; i < nodes.length; i ++ ){
    		if (nodes[i].getData().equals(x.getData())){
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
    	if(ArrayUtils.indexOf(nodes, x) == -1){
    		Node[] temp = nodes;
    		nodes = Arrays.copyOf(temp, nodes.length + 1);
    		nodes[nodes.length - 1] = new Node(x.getData());
    		int[][] tempAdjacency = Arrays.copyOf(adjacencyMatrix, adjacencyMatrix.length + 1); 
    		tempAdjacency[adjacencyMatrix.length] = new int[adjacencyMatrix.length]; 
    		for (int i = 0; i < adjacencyMatrix.length; i++) {
    			tempAdjacency[adjacencyMatrix.length][i] = 0;
    		}
    		int [][] finaltemp = new int[adjacencyMatrix.length + 1][adjacencyMatrix.length + 1];
    		int z = 0;
    		for (int[] is : tempAdjacency) {
    			int[] temp1 = Arrays.copyOf(is, is.length + 1);
    			finaltemp[z] = temp1;
    			z = z + 1;
			}
    		adjacencyMatrix = new int[adjacencyMatrix.length + 1][adjacencyMatrix.length + 1];
    		adjacencyMatrix = finaltemp;
    		return true;
		}
    	return false;
    }

    @Override
    public boolean removeNode(Node x) {
    	try{
    		for(int i = 0; i < nodes.length; i ++){
    			if(nodes[i].equals(x)){
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
    	Node from = x.getFrom();
    	Node to = x.getTo();
    	if(adjacencyMatrix[ArrayUtils.indexOf(nodes, x.getFrom())][ArrayUtils.indexOf(nodes, x.getTo())] == 0){
    		adjacencyMatrix[ArrayUtils.indexOf(nodes, x.getFrom())][ArrayUtils.indexOf(nodes, x.getTo())] = x.getValue();
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
    

    @Override
    public Optional<Node> getNode(Node node) {
        Iterator<Node> iterator = Arrays.asList(nodes).iterator();
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
