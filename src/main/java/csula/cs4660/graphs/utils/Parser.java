package csula.cs4660.graphs.utils;

import csula.cs4660.games.models.Tile;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import csula.cs4660.graphs.representations.Representation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * A quick parser class to read different format of files
 */
public class Parser {
	static List<Tile> tiles = new ArrayList<Tile>();
    public static Graph readRectangularGridFile(Representation.STRATEGY graphRepresentation, File file) {
        Graph graph = new Graph(Representation.of(graphRepresentation));
        Scanner scan;
        
    	try {
    		scan = new Scanner(file);
    		int row = 0;
    		int coloumn =0;
			    while (scan.hasNextLine()) {
			    	String nextLine = scan.nextLine();
			    	if(!nextLine.contains("+") ){
			    		coloumn = 0;
			    	for(int i = 0; i < nextLine.length() - 2; i++){
			    		if(nextLine.charAt(i) != '|'){
			    		Tile temp = new Tile(coloumn,row,nextLine.charAt(i)+""+nextLine.charAt(i+1));
			    		tiles.add(temp);
			    		graph.addNode(new Node<>(temp));
			    		i += 1;
			    		coloumn += 1;
			    		}
			    	}
			    	row += 1;
			    	}
			    }
			    List<Character> directions = new ArrayList<Character>(); 
			    directions.add('N');directions.add('E');directions.add('S');directions.add('W');
			    for(Tile eachTile : tiles){
			    	for(char direction : directions){
			    		Tile temp = tileHasNeibhour(eachTile,direction);
			    		if(temp != null){
			    			graph.addEdge(new Edge(new Node<>(eachTile), new Node<>(temp), 1));
			    		}
			    	}
			    }
			    }
    	catch(Exception e){
    		
    	}
        return graph;
    }

    private static Tile tileHasNeibhour(Tile eachTile, char direction) {
    	Tile tile = null;
    	switch(direction){
    	case 'N':
    		tile = getTile(eachTile,eachTile.getX(),eachTile.getY() - 1);
    		break;
    	case 'E':
    		tile = getTile(eachTile,eachTile.getX() + 1,eachTile.getY());
    		break;
    	case 'S':
    		tile = getTile(eachTile,eachTile.getX(),eachTile.getY() + 1);
    		break;
    	case 'W':
    		tile = getTile(eachTile,eachTile.getX() - 1,eachTile.getY());
    		break;
    	}
		return tile;
	}

	private static Tile getTile(Tile eachTile, int x, int y) {
		for(Tile tile : tiles){
			if(tile.getX() == x && tile.getY() == y){
				return tile;
			}
		}
		return null;
	}

	public static String converEdgesToAction(Collection<Edge> edges) {
        // TODO: convert a list of edges to a list of action
        return "";
    }
}
