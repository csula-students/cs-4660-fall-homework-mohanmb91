package csula.cs4660.games;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

public class AlphaBeta {
    
    static Node rootNode = null;
    static Integer Alpha = Integer.MIN_VALUE;
    static Integer Beta = Integer.MAX_VALUE;
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Node getBestMove(Graph graph, Node source, Integer depth, Integer alpha, Integer beta, Boolean max) {
        // TODO: implement your alpha beta pruning algorithm here

    	if(rootNode == null){
    		rootNode = source;
    	}
    	Node bestValue;
        if (depth == 0 || graph.neighbors(source).size() == 0) {
            return source; // return a number
        }

        if (max) {
            bestValue =new Node<>(new MiniMaxState( Integer.MIN_VALUE, Integer.MIN_VALUE)); // negative infinite
            for (Node eachNode: graph.neighbors(source)) {
                Node value = getBestMove(graph,eachNode, depth - 1, alpha, beta, !max);
                if(alpha < ((MiniMaxState)value.getData()).getValue() ){
       			 alpha = ((MiniMaxState)value.getData()).getValue();
       			if(beta < alpha){
       				bestValue = compareNodesMin(bestValue, value);
       				break;
       			}
       		 	}
                bestValue = compareNodesMax(bestValue, value);
            }
            if(((MiniMaxState) source.getData()).getIndex() != ((MiniMaxState) rootNode.getData()).getIndex()){
            bestValue = new Node(new MiniMaxState( ((MiniMaxState) source.getData()).getIndex() , ((MiniMaxState)bestValue.getData()).getValue() ));
            }
            ((MiniMaxState) graph.getNode(source).get().getData()).setValue(((MiniMaxState)bestValue.getData()).getValue());
            return bestValue;
        } else {
        	bestValue =new Node<>(new MiniMaxState( Integer.MAX_VALUE, Integer.MAX_VALUE)); // positive infinite
        	 for (Node eachNode: graph.neighbors(source)) {
        		 Node value = getBestMove(graph,eachNode, depth - 1, alpha, beta, !max);
        		 if(beta > ((MiniMaxState)value.getData()).getValue() ){
        			 beta = ((MiniMaxState)value.getData()).getValue();
        			 if(beta < alpha){
        				 bestValue = compareNodesMin(bestValue, value);
        				 break;
        			 }
        		 }
                bestValue = compareNodesMin(bestValue, value);
            }
        	  if(((MiniMaxState) source.getData()).getIndex() != ((MiniMaxState) rootNode.getData()).getIndex()){
                  bestValue = new Node(new MiniMaxState( ((MiniMaxState) source.getData()).getIndex() , ((MiniMaxState)bestValue.getData()).getValue() ));
                  }
        	  ((MiniMaxState) graph.getNode(source).get().getData()).setValue(((MiniMaxState)bestValue.getData()).getValue());
            return bestValue;
        }
       
    }
    
    private static Node compareNodesMin(Node<MiniMaxState> bestValue, Node<MiniMaxState> value) {
		if(((MiniMaxState) bestValue.getData()).getValue() < value.getData().getValue()){
			return bestValue;
		}
		return value;
	}

	private static Node<MiniMaxState> compareNodesMax(Node<MiniMaxState> bestValue, Node<MiniMaxState> value) {
		if(( bestValue.getData()).getValue() > value.getData().getValue()){
			return bestValue;
		}
		return value;
	}

	
}
