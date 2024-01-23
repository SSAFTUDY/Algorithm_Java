import java.io.*;
import java.util.StringTokenizer;

class MyLinkedList<E> {
	public Node<E> head;
	public int size;

	public MyLinkedList() {
		this.head = new Node<>();
		this.size = 0;
	}

	private static class Node<E> {
		private E data; // 데이터가 저장될 필드
		private Node<E> next; // 다음 노드를 가리키는 필드

		public Node() {
		}

		public Node(E input, Node<E> next) {
			this.data = input;
			this.next = next;
		}

		public String toString() { // 노드의 내용 출력
			return String.valueOf(this.data);
		}
	}

	public void add(int s, int value) {
		Node<E> p = search(s);
		Node<E> newNode = new Node(value, null); // 새 노드 생성 (바로 다음 노드와 연결)
		newNode.next = p.next;
		p.next = newNode;
		size++;
	}

	public void remove(int s) {
		Node<E> p = search(s);
		Node<E> del_node = p.next;
		del_node.data = null;

		p.next = del_node.next;
		size--;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		Node<E> n = head.next;
		for (int i = 0; i < 10; i++) {
			if (n != null) {
				sb.append(n.data).append(" ");
				n = n.next;
			}
		}

		return sb.toString();
	}

	public Node<E> search(int num) {
		Node<E> node = head;
		for (int i = 0; i < num; i++) {
			node = node.next;
		}
		return node;
	}
}

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M, x, y, s;

		for (int i = 0; i < 10; i++) {
			N = Integer.parseInt(br.readLine());

			MyLinkedList<Integer> str = new MyLinkedList<Integer>(); // 암호문 list 생성
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				str.add(j, Integer.parseInt(st.nextToken())); // 숫자 저장
			}

			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {

				String inputCh = st.nextToken();
				if (inputCh.equals("I")) {

					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());

					int arr[] = new int[y];
					for (int j = 0; j < y; j++) {
						s = Integer.parseInt(st.nextToken());
						arr[j] = s;
					}
					for (int j = 0; j < y; j++) {
						str.add(x, arr[y - j - 1]);
					}

				} else if (inputCh.equals("D")) {

					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());

					for (int j = 0; j < y; j++) {
						str.remove(x);
					}
				} else {

					y = Integer.parseInt(st.nextToken());

					for (int j = 0; j < y; j++) {
						s = Integer.parseInt(st.nextToken());
						str.add(str.size, s);
					}

				}
			}
			System.out.println("#" + (i + 1) + " " + str.toString());
		}

	}

}
