package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

class MyLinkedList<E> {
	public Node<E> head;
	public Node<E> cursor;
	public int size;

	public MyLinkedList() {
		this.head = new Node<>();
		this.cursor = head;
		this.size = 0;
	}

	private static class Node<E> {
		private E data; // 데이터가 저장될 필드
		private Node<E> next; // 다음 노드를 가리키는 필드
		private Node<E> prev; // 이전 노드를 가리키는 필드
		

		public Node() {
		}

		public Node(E input, Node<E> prev, Node<E> next) {
			this.data = input;
			this.next = next;
			this.prev = prev;
		}

		public String toString() { // 노드의 내용 출력
			return String.valueOf(this.data);
		}
	}


	public void add(E value) {

		// 오른쪽 끝일 경우
		if (cursor.next == null) {
			Node<E> newNode = new Node<>(value, cursor, null); // 새 노드 생성 (바로 다음 노드와 연결)
			cursor.next = newNode;
			cursor = newNode;
			
		} else {
			//중간에 삽입하는 경우
			Node<E> newNode = new Node<>(value, cursor, cursor.next); // 새 노드 생성 (바로 다음 노드와 연결)
			cursor.next.prev = newNode;
			cursor.next = newNode;
			cursor = cursor.next;
	
		}
	
		size++;
	}


	public void remove() {

		// 인덱스가 0이면 removeFirst 메서드 실행하고 리턴
		if (cursor.prev != null) {
			if (cursor.next != null) {
				Node<E> del_node = cursor;  // 삭제할 노드
				del_node.data = null;
				
			
				del_node.prev.next = cursor.next;
				del_node.next.prev = cursor.prev;
				cursor = cursor.prev;
			} else {      // 맨 끝 문자 삭제시
				Node<E> del_node = cursor;  // 삭제할 노드
				del_node.data = null;
			
				del_node.prev.next = null;
				cursor = cursor.prev;
				
			}
			
			size--;

			
		}
		
		
	}

	public String toString() {
	  	StringBuilder sb = new StringBuilder();
	  	
      	Node<E> n = head.next;
      	while (n != null) {
          	sb.append(n.data);
          	n = n.next;
      	}

      	return sb.toString();
  	}

	

	public void moveLeft() {
		if(cursor.prev != null) {
			cursor = cursor.prev;	
		}
	}

	public void moveRight() {
		if(cursor.next != null) {
			cursor = cursor.next;	
		}
	}

}

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MyLinkedList<String> str = new MyLinkedList<String>(); // 문자열 저장할 list 생성
		char[] ch = br.readLine().toCharArray();

		for (int i = 0; i < ch.length; i++) {
			str.add(String.valueOf(ch[i])); // 문자 저장
		}

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			String inputCh = st.nextToken();
			if (inputCh.equals("P")) { // 입력 받은 문자가 P라면
				String addCh = st.nextToken(); // 추가할 문자 받고
				str.add(addCh);

			} else if (inputCh.equals("L")) {
				str.moveLeft();

			} else if (inputCh.equals("D")) {
				str.moveRight();
			} else {
				str.remove();
			}
//			System.out.println("cursor : "+ str.cursor);
//			System.out.println(str.toString());

		}

		System.out.println(str.toString());

	}

}
