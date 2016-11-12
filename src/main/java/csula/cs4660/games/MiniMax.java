package csula.cs4660.games;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

public class MiniMax {
    public static Node getBestMove(Graph graph, Node root, Integer depth, Boolean max) {
    	Node bestValue;
        if (depth == 0 || graph.neighbors(root).size() == 0) {
            return root; // return a number
        }

        if (max) {
            bestValue =new Node<>(new MiniMaxState( Integer.MIN_VALUE, Integer.MIN_VALUE)); // positive infinite
            for (Node eachNode: graph.neighbors(root)) {
                Node value = getBestMove(graph,eachNode, depth - 1, false);
                bestValue = compareNodesMax(bestValue, value);
            }
            return bestValue;
        } else {
        	bestValue =new Node<>(new MiniMaxState( Integer.MAX_VALUE, Integer.MAX_VALUE)); // positive infinite
        	 for (Node eachNode: graph.neighbors(root)) {
        		 Node value = getBestMove(graph,eachNode, depth - 1, true);
                bestValue = compareNodesMin(bestValue, value);
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
