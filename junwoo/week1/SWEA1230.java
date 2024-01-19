import java.util.*;
import java.lang.*;
import java.io.*;

class linkedlist {
	int size;
	Node head;
	Node tail;
	Node cursor; 
	
	linkedlist(int n, int[] arr) {
		for (int i = 0; i < n; i++) {
			Node node;
			if (i == 0) {
				node = new Node(arr[i], null, null);
				this.head = node;
				this.cursor = node;
				this.size += 1;
			}
			else {
				node = new Node(arr[i], null, this.cursor);
				this.cursor.next = node;
				this.cursor = node;
				this.size += 1;
			}
			if (i == n - 1) {
				this.tail = node;
			}
		}
		this.cursor = this.head;
	}
	class Node {
		int data;
		Node next;
		Node prev;
		
		Node(int data, Node next, Node prev) { 
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	void insert(int x, int y, int[] arr) {
		if (x == 0) {
			Node node = new Node(arr[0], this.cursor, null);
			this.head = node;
			this.cursor.prev = node;
			this.size += 1;
			this.cursor = this.head;
			for (int i = 1; i < y; i++) {
				node = new Node(arr[i], this.cursor.next, this.cursor);
				if (this.cursor.next == null) {
					this.tail = node;
				}
				else {
					this.cursor.next.prev = node;
				}
				this.cursor.next = node;
				this.cursor = node;
				this.size += 1;
			}
			this.cursor = this.head;
			return;
		}
		for (int i = 0; i < x - 1; i++) {
			this.cursor = this.cursor.next;
		}		
		for (int i = 0; i < y; i++) {
			Node node = new Node(arr[i], this.cursor.next, this.cursor);
			if (this.cursor.next == null) {
				this.tail = node;
			}
			else {
				this.cursor.next.prev = node;
			}
			this.cursor.next = node;
			this.cursor = node;
			this.size += 1;
		}
		this.cursor = this.head;
	}
	void delete(int x, int y) {
		if (x == 0) {
			for (int i = 0; i < y; i++) {
				this.cursor = this.head.next;
				this.cursor.prev = null;
				this.head = this.cursor;
				this.size -= 1;
			}
			return;
		}
		for (int i = 0; i < x - 1; i++) {
			this.cursor = this.cursor.next;
		}
		for (int i = 0; i < y; i++) { 
			if (i == y-1 && this.cursor.next.next == null) {
				this.cursor.next = null;
				this.tail = this.cursor;
				this.size -= 1;
			}
			else {
				this.cursor.next.next.prev = this.cursor;
				this.cursor.next = this.cursor.next.next;
				this.size -= 1;
			}
		}
		this.cursor = this.head;
	}
	void append(int y, int[] arr) {
		this.cursor = this.tail;
		for (int i = 0; i < y; i++) {
			Node node = new Node(arr[i], this.cursor.next, this.cursor);
			this.tail = node;
			this.cursor.next = node;
			this.cursor = node;
			this.size += 1;
		}
		this.cursor = this.head;
	}
	public String toString() {
		StringBuilder string = new StringBuilder();
		Node now = head;
		for (int i = 0; i < 10; i++) {
			string.append(now.data);
			string.append(" ");
			now = now.next;
		}
		return string.toString();
	}
}





public class Main {
    static int n;
    static int[] arr;
    static int order_num;
  
    public static void main(String[] args) throws IOException{
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	for (int tc = 1; tc < 11; tc++) {
    		n = Integer.parseInt(bf.readLine());
        	StringTokenizer st = new StringTokenizer(bf.readLine());
        	arr = new int[n];
        	for (int i = 0; i < n; i++) {
    			arr[i] = Integer.parseInt(st.nextToken());
    		}
        	linkedlist linkedlist = new linkedlist(n, arr);
        	order_num = Integer.parseInt(bf.readLine());
        	char ch;
        	int x;
    		int y;
    		int[] orders;
    		st = new StringTokenizer(bf.readLine());
        	for (int i = 0; i < order_num; i++) {
        		ch = st.nextToken().charAt(0);
        		if (ch == 'I') {
    				x = Integer.parseInt(st.nextToken());
    				y = Integer.parseInt(st.nextToken());
    				orders = new int[y];
    				for (int j = 0; j < y; j++) {
    					orders[j] = Integer.parseInt(st.nextToken());
    				}
    				linkedlist.insert(x, y, orders);
        		}
        		else if (ch == 'D') {
    				x = Integer.parseInt(st.nextToken());
    				y = Integer.parseInt(st.nextToken());
        			linkedlist.delete(x, y);
        		}
        		else if (ch == 'A') {
        			y = Integer.parseInt(st.nextToken());
        			orders = new int[y];
        			for (int j = 0; j < y; j++) {
        				orders[j] = Integer.parseInt(st.nextToken());
    				}
        			linkedlist.append(y, orders);
        		}
    		}
        	String str = linkedlist.toString();
        	System.out.printf("#%d %s\n", tc, str);
		}		
    }
}




