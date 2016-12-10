package csula.cs4660.games;
import java.util.*;


class PointXY {
	final int x;
    final int y;
    

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public PointXY(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof PointXY)) {
            return false;
        }

        PointXY coord = (PointXY) o;

        return  coord.x == x && coord.y == y;
    }

    @Override
    public int hashCode() {
    	int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}

class Edge {
    private NodeTron from;
    private NodeTron to;
    private int value;

    public Edge(NodeTron from, NodeTron to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeTron getFrom() {
        return from;
    }

    public void setFrom(NodeTron from) {
        this.from = from;
    }

    public NodeTron getTo() {
        return to;
    }

    public void setTo(NodeTron to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (getValue() != edge.getValue()) return false;
        if (getFrom() != null ? !getFrom().equals(edge.getFrom()) : edge.getFrom() != null)
            return false;
        return !(getTo() != null ? !getTo().equals(edge.getTo()) : edge.getTo() != null);

    }

    @Override
    public String toString() {
        return "Edge{" +
            "from=" + from +
            ", to=" + to +
            ", value=" + value +
            '}';
    }

    @Override
    public int hashCode() {
        int result = getFrom() != null ? getFrom().hashCode() : 0;
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + getValue();
        return result;
    }
}

class TileTron {
    private final int x;
    private final int y;
    private final String type;

    public TileTron(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TileTron)) return false;

        TileTron tile = (TileTron) o;

        if (getX() != tile.getX()) return false;
        if (getY() != tile.getY()) return false;
        return getType() != null ? getType().equals(tile.getType()) : tile.getType() == null;

    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }
}
class MiniMaxStateTron {
    private int[][] state;
    private String recentMoves;
    private int value;

    public MiniMaxStateTron(int[][] state, int value, String recentMoves) {
        this.state = state;
        this.value = value;
        this.recentMoves = recentMoves;
    }

    public String getRecentMoves() {
		return recentMoves;
	}

	public void setRecentMoves(String recentMoves) {
		this.recentMoves = recentMoves;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int[][] getState() {
        return state;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MiniMaxStateTron)) return false;

        MiniMaxStateTron that = (MiniMaxStateTron) o;

        return Arrays.deepEquals(getState(), that.getState());
    }
    
    @Override
    public int hashCode() {
    	return Arrays.deepHashCode(getState());
    }
}
class NodeTron<T>{
	    private T data;
	    //private final int value;
	    
	    
	    public NodeTron(T data) {
	        this.data = data;
	    }

	    public T getData() {
	        return data;
	    }
	    
	    public void setData(T data){
	    	this.data = data;
	    }

	    @Override
	    public String toString() {
	        return "NodeTron{" +
	            "data=" + data +
	            '}';
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof NodeTron)) return false;

	        NodeTron<?> node = (NodeTron<?>) o;

	        return getData() != null ? getData().equals(node.getData()) : node.getData() == null;

	    }

	    @Override
	    public int hashCode() {
	        return getData() != null ? getData().hashCode() : 0;
	    }
}

class GraphTron{
	 private Map<NodeTron, Collection<Edge>> adjacencyList= new HashMap<NodeTron,Collection<Edge>>();

	 	public GraphTron(){
	 		
	 	}

