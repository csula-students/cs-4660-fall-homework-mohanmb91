package csula.cs4660.quizes;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;
import csula.cs4660.quizes.models.DTO;
import csula.cs4660.quizes.models.State;

import java.util.*;

/**
 * Here is your quiz entry point and your app
 */
public class App {
	static boolean isGoalReached = false;
    public static void main(String[] args) {
        State initialState = Client.getState("10a5461773e8fd60940a56d2e9ef7bf4").get();
        State goal = Client.getState("e577aa79473673f6158cc73e0e5dc122").get();
        System.out.println("BFS Path:");
        System.out.println();
        BFS(initialState,goal); //BFS implementation
        System.out.println();
        System.out.println("Dijkstra's path"); 
        System.out.println();
        Dijikstra(initialState,goal);   //Dijikstra implementation
    }

    private static void Dijikstra(State initialState, State goal) {
    	//Queue obj = new LinkedList<>();
    	PriorityQueueQuiz frontier = new PriorityQueueQuiz();
        Set<State> exploredSet = new HashSet<>();
        Map<State, State> parents = new HashMap<>();
    	
    	frontier.enqueue(initialState, 0);
    	exploredSet.add(initialState);
    	
    	while(!frontier.isEmpty()){
    	State firstNodeOfPriorityQueue = frontier.dequeue();
    	exploredSet.add(firstNodeOfPriorityQueue);
    	for(State eachNeibhour : Client.getState(firstNodeOfPriorityQueue.getId()).get().getNeighbors()){
			if(!exploredSet.contains(eachNeibhour)){
				Optional<DTO> stateTranscition = Client.stateTransition(firstNodeOfPriorityQueue.getId(), eachNeibhour.getId());
				int neibhourWeight = stateTranscition.get().getEvent().getEffect();
				int currentNodeWeight = frontier.getPriotiyOfState(firstNodeOfPriorityQueue);
				int combinedWeight = neibhourWeight + currentNodeWeight;
				if(frontier.getPriotiyOfState(eachNeibhour) == -1){
					frontier.enqueue(eachNeibhour, combinedWeight);
					parents.put(eachNeibhour,firstNodeOfPriorityQueue);
				}else{
					if(combinedWeight > frontier.getPriotiyOfState(eachNeibhour)){
						frontier.remove(eachNeibhour);
						frontier.enqueue(eachNeibhour, combinedWeight);	
						parents.put(eachNeibhour, firstNodeOfPriorityQueue);
					}
				}
			}
		}
    	}
    	findDepth(parents,goal,initialState);
        
		
	}

	private static void BFS(State initialState,State goal) {

        Queue<State> frontier = new LinkedList<>();
        Set<State> exploredSet = new HashSet<>();
        Map<State, State> parents = new HashMap<>();
        frontier.add(initialState);
        while (!frontier.isEmpty()) {
            State current = frontier.poll();
            exploredSet.add(current);
            if (current.equals(goal)){
                // construct actions from endTile
            	//parents.put(neighbor, current);
                findDepth(parents, current, initialState);
                isGoalReached = true;
                
            }
            // for every possible action
            for (State neighbor: Client.getState(current.getId()).get().getNeighbors()) {
            	if(isGoalReached)
            		break;
                if (!exploredSet.contains(neighbor)) {
                     parents.put(neighbor, current);
                     frontier.add(neighbor);
                 }
            }
            if(isGoalReached)
        		break;
        }
        if(!isGoalReached)
        	System.out.println("Not found solution");
        if(isGoalReached)
        	isGoalReached = true;
       
		
	}

	public static void findDepth(Map<State, State> parents, State current, State start) {
        State c = current;
        Stack<String> stack = new Stack<String>();
        int depth = 0;
        Optional<DTO> stateTranscition;
        while (!c.equals(start)) {
        	stateTranscition = Client.stateTransition(parents.get(c).getId(),c.getId() );
        	stack.push(parents.get(c).getLocation().getName() +"   "+parents.get(c).getId()+" : "+c.getLocation().getName()+ " : " +c.getId()+"    "+stateTranscition.get().getEvent().getEffect() );
            depth ++;
            c = parents.get(c);
        }
        while(!stack.empty()){
        	System.out.println(stack.pop());
        }
        
    }
}
