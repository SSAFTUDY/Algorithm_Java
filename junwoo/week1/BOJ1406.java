import java.util.*;
import java.lang.*;
import java.io.*;
/*
 * 여러 개의 객체가 필요 없는 경우
stateless 객체를 구별할 필요가 없는 경우 = 수정 가능한 멤버 변수가 없고 기능만 있는 경우
이런 객체를 stateless라고 한다.
객체를 생성/삭제하는데에 많은 비용이 들어서 재사용이 유리한 경우
 */

class LinkedList { 
	private Node head;
	private Node tail;
	private int size = 0;
	private Node cursor;
	
	LinkedList(String str) {
		Node node = new Node('\0', null, null);
		this.head = node;
		this.tail = node;
		for (int i = 0; i < str.length(); i++) {
			node = new Node(str.charAt(i), null, this.tail);
			this.tail = node;
			this.size += 1;
			node.prev.next = node;
		}
		this.cursor = tail;
	}
	
	private class Node {
		private char data;
		private Node next;
		private Node prev;
		
		public Node(char input, Node next, Node prev) {
			this.data = input;
			this.next = next;
			this.prev = prev;
		}
	}
	public void Left() {
		if (cursor != this.head) {
			this.cursor = cursor.prev;
		}
	}
	public void Right() {
		if (cursor != this.tail) {
			this.cursor = cursor.next;
		}
	}
	public void Delete() {
		if (cursor != this.head) {
			this.cursor.prev.next = this.cursor.next;
			if (cursor != this.tail) { 
				this.cursor.next.prev = this.cursor.prev;
			}
			else {
				this.tail = this.cursor.prev;
			}
			this.size -= 1;
			this.cursor = this.cursor.prev;
		}
	}
	public void Insert(char ch) {
		Node node = new Node(ch, this.cursor.next, this.cursor);
		if (cursor != this.tail) { 
			this.cursor.next.prev = node;
		}
		else {
			this.tail = node;
		}
		this.cursor.next = node;
		this.cursor = node;
		this.size += 1;
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		Node now = head.next;
		for (int i = 0; i < this.size; i++) {
			string.append(now.data);
			now = now.next;
		}
		return string.toString();
	}
	public char	cursorData() {
		System.out.println(this.cursor.prev.data);
		System.out.println(this.cursor.prev.next.data);

		return this.cursor.data;
	}
	public int getSize() {
		return this.size;
	}
	
}

public class Main {
    static StringBuilder string = new StringBuilder();
    static int n;
    static int now;
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        LinkedList linkedlist = new LinkedList(bf.readLine());
        n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            if (str.charAt(0) == 'L') {
            	linkedlist.Left();
            }
            else if (str.charAt(0) == 'D') {
            	linkedlist.Right();
            }
            else if (str.charAt(0) == 'B') {
            	linkedlist.Delete();
            }
            else if (str.charAt(0) == 'P') {
                char ch = str.charAt(2);
                linkedlist.Insert(ch);
            }            
        }
        System.out.println(linkedlist.toString());
    }
}