	    public boolean adjacent(NodeTron x, NodeTron y) {
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

	    public List<NodeTron> neighbors(NodeTron x) {
	    	List<NodeTron> neighborsNodes = new ArrayList<>();
	    	if(adjacencyList.containsKey(x)){
	   		  Collection<Edge> edges = adjacencyList.get(x);
	   		  for(Edge eachEdge: edges){
	   			  neighborsNodes.add(eachEdge.getTo());
	   		  }
	   		  return neighborsNodes;
	   	  }
	        return neighborsNodes;
	    }


	    public boolean addNode(NodeTron x) {
	    	
	    	if(!adjacencyList.containsKey(x)){
	    		  adjacencyList.put(x,new ArrayList<Edge>());
	    		  //System.out.println(adjacencyList.containsKey(new NodeTron<TileTron>(new TileTron(0,0,"0"))));
	    		  return true;
	   	  }
	        return false;
	    }

	    public boolean removeNode(NodeTron x) {
	    	if(adjacencyList.containsKey(x)){
	  		  adjacencyList.remove(x);
	  		for(Map.Entry<NodeTron, Collection<Edge>> entry : adjacencyList.entrySet()) {
				NodeTron key = entry.getKey();
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

	    public boolean addEdge(Edge x) {
	    	NodeTron from = x.getFrom();
	    	NodeTron to = x.getTo();
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

	    public boolean removeEdge(Edge x) {
	    	NodeTron from = x.getFrom();
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

	    public int distance(NodeTron from, NodeTron to) {
	    	Collection<Edge> edges = adjacencyList.get(from);
	    	for(Edge eachEdge : edges){
	    		if(eachEdge.getTo().getData() == to.getData()){
	    			return eachEdge.getValue();
	    		}
	    	}
	        return 0;
	    }

	    
	    public NodeTron getNodeIndexByData(int data){
	    	for (Map.Entry<NodeTron,Collection<Edge>> entry : adjacencyList.entrySet()) {
	    		  NodeTron key = entry.getKey();
	    		  if((int) key.getData() == (int) data){
	      			return key;
	      			}
	    		  }
	    	return null;
	    }

	    public Optional<NodeTron> getNode(NodeTron node) {
	        Iterator<NodeTron> iterator = adjacencyList.keySet().iterator();
	        Optional<NodeTron> result = Optional.empty();
	        while (iterator.hasNext()) {
	            NodeTron next = iterator.next();
	            if (next.equals(node)) {
	                result = Optional.of(next);
	            }
	        }
	        return result;
	    }
}
class Player {
	static int floodFillCount = 0;
	static HashMap<Integer,List<PointXY>> playersLocationsTrack = new HashMap<>();
	static NodeTron rootNode = null;
	static int radius = 50;
	static Integer Alpha = Integer.MIN_VALUE;
	static Integer Beta = Integer.MAX_VALUE;
	static List<NodeTron<TileTron>> visitedNodes;
	static Map <PointXY,TileTron> tileMap = new HashMap<PointXY,TileTron>();
	static int P;
    private long startTime;
    static final int rows = 20;
    static final int columns = 30;
    static int N;
    static int NCopy = 0;
    static int level = 1;
    public static void main(String args[]) {
    	Scanner in = new Scanner(System.in);
        String currentMove = "", previousMove = "";
    	long startTime = System.currentTimeMillis();
    	//code
        int board[][] = new int[rows][columns];
        
        long endTime = System.currentTimeMillis();
        GraphTron graphs = parseIntoTiles(board);
    	System.err.println("Took "+(endTime - startTime) + " ms");
    	List<PointXY> opponentsLocations = new ArrayList<PointXY>();
        while (true) {
            N = in.nextInt(); // total number of players (2 to 4).
            if(NCopy == 0){
            	NCopy = N;
            }
            P = in.nextInt(); // your player number (0 to 3).
            int currentRow =0;
            int currentColumn = 0;
            for (int i = 0; i < N; i++) {
                int X0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
                int Y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
                int X1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
                int Y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)
                if(X0 != -1){
                board[Y1][X1] = i+1;
                Optional<NodeTron> beforeUpdatedNode =  graphs.getNode(new NodeTron<TileTron>(new TileTron(Y1,X1,"0")));
                NodeTron<TileTron> afterUpdateNode = ((NodeTron<TileTron>) beforeUpdatedNode.get());
                afterUpdateNode.setData(new TileTron(Y1,X1,(i+1)+""));
                if(!playersLocationsTrack.containsKey(i)){
                	List<PointXY> playersMoves = new ArrayList<>();
                	playersMoves.add(new PointXY(Y1, X1));
                	playersLocationsTrack.put(i, playersMoves);
                }else{
                	List<PointXY> playersMoves = playersLocationsTrack.get(i);
                	playersMoves.add(new PointXY(Y1, X1));
                	playersLocationsTrack.put(i, playersMoves);
                }
                if(i == P){ 
                    System.err.println(X0+"-"+Y0+"-"+X1+"-"+Y1);
                    currentRow = Y1;
                    currentColumn = X1;
                }
                else{
                	opponentsLocations.add(new PointXY(Y1, X1));
                }
                }else{
                	if(playersLocationsTrack.containsKey(i)){
                		NCopy = NCopy - 1;
                		graphs  = removeFromBoard(board,playersLocationsTrack.get(i),(i+1)+"",graphs);
                		playersLocationsTrack.remove(i);
                	}
                }
            }
            String initialState = currentRow+"+"+currentColumn;
        	for(PointXY eachOpponentLocation : opponentsLocations){
        		initialState += "#" + eachOpponentLocation.getX() + "+" + eachOpponentLocation.getY();
        	}
        	rootNode = null;
        	if(isEnemyClose( new PointXY(currentRow, currentColumn), opponentsLocations )){
        		 GraphTron graphMinMax = new GraphTron();
                 graphMinMax = buildGraph(board,currentColumn,currentRow,opponentsLocations);
        		NodeTron best = getBestMove(graphMinMax, new NodeTron<MiniMaxStateTron>(new MiniMaxStateTron(board, 0 , initialState)), 3, true);
        		 int rowInitial = Integer.parseInt(initialState.split("#")[P].split("\\+")[0]);
                 int columnInitial = Integer.parseInt(initialState.split("#")[P].split("\\+")[1]);
                 int row = Integer.parseInt(((MiniMaxStateTron) best.getData()).getRecentMoves().split("#")[P].split("\\+")[0]);
                 int column = Integer.parseInt(((MiniMaxStateTron) best.getData()).getRecentMoves().split("#")[P].split("\\+")[1]);
                 currentMove = getMove(rowInitial,columnInitial,row,column);
        	}else{
        		Optional<NodeTron> currentNode =  graphs.getNode(new NodeTron<TileTron>(new TileTron(currentRow,currentColumn,(P+1)+"")));
                NodeTron currentNodeOfPlayer = (NodeTron) currentNode.get();
                NodeTron<TileTron> longestPathNode = null;
                int max = 0;
                for(NodeTron<TileTron> eachNode : graphs.neighbors(new NodeTron<TileTron>(new TileTron(currentRow,currentColumn,(P+1)+"")))){
                	visitedNodes =  new ArrayList<NodeTron<TileTron>>();
                	int lengthOfTheChildNode = getLongestPathNode(graphs,eachNode);
                	if(max < lengthOfTheChildNode){
                		max = lengthOfTheChildNode;
                		longestPathNode = eachNode;
                	}
                }
                currentMove = getMove(currentRow,currentColumn,longestPathNode.getData().getX(),longestPathNode.getData().getY());
        	}
            
            //debugBoard(board);
           
            if(currentMove.equals("")){
            	currentMove = avoidBlock(board, currentRow+"+"+currentColumn,previousMove);
            	System.err.println("ran by Simple Avoid Block");
            }
            System.out.println(currentMove);
            previousMove = currentMove;
            opponentsLocations =  new ArrayList<PointXY>();
             // A single line with UP, DOWN, LEFT or RIGHT
        }
    }
    private static GraphTron removeFromBoard(int[][] board, List<PointXY> playerMoves, String playerNumber, GraphTron graphs) {
    	for(PointXY eachLocationOfPlayer: playerMoves){
			board[eachLocationOfPlayer.getX()][eachLocationOfPlayer.getY()] = 0;
			Optional<NodeTron> beforeUpdatedNode =  graphs.getNode(new NodeTron<TileTron>(new TileTron(eachLocationOfPlayer.getX(),eachLocationOfPlayer.getY(),playerNumber)));
	         NodeTron<TileTron> afterUpdateNode = ((NodeTron<TileTron>) beforeUpdatedNode.get());
	         afterUpdateNode.setData(new TileTron(eachLocationOfPlayer.getX(),eachLocationOfPlayer.getY(),"0"));
		}
    	 
		return graphs;
	}
	private static boolean isEnemyClose(PointXY myLocation, List<PointXY> opponentsLocations) {
    	for(PointXY eachOpponentLocation : opponentsLocations){
    		int dx = Math.abs(myLocation.getX() - eachOpponentLocation.getX());
       	  	int dy = Math.abs(myLocation.getY() - eachOpponentLocation.getY());
       	  	if(N > 3){
       	  		level = 1;
       	  	}else{
	           	level = 2;
            }
       	  	if((dx + dy) < radius ){
       	  		return true;
       	  	}
    	}
    	 
		return false;
	}
	private static int getLongestPathNode(GraphTron graphs,NodeTron<TileTron> eachNode) {
    	visitedNodes.add(eachNode);
    	    if(! hasUnvisitedNode(graphs.neighbors(eachNode))){
    	         return 0;
    	    }else{
    	        int maxDepth = 0;

    	        for(NodeTron<TileTron> child : graphs.neighbors(eachNode)){
    	        	if(!visitedNodes.contains(child) && child.getData().getType() == "0" ){
    	        		maxDepth = Math.max(maxDepth, getLongestPathNode(graphs,child));
    	        	}
    	        }
    	        return maxDepth + 1;
    	    }
    }
    private static boolean hasUnvisitedNode(List<NodeTron> neibhourNodes) {
		// TODO Auto-generated method stub
		for(NodeTron<TileTron> eachNode : neibhourNodes){
			 if(! visitedNodes.contains(eachNode)){
				 return true;
			 }
		}
		return false;
	}
    private static String getMove(int rowInitial, int columnInitial, int row, int column) {
		if(row < rowInitial ){
			return "UP";
		}
		else if(row > rowInitial ){
			return "DOWN";
		}
		else if(column < columnInitial ){
			return "LEFT";
		}
		else if (column > columnInitial ){
			return "RIGHT";
		}
		else{
			return "";
		}
		
	}
	public static int[][] deepCopyIntMatrix(int[][] input) {
	    if (input == null)
	        return null;
	    int[][] result = new int[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}
    private static GraphTron buildGraph(int[][] board, int currentColumn, int currentRow,
			List<PointXY> opponentsLocations) {
    	GraphTron graphMinMax = new GraphTron();
    	String initialState = currentRow+"+"+currentColumn;
    	for(PointXY eachOpponentLocation : opponentsLocations){
    		initialState += "#" + eachOpponentLocation.getX() + "+" + eachOpponentLocation.getY();
    	}
    	
    	long startTime = System.currentTimeMillis();
    	
    	
    	NodeTron<MiniMaxStateTron> start = new NodeTron<MiniMaxStateTron>(new MiniMaxStateTron(board,0,initialState));
    	Queue<NodeTron<MiniMaxStateTron>> frontier = new LinkedList<NodeTron<MiniMaxStateTron>>();
        frontier.add(start);
    	

        TreeMap<String, String> possibleMoves = new TreeMap<String, String>();
        NodeTron<MiniMaxStateTron> lastNode = start;
//        int level;
//        if(N > 3){
//        level = 1;
//        radius = 15;
//        }else{
//        	level = 2;
//        	radius = 100;
//        }
    	while(level > 0 && !frontier.isEmpty()){
    		NodeTron<MiniMaxStateTron> currentNode = frontier.poll();
    		if(currentNode.equals(lastNode)){
    			lastNode = null;
    			level -=1;
    		}
    		graphMinMax.addNode(currentNode);
    		possibleMoves = generatePossibleMove(currentNode);
	        graphMinMax = addPossibleStates(possibleMoves, graphMinMax,currentNode);

	        int count = 0;
	        
    		for(NodeTron<MiniMaxStateTron> eachNode: graphMinMax.neighbors(currentNode)){
    			frontier.add(eachNode);
    			if(count == graphMinMax.neighbors(currentNode).size() - 1){
    				if(lastNode == null){
    				lastNode = eachNode;
    				}
    			}
    	        count += 1;
    		}
    	}
    	
    	
    	
    	long endTime = System.currentTimeMillis();
    	System.err.println("Took this much for graph build"+(endTime - startTime) + " ms");
        
        
        
		return graphMinMax;
	}

	private static TreeMap<String, String> generatePossibleMove(NodeTron<MiniMaxStateTron> start) {
		// TODO Auto-generated method stub
		String players[] = start.getData().getRecentMoves().split("#");
		TreeMap<String, String> possibleMoves = new TreeMap<String,String>();
		int i = 0;
        for(String eachPlayer : players){
        	String x = eachPlayer.split("\\+")[0];
        	String y = eachPlayer.split("\\+")[1];
        	if(i == 0){
        		 possibleMoves = getPossibleMovesTreeMap(start.getData().getState(), x +"+"+ y );
        	}else{
        	possibleMoves = findPossibleCombinations(possibleMoves,getPossibleMovesTreeMap(start.getData().getState(),x +"+"+ y));
        	}
        	i++;
        }
		return possibleMoves;
	}
	private static GraphTron addPossibleStates(TreeMap<String, String> possibleMoves, GraphTron graphMinMax,
			NodeTron<MiniMaxStateTron> start) {
		int boardCopy[][];
		for(Map.Entry<String, String> entry1 :  possibleMoves.entrySet()){
        	String playerMoves[]  = entry1.getValue().split("#");
        	 boardCopy = deepCopyIntMatrix(start.getData().getState());
        	for(int i = 1; i <= playerMoves.length ; i++ ){
        		int x = Integer.parseInt(playerMoves[i-1].split("\\+")[0]);
        		int y = Integer.parseInt(playerMoves[i-1].split("\\+")[1]);
        		boardCopy[x][y] = i;
        	}
        	NodeTron<MiniMaxStateTron> child = new NodeTron<MiniMaxStateTron>(new MiniMaxStateTron(boardCopy, 0,entry1.getValue()));
        	graphMinMax.addNode(child);
        	graphMinMax.addEdge(new Edge(start,child,1));
        }
		return graphMinMax;
	}

	private static TreeMap<String, String> findPossibleCombinations(TreeMap<String, String> possibleMoves1,
			TreeMap<String, String> possibleMoves2) {
    	TreeMap<String,String> result = new TreeMap<String, String>();
    	for(Map.Entry<String, String> entry1 : possibleMoves1.entrySet()){
    		for(Map.Entry<String, String> entry2 : possibleMoves2.entrySet()){
    		String key = entry1.getKey() + "#"+entry2.getKey();
    		String value = entry1.getValue() + "#" + entry2.getValue();
    		result.put(key, value);
        	}
    	}
    	return result;
	}

	private static GraphTron parseIntoTiles(int[][] board) {
    	GraphTron graph = new GraphTron();
    	//to add the nodes 
    	for(int i = 0;i< board.length ;i++){
    		for(int j = 0;j< board[0].length ;j++){
    			tileMap.put(new PointXY(i,j),new TileTron(i,j,"0"));
    			graph.addNode(new NodeTron<TileTron>(new TileTron(i,j,"0")));
    		}
    	}
    	
    	for (Map.Entry<PointXY,TileTron> entry : tileMap.entrySet())
    	{
    		pushEdges(graph,entry.getValue(),"N",entry.getKey().getX(),entry.getKey().getY());
    		pushEdges(graph,entry.getValue(),"E",entry.getKey().getX(),entry.getKey().getY());
    		pushEdges(graph,entry.getValue(),"S",entry.getKey().getX(),entry.getKey().getY());
    		pushEdges(graph,entry.getValue(),"W",entry.getKey().getX(),entry.getKey().getY());
    	}
		return graph;
	}
    
    private static void pushEdges(GraphTron graph,TileTron fromTile, String direction,int row,int column) {
	int x;
	int y;
	PointXY newCoord;
	TileTron newTile;
	switch(direction){
	case "N" : 
			x = fromTile.getX();	
			y = fromTile.getY()-1;
			newCoord = new PointXY(x,y);
			newTile =(TileTron) tileMap.get(newCoord);
			if(tileMap.containsKey(newCoord)){
				graph.addEdge(new Edge(new NodeTron<TileTron>(fromTile),new NodeTron<TileTron>(newTile),1));
		}
		break;
	case "S" : 
			x = fromTile.getX();
			y = fromTile.getY()+1;
			newCoord = new PointXY(x,y);
			newTile =(TileTron) tileMap.get(newCoord);
			if(tileMap.containsKey(newCoord)){
				graph.addEdge(new Edge(new NodeTron<TileTron>(fromTile),new NodeTron<TileTron>(newTile),1));
		}

		break;
	case "E" : 
		
			x = fromTile.getX()+1;
			y = fromTile.getY();
			newCoord = new PointXY(x,y);
			newTile = tileMap.get(newCoord);		
			if(tileMap.containsKey(newCoord)){
				graph.addEdge(new Edge(new NodeTron<TileTron>(fromTile),new NodeTron<TileTron>(newTile),1));
		}

		break;
	case "W" : 
			x = fromTile.getX()-1;
			y = fromTile.getY();
			newCoord = new PointXY(x,y);
			newTile = tileMap.get(newCoord);	
			if(tileMap.containsKey(newCoord)){
			graph.addEdge(new Edge(new NodeTron<TileTron>(fromTile),new NodeTron<TileTron>(newTile),1));
		}
		break;
	
	}

}
	private static String avoidBlock(int[][] board, String currentXY,String previousMove) {
		// TODO Auto-generated method stub
    	return getPossibleMoves(board,currentXY);
    	
    	
	}
	 private static TreeMap<String,String> getPossibleMovesTreeMap(int[][] board, String currentXY) {
	        TreeMap<String,String> possibleMoves = new TreeMap<String, String>();
	    	String XY[] = currentXY.split("\\+");
	    	int row = Integer.parseInt(XY[0]);
	    	int column = Integer.parseInt(XY[1]);
	    	
	    	if(row + 1 < rows && board[row +1][column] < 1){
	    		possibleMoves.put("DOWN",(row+1)+"+"+column);
			}
			if(row -1 >= 0 && board[row  - 1][column] < 1){
				possibleMoves.put("UP",(row-1)+"+"+column);
			}
			if(column + 1 < columns && board[row][column + 1] < 1){
				possibleMoves.put("RIGHT",row+"+"+(column+1));
			}
			if(column - 1 >= 0 && board[row][column - 1] < 1){
				possibleMoves.put("LEFT",row+"+"+(column-1));
			}
	    	return possibleMoves;
	    }
    private static  String getPossibleMoves(int[][] board, String currentXY) {
        HashMap<String,String> possibleMoves = new HashMap<String, String>();
    	String XY[] = currentXY.split("\\+");
    	int row = Integer.parseInt(XY[0]);
    	int column = Integer.parseInt(XY[1]);
    	System.err.println(row + "=====> " + column);
    	if(row + 1 < rows && board[row +1][column] < 1){
    		possibleMoves.put("DOWN",(row+1)+"+"+column);
		}
		if(row -1 >= 0  && board[row  - 1][column] < 1){
			possibleMoves.put("UP",(row-1)+"+"+column);
		}
		if(column + 1 < columns && board[row][column + 1] < 1){
			possibleMoves.put("RIGHT",row+"+"+(column+1));
		}
		if(column - 1 >= 0 && board[row][column - 1] < 1){
			possibleMoves.put("LEFT",row+"+"+(column-1));
		}
    	System.err.println("size ==== > " + possibleMoves.size());
    	String result = possibleMoves.keySet().iterator().next();
    	System.err.println(result);
    	return result;
    }
    private static String getFirstPossibleMove(int[][] board, String currentXY) {
        HashMap<String,String> possibleMoves = new HashMap<String, String>();
    	String XY[] = currentXY.split("\\+");
    	int row = Integer.parseInt(XY[0]);
    	int column = Integer.parseInt(XY[1]);
    	
    	if(row + 1 < rows && board[row +1][column] < 1){
    		return "Down";
		}
    	else if((row -1 < rows && row -1 >= 0) && board[row  - 1][column] < 1){
			return "UP";
		}
    	else if(column + 1 < columns && board[row][column + 1] < 1){
			return "RIGHT";
		}
    	else if((column - 1 < columns && column - 1 >= 0) && board[row][column - 1] < 1){
			return "LEFT";
		}
    	return null;
    }
    
	private static void debugBoard(int[][] Board){
         for(int i=0; i < 20;i ++){
            for(int j=0; j < 30;j ++){
                System.err.print(Board[i][j]+ " ");
                }
                System.err.println();
            }
        }

	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public static NodeTron getBestMove(GraphTron graph, NodeTron<MiniMaxStateTron> source, Integer depth, Boolean max) {
	        // TODO: implement your alpha beta pruning algorithm here

	    	if(rootNode == null){
	    		rootNode = source;
	    	}
	    	NodeTron bestValue;
	        if (depth == 0 || graph.neighbors(source).size() == 0) {
	            	
	            int value =evaluvateState(source); 
	            source.getData().setValue(value);
	        	return source; // return a number
	        }

	        if (max) {
	            bestValue =new NodeTron<>(new MiniMaxStateTron(null, Integer.MIN_VALUE,"")); // negative infinite
	            for (NodeTron eachNode: graph.neighbors(source)) {
	                NodeTron value = getBestMove(graph,eachNode, depth - 1, !max);
	               bestValue = compareNodesMax(bestValue, value);
	            }
	            if( !(((MiniMaxStateTron) source.getData()).getRecentMoves()).equals(((MiniMaxStateTron) rootNode.getData()).getRecentMoves())){
	            bestValue = new NodeTron(new MiniMaxStateTron( ((MiniMaxStateTron) source.getData()).getState() , ((MiniMaxStateTron)bestValue.getData()).getValue(), ((MiniMaxStateTron) source.getData()).getRecentMoves() ));
	            }
	            ((MiniMaxStateTron) graph.getNode(source).get().getData()).setValue(((MiniMaxStateTron)bestValue.getData()).getValue());
	            return bestValue;
	        } else {
	        	bestValue =new NodeTron<>(new MiniMaxStateTron(null, Integer.MAX_VALUE,"")); // positive infinite
	        	 for (NodeTron eachNode: graph.neighbors(source)) {
	        		NodeTron value = getBestMove(graph,eachNode, depth - 1, !max);
	                bestValue = compareNodesMin(bestValue, value);
	            }
	        	 if( !(((MiniMaxStateTron) source.getData()).getRecentMoves()).equals(((MiniMaxStateTron) rootNode.getData()).getRecentMoves())){
	 	            bestValue = new NodeTron(new MiniMaxStateTron( ((MiniMaxStateTron) source.getData()).getState() , ((MiniMaxStateTron)bestValue.getData()).getValue(), ((MiniMaxStateTron) source.getData()).getRecentMoves() ));
	 	            }
	        	  ((MiniMaxStateTron) graph.getNode(source).get().getData()).setValue(((MiniMaxStateTron)bestValue.getData()).getValue());
	            return bestValue;
	        }
	       
	    }
	    
	    private static int evaluvateState(NodeTron<MiniMaxStateTron> source) {
	    	
	    	Queue<String> frontier = new LinkedList<>();
    		int playerCountCellCount,otherPlayersCellCount,wall = 9;
    		playerCountCellCount = otherPlayersCellCount = 0;
			int [][] currentBoardState = deepCopyIntMatrix(source.getData().getState());
	    	String playerCurrentLocation[] = source.getData().getRecentMoves().split("#");
	    	HashMap<String, Integer> cellWithParents = new HashMap<>();
	    	int i = 0;
	    	for(String eachPlayerLocation : playerCurrentLocation){
	    		if(! cellWithParents.containsKey(eachPlayerLocation)){
	    			cellWithParents.put(eachPlayerLocation, i+1);
	    		}
	    		frontier.add(eachPlayerLocation);
	    		i++;
	    	}
	    	
	    	while(! frontier.isEmpty()){
	    		String key = frontier.poll();
	    		String rowColumn[] = key.split("\\+"); 
	    		int x = Integer.parseInt(rowColumn[0]);
	    		int y = Integer.parseInt(rowColumn[1]);
	    		
	    		if(x + 1 < rows && (currentBoardState[x + 1][y] == 0 || currentBoardState[x + 1][y] > 4 && currentBoardState[x + 1][y] <= 20)){
	    			String newKey = (x+1) + "+" + y;
	    			if(currentBoardState[x + 1][y] == 0){
	    			cellWithParents.put(newKey, cellWithParents.get(key));
	    			currentBoardState[x+1][y] = cellWithParents.get(key) + 4;
	    			if(P + 1 + 4 == currentBoardState[x+1][y]){
	    				playerCountCellCount += 1;
	    			}else{
	    				otherPlayersCellCount += 1;
	    			}
	    			frontier.add(newKey);
	    			}else{
	    				if(cellWithParents.get(newKey) != cellWithParents.get(key)){
	    					if(currentBoardState[x + 1][y] > 4 && currentBoardState[x + 1][y] != wall){
	    						if(P + 1 + 4 == currentBoardState[x + 1][y]){
	    		    				playerCountCellCount -= 1;
	    		    			}else{
	    		    				otherPlayersCellCount -= 1;
	    		    			}
		    				currentBoardState[x + 1][y] = wall;
	    					}
		    			}
	    			}
	    		}
	    		if(x - 1 >= 0 && (currentBoardState[x - 1][y] == 0 || currentBoardState[x - 1][y] > 4 && currentBoardState[x - 1][y] <= 20)){
	    			String newKey = (x-1) + "+" + y;
	    			if(currentBoardState[x - 1][y] == 0){
	    			cellWithParents.put(newKey, cellWithParents.get(key));
	    			currentBoardState[x-1][y] = cellWithParents.get(key) + 4;
	    			if(P + 1 + 4 == currentBoardState[x-1][y]){
	    				playerCountCellCount += 1;
	    			}else{
	    				otherPlayersCellCount += 1;
	    			}
	    			frontier.add(newKey);
	    			}else{
	    				if(cellWithParents.get(newKey) != cellWithParents.get(key)){
	    					if(currentBoardState[x - 1][y] > 4 && currentBoardState[x - 1][y] != wall){
	    						if(P + 1 + 4 == currentBoardState[x-1][y]){
	    		    				playerCountCellCount -= 1;
	    		    			}else{
	    		    				otherPlayersCellCount -= 1;
	    		    			}
		    				currentBoardState[x - 1][y] = wall;
	    					}
		    			}
	    			}
	    		}
	    		if(y + 1 < columns && (currentBoardState[x][y + 1] == 0 || currentBoardState[x][y + 1] > 4 && currentBoardState[x][y + 1] <= 20)){
	    			String newKey = x + "+" + (y + 1);
	    			if(currentBoardState[x][y+1] == 0){
	    			cellWithParents.put(newKey, cellWithParents.get(key));
	    			currentBoardState[x][y+1] = cellWithParents.get(key) + 4;
	    			if(P + 1 + 4 == currentBoardState[x][y + 1]){
	    				playerCountCellCount += 1;
	    			}else{
	    				otherPlayersCellCount += 1;
	    			}
	    			frontier.add(newKey);
	    			}else{
	    				if(cellWithParents.get(newKey) != cellWithParents.get(key)){
	    					if(currentBoardState[x][y + 1] > 4  && currentBoardState[x][y + 1] != wall){
	    						if(P + 1 + 4 == currentBoardState[x][y + 1]){
	    		    				playerCountCellCount -= 1;
	    		    			}else{
	    		    				otherPlayersCellCount -= 1;
	    		    			}
		    				currentBoardState[x][y + 1] = wall;
	    					}
		    			}
	    			}
	    		}
	    		if(y - 1 >= 0 && (currentBoardState[x][y - 1] == 0 || currentBoardState[x][y - 1] > 4 && currentBoardState[x][y - 1] <= 20)){
	    			String newKey = x + "+" + (y - 1);
	    			if(currentBoardState[x][y-1] == 0){
	    			cellWithParents.put(newKey, cellWithParents.get(key));
	    			currentBoardState[x][y-1] = cellWithParents.get(key) + 4;
	    			if(P + 1 + 4 == currentBoardState[x][y - 1]){
	    				playerCountCellCount += 1;
	    			}else{
	    				otherPlayersCellCount += 1;
	    			}
	    			frontier.add(newKey);
	    			}else{
	    				if(cellWithParents.get(newKey) != cellWithParents.get(key)){
	    					if(currentBoardState[x][y-1] > 4  && currentBoardState[x][y - 1] != wall){
	    						if(P + 1 + 4 == currentBoardState[x][y - 1]){
	    		    				playerCountCellCount -= 1;
	    		    			}else{
	    		    				otherPlayersCellCount -= 1;
	    		    			}
		    				currentBoardState[x][y-1] = wall;
	    					}
		    			}
	    			}
	    		}
	    	
	    	}
	    	//debugBoard(currentBoardState);
	    	return playerCountCellCount - otherPlayersCellCount; 
		}
		private static NodeTron compareNodesMin(NodeTron<MiniMaxStateTron> bestValue, NodeTron<MiniMaxStateTron> value) {
			if(((MiniMaxStateTron) bestValue.getData()).getValue() < value.getData().getValue()){
				return bestValue;
			}
			return value;
		}

		private static NodeTron<MiniMaxStateTron> compareNodesMax(NodeTron<MiniMaxStateTron> bestValue, NodeTron<MiniMaxStateTron> value) {
			if(( bestValue.getData()).getValue() > value.getData().getValue()){
				return bestValue;
			}
			return value;
		}
}