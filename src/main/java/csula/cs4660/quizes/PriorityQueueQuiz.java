package csula.cs4660.quizes;


import csula.cs4660.quizes.models.State;


class QStateQuiz{
	QStateQuiz next;
	State data;
	int priority;
	public QStateQuiz(State newData,int newPriority){
		data = newData;
		priority = newPriority;
		next = null;
	}
}

public class PriorityQueueQuiz{
	QStateQuiz head;
	
	public PriorityQueueQuiz(){
	head = null;
	}
	int getPriotiyOfState(State State){
		QStateQuiz currentState = head;
		while(currentState != null){
			if(currentState.data.equals(State)){
				return currentState.priority;
			}
			currentState = currentState.next;
		}
		return -1;
	}
	void remove(State State){
		QStateQuiz currentState = head;
		while(currentState.next != null){
			if(currentState.next.data == State){
				currentState.next = currentState.next.next;
				return;
			}
			currentState = currentState.next;
		}
		if(head.data.equals(State)){
			head = head.next;
		}
	}
	void enqueue(State State, int priority){
		QStateQuiz newState = new QStateQuiz(State, priority); 
		if(head == null){
			head = newState;
		}
		else{
			QStateQuiz currentState = head;
			while(currentState.next != null && currentState.next.priority > newState.priority ){
				currentState = currentState.next;
			}
			if(head == currentState){
				if(head.priority < newState.priority){
					newState.next = head;
					head = newState;
				}
				else{
					newState.next = head.next;
					head.next = newState;
				}
			}else{
				if(currentState.priority < newState.priority){
					newState.next = currentState;
					currentState = newState;
				}
				else{
					newState.next = currentState.next;
					currentState.next = newState;
				}
			}
		}
	}
	State dequeue(){
		if(head != null){
		State data = head.data;
		head = head.next;
		return data;
		}
		
		return null;
	}
	boolean isEmpty(){
		return head == null;
	}
}

