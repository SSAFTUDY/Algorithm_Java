
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MyLinkedList<E> {

	public Node<E> head;
	public Node<E> tail;
	static int size = 1;
	static int time = 1;

	static int head_x = 1;
	static int head_y = 1;
	static int tail_x = 1;
	static int tail_y = 1;

	static int dx = 0; // head의 방향 정보
	static int dy = 1;

	static int dir_index = 0;

	static void change_direction() { // 방향 변화 로직

		if (dir_index < Main.L) {
			if (Main.sec[dir_index] == time) {
				System.out.println("dir_index : "+ dir_index);
				String dir = Main.dir[dir_index];

				int tmp;
				if (dir.equals("D")) {
					if (dx == 1 || dx == -1) {
						
						tmp = dy;
						dy = dx;
						dx = tmp;
						dy = -dy;
					}
					else {
						tmp = dy;
						dy = dx;
						dx = tmp;
					}
					System.out.println("D");
				} else if (dir.equals("L")) {
					System.out.println("L");
					if (dy == 1 || dy == -1) {
						tmp = dy;
						dy = dx;
						dx = tmp;
						dx = -dx;
					}
					else {
						tmp = dy;
						dy = dx;
						dx = tmp;
					}
				} 
				dir_index++;
			}
		}
	}

	public MyLinkedList() {
		this.head = new Node<>();
		this.tail = new Node<>();
	}

	private static class Node<E> {

		private int x; // x축 위치 정보 필드
		private int y; // y축 위치 정보 필드
	
		private Node<E> next; // 다음 노드를 가리키는 필드

		public Node() {
		}

		public Node(int x, int y, Node<E> next) {
			super();
			this.x = x;
			this.y = y;
			
			this.next = next;
		}

		public String toString() { // 노드의 내용 출력
			return String.valueOf(time);
		}
	}

	public void add(int N) {
		Node<E> p = search(0); // 항상 head 앞에 새로운 노드 추가
		head = p;
		head.x = 1;
		head.y = 1;
		while (true) {
			int new_x = head_x + dx;
			int new_y = head_y + dy;
			if (new_x < 1 || new_x > N || new_y < 1 || new_y > N) {
				System.out.println("범위 넘어감");
				break;
			} else if (collid_body(new_x, new_y) == 1) {
				System.out.println("자기 몸에 부딪힘");
				break;
			} else {
				System.out.println("들어옴");
				head_x = new_x;
				head_y = new_y;
				System.out.println("head_x : "+ head_x);
				System.out.println("head_y : "+ head_y);
				System.out.println("size : "+ size);
				@SuppressWarnings("unchecked")
				Node<E> newNode = new Node(new_x, new_y, null); // 새 노드 생성 (바로 다음 노드와 연결)
				newNode.next = head;
				head = newNode;
				
				if (Main.apple[head_x][head_y] == 1) {
					System.out.println("사과 있어요");
					Main.apple[head_x][head_y] = 0;
					
				} else {
					
					System.out.println("사과 없어요");
					remove();
					
				}
				change_direction();
				System.out.println("dir_x : "+ dx);
				System.out.println("dir_y : "+ dy);		
			}
			size++;
			time++;
			System.out.println("size : " + size);
			System.out.println("time : " + time);
			print();
		}

	}

	public void remove() {
		System.out.println("size is :"+ size);
		
		if(size ==2) {
			head.next = null;
			tail = head;
			tail_x = head.x;
			tail_y = head.y;
		}
		else {
			Node<E> p = search(size-1);	
			System.out.println("remove_x : "+ p.x +"remove_y : "+p.y);
			p.next = null;
			tail_x = p.x;
			tail_y = p.y;
		}
		size--;
	}

	public Node<E> search(int num) {
		Node<E> node = head;
		System.out.println("head_x : "+ head.x +"head.y :" + head.y);
		for (int i = 0; i < num; i++) {
			System.out.println("move_x : "+ node.x +"move_y : "+ node.y);
			
			node = node.next;
			
		}
		return node;
	}
	
	public void print() {
		Node<E> node = head;
		for(int i=0; i< size; i++) {
			System.out.println("index : " + (i+1) + "toString : "+ node.x + node.y);
			node = node.next;
		}
	}


	public int collid_body(int new_x, int new_y) { // 몸이랑 부딪히는지 파악 -> 자기 몸과 부딪히면 1 리턴, 아니면 0 리턴
		Node<E> node = head;

//		for (int i = 0; i < size - 1; i++) {
//			System.out.println("i = "+ i);
//			System.out.println("node_x = "+ node.x);
//			node = node.next;
//			System.out.println("node_x = " + node.x +"node_y = "+ node.y);
//			if (new_x == node.x && new_y == node.y) {
//				return 1;
//			}
//		}
		return 0;
	}
}

public class Main {
	public static int[][] apple;
	public static int[] sec;
	public static String[] dir;
	public static int L;

	public static void main(String[] args) throws IOException {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		apple = new int[N + 1][N + 1]; // 사과 위치 정보 -> 1로 나타냄
		StringTokenizer st;
		int x, y;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			apple[x][y] = 1;
		}

		L = Integer.parseInt(br.readLine());

		// 뱀의 방향 전환 정보

		sec = new int[L];
		dir = new String[L];
		for (int i = 0; i < L; i++) { // 방향 변화 초기화
			st = new StringTokenizer(br.readLine());
			sec[i] = Integer.parseInt(st.nextToken());
			dir[i] = st.nextToken();
		}

		list.add(N); // 초기 뱀의 위치
		System.out.println(list.time);
	}
}
