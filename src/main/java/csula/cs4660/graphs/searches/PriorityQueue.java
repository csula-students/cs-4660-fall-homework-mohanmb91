package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Node;


	class QNode{
		QNode next;
		Node data;
		int priority;
		public QNode(Node newData,int newPriority){
			data = newData;
			priority = newPriority;
			next = null;
		}
	}

	public class PriorityQueue{
		QNode head;
		
		public PriorityQueue(){
		head = null;
		}
		int getPriotiyOfNode(Node node){
			QNode currentNode = head;
			while(currentNode != null){
				if(currentNode.data.getData() == node.getData()){
					return currentNode.priority;
				}
				currentNode = currentNode.next;
			}
			return -1;
		}
		void remove(Node node){
			QNode currentNode = head;
			while(currentNode.next != null){
				if(currentNode.next.data.getData() == node.getData()){
					currentNode.next = currentNode.next.next;
					return;
				}
				currentNode = currentNode.next;
			}
			if(head.data.getData() == node.getData()){
				head = head.next;
			}
		}
		void enqueue(Node node, int priority){
			QNode newNode = new QNode(node, priority); 
			if(head == null){
				head = newNode;
			}
			else{
				QNode currentNode = head;
				while(currentNode.next != null && currentNode.next.priority < newNode.priority ){
					currentNode = currentNode.next;
				}
				if(head == currentNode){
					if(head.priority > newNode.priority){
						newNode.next = head;
						head = newNode;
					}
					else{
						newNode.next = head.next;
						head.next = newNode;
					}
				}else{
					if(currentNode.priority > newNode.priority){
						newNode.next = currentNode;
						currentNode = newNode;
					}
					else{
						newNode.next = currentNode.next;
						currentNode.next = newNode;
					}
				}
			}
		}
		Node dequeue(){
			if(head != null){
			Node data = head.data;
			head = head.next;
			return data;
			}
			
			return null;
		}
		boolean isEmpty(){
			return head == null;
		}
//		public static void main (String args[]){
//			PriorityQueue q = new PriorityQueue();
//			q.enqueue(new Node(4), 1);
//			q.enqueue(new Node(3), 2);
//			q.enqueue(new Node(2), 3);
//			q.enqueue(new Node(5), 1);
//			
//			q.remove(new Node(5));
//			q.remove(new Node(4));
//			q.remove(new Node(3));
//			q.remove(new Node(2));
//		}
	}
