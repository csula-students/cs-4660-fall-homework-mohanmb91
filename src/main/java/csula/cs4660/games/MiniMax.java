package csula.cs4660.games;

import java.util.HashMap;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

public class MiniMax {
	static HashMap<Node<MiniMaxState>, Node<MiniMaxState>> parents = new HashMap<>();
    @SuppressWarnings({ "rawtypes", "unchecked" })
    static Node rootNode = null; 
	public static Node getBestMove(Graph graph, Node root, Integer depth, Boolean max) {
    	if(rootNode == null){
    		rootNode = root;
    	}
    	Node bestValue;
        if (depth == 0) {
            return root; // return a number
        }

        if (max) {
            bestValue =new Node<>(new MiniMaxState( Integer.MIN_VALUE, Integer.MIN_VALUE)); // negative infinite
            for (Node eachNode: graph.neighbors(root)) {
                Node value = getBestMove(graph,eachNode, depth - 1, false);
                bestValue = compareNodesMax(bestValue, value);
            }
            if(((MiniMaxState) root.getData()).getIndex() != ((MiniMaxState) rootNode.getData()).getIndex()){
            bestValue = new Node(new MiniMaxState( ((MiniMaxState) root.getData()).getIndex() , ((MiniMaxState)bestValue.getData()).getValue() ));
            }
            return bestValue;
        } else {
        	bestValue =new Node<>(new MiniMaxState( Integer.MAX_VALUE, Integer.MAX_VALUE)); // positive infinite
        	 for (Node eachNode: graph.neighbors(root)) {
        		 Node value = getBestMove(graph,eachNode, depth - 1, true);
                bestValue = compareNodesMin(bestValue, value);
            }
        	  if(((MiniMaxState) root.getData()).getIndex() != ((MiniMaxState) rootNode.getData()).getIndex()){
                  bestValue = new Node(new MiniMaxState( ((MiniMaxState) root.getData()).getIndex() , ((MiniMaxState)bestValue.getData()).getValue() ));
                  }
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
