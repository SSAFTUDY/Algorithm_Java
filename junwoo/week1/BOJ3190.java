import java.util.*;

import javax.print.DocFlavor.STRING;

import java.lang.*;
import java.io.*;



class linkedlist {
	Node head;
	Node tail;
	int size = 1;
	int dir;
	int[] dx = new int[] {-1, 0, 1, 0};
	int[] dy = new int[] {0, 1, 0, -1};
	int[][] snake_arr;
	int n;

	linkedlist(int n) { 
		Node node = new Node(0, 0, null, null);
		this.snake_arr = new int[n][n];
		this.n = n;
		this.head = node;
		this.tail = node;
		this.dir = 1;
		Node now = this.head;
		for (int i = 0; i < size; i++) {		
			snake_arr[now.loc[0]][now.loc[1]] = 1;
			now = now.next;
		}
	}
	
	class Node {
		int[] loc;
		Node next;
		Node prev;
		
		Node(int x, int y, Node next, Node prev) {
			this.loc = new int[] {x, y};
			this.next = next;
			this.prev = prev;
		}
	}
	
	int[] move_head() {
		int nx = this.head.loc[0] + dx[dir];
		int ny = this.head.loc[1] + dy[dir];
		if (0 <= nx && nx < n && 0 <= ny && ny < n && this.snake_arr[nx][ny] == 0) {
			Node node = new Node(nx, ny, this.head, null);
			this.head.prev = node;
			this.size += 1;
			this.snake_arr[nx][ny] = 1;
			this.head = node;
			return new int[] {nx, ny};
		}
		else {
			return new int[] {-1, -1};
		}
	}
	
	void delete_tail() {
		this.tail.prev.next = null;
		this.snake_arr[this.tail.loc[0]][this.tail.loc[1]] = 0;
		this.size -= 1;
		this.tail = this.tail.prev;	
	}
	
	void change_dir(char ch) {
		if (ch == 'L') {
			this.dir += 3;
			this.dir %= 4;
		}
		else if (ch == 'D') {
			this.dir += 1;
			this.dir %= 4;
		}
	}
	void tostring() {
		Node node = this.head;
		for (int i = 0; i < this.size; i++) {
			System.out.print(node.loc[0] + " " + node.loc[1] + " / ");
			node = node.next;
		}
	}
	
}

public class Main {
    static int n;
    static int apple_cnt;
    static int L;
    static int[][] arr;
    static int[] order; 
    static char[] direc;
    static int now_order;
    
    public static void main(String[] args) throws IOException{
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(bf.readLine());
    	arr = new int[n][n];
    	apple_cnt = Integer.parseInt(bf.readLine());
    	StringTokenizer st;
    	for (int i = 0; i < apple_cnt; i++) {
    		st = new StringTokenizer(bf.readLine());
    		int x = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
			arr[x-1][y-1] = 1;
		}
    	L = Integer.parseInt(bf.readLine());
    	order = new int[L + 1];
    	direc = new char[L];
    	now_order = 0;
    	for (int i = 0; i < L ; i++) {
    		st = new StringTokenizer(bf.readLine());
    		int t = Integer.parseInt(st.nextToken());
    		char ch = st.nextToken().charAt(0);
    		order[i] = t;
    		direc[i] = ch;
		}
    	order[L] = 10001;
//    	System.out.println(Arrays.deepToString(arr));
//    	System.out.println(Arrays.toString(order));
//    	System.out.println(Arrays.toString(direc));
    	
    	int time = 1;
    	linkedlist snake = new linkedlist(n);
    	int[] li;
    	while (true) {
    		li = snake.move_head();
    		if (li[0] != -1) {
    			if (arr[li[0]][li[1]] == 1) {
    				arr[li[0]][li[1]] = 0;
    			}
    			else {
    				snake.delete_tail();
     			}
    			if (order[now_order] == time) {
    				snake.change_dir(direc[now_order]);
    				now_order += 1;
    			}
    		}
    		else { 
    			break;
    		}
//    		System.out.println(time);
//    		snake.tostring();
//    		System.out.println();
    		time += 1;
    	}
    	System.out.println(time);
    }	
}




