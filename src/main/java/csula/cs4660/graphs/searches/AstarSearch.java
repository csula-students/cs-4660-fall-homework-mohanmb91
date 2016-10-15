package csula.cs4660.graphs.searches;

import csula.cs4660.games.models.Tile;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.List;

/**
 * Perform A* search
 */
public class AstarSearch implements SearchStrategy {

	@Override
	public List<Edge> search(Graph graph, Node source, Node dist) {
		System.out.println("enter");
		
		for(Node<String> eachNode : graph.neighbors(source)){
			System.out.println(eachNode);
		}
		
		return null;
	}
}
