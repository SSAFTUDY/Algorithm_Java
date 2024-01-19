import java.io.*;
import java.util.NoSuchElementException;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static char[] arr;
	static MyLinkedList<Character> list = new MyLinkedList<>();
	
	static String solve() throws IOException {
		for (char c : arr) {
			list.addLast(c);
		}
		
		int cursor = 0; //커서의 초기 위치는 맨 앞글자의 왼쪽
		
		for (int i = 0; i < M; i++) {
			String oper = br.readLine();
			switch (oper.charAt(0)) {
				case 'L':
					if (cursor > 0) {
						cursor--;
					}
					break;
				case 'D':
					if (cursor < N) {
						cursor++;
					}
					break;
				case 'B':
					list.
					break;
				case 'P':
					break;
			}
		}
		
		return list.toString();
	}

	public static void main(String[] args) throws IOException {
		arr = br.readLine().toCharArray();
		N = arr.length;
		M = Integer.parseInt(br.readLine());
		System.out.println(solve());
	}
	
	static class Node<E> {
		
		E item;
		Node<E> prev;
		Node<E> next;
		
		Node(E item) {
			this.item = item;
			this.prev = null;
			this.next = null;
		}

		Node(E item, Solution.Node<E> prev, Solution.Node<E> next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}			
	}
	
	static class MyLinkedList<E> {
		
		private Node<E> first;
		private Node<E> last;
		private int size;
		
		public MyLinkedList() {
			this.first = null;
			this.last = null;
			this.size = 0;
		}
		
		//삽입
		public void addFirst(E item) {
			//새로 들어가는 노드의 다음을 first로 가리키고, first의 이전을 노드로 가리키고, first를 노드로 바꾸기
			Node<E> node = new Node<E>(item);
			node.next = this.first;
			
			if (this.first != null) {
				this.first.prev = node;
			}
			
			this.first = node;
			this.size++;
			
			if (this.first.next == null) {
				this.last = this.first;
			}
		}
		
		public void addLast(E item) {
			if (this.size == 0) {
				addFirst(item);
				return;
			}
			
			//last의 next를 새 노드를 가리키게 하고, 새 노드의 이전을 last로 하고, last를 새 노드로 바꾸기
			Node<E> node = new Node<E>(item);
			this.last.next = node;
			node.prev = this.last;
			this.last = node;
			this.size++;
		}
		
		public void add(int index, E item) {
			checkAddIndex(index);
			
			if (index == 0) {
				addFirst(item);
				return;
			}
			if (index == this.size) {
				addLast(item);
				return;
			}
			
			//추가하려는 위치의 이전 노드
			Node<E> pNode = search(index - 1);
			//추가하려는 위치의 노드
			Node<E> nNode = pNode.next;
			//추가하려는 노드
			Node<E> node = new Node<E>(item);
			
			pNode.next = null;
			nNode.prev = null;
			
			pNode.next = node;
			node.prev = pNode;
			nNode.prev = node;
			node.next = nNode;
			this.size++;
		}
		
		//삭제
		public E removeFirst() {
			Node<E> hNode = first;
			if (hNode == null) {
				throw new NoSuchElementException();
			}
			
			E e = hNode.item;
			Node<E> nNode = first.next;
			
			first.item = null;
			first.next = null;
			
			/**
			 * 리스트에 노드가 하나만 있던 경우(== 다음 노드가 null인 경우)
			 * nNode.prev를 참조할 수 없으므로 주의
			 */
			if (nNode != null) {
				nNode.prev = null;
			}
			
			first = nNode;
			this.size--;
			return e;
		}
		
		public E remove(int index) {
			checkElementIndex(index);
			
			if (index == 0) {
				E e = this.first.item;
				removeFirst();
				return e;
			}
			
			Node<E> pNode = search(index - 1);
			Node<E> node = pNode.next; //삭제할 노드
			Node<E> nNode = node.next;
			E e = node.item;
			
			pNode.next = null;
			node.next = null;
			node.prev = null;
			node.item = null;
			
			if (nNode != null) {
				nNode.prev = pNode;
				pNode.next = nNode;
			} else {
				this.last = pNode;
			}
			
			this.size--;
			return e;
		}
		
		//탐색
		public Node<E> search(int index) {
			checkElementIndex(index);
			
			//찾고자 하는 위치가 중간보다 뒤에 있으면 뒤부터 탐색
			if (index + 1 > this.size / 2) {
				Node<E> n = last;
				for (int i = size - 1; i > index; i--) {
					n = n.prev;
				}
				return n;
			} else {
				//아니라면 앞부터 탐색
				Node<E> n = first;
				for (int i = 0; i < index; i++) {
					n = n.next;
				}
				return n;
			}
		}
		
		//사이즈 가져오기
		public int size() {
			return this.size;
		}
		
		//현재까지 리스트 안에 들어있는 값 문자열로 반환
		public String toString() {
			if (this.first.item == null) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			Node<E> node = this.first;
			while (node.item != null) {
				sb.append(node.item);
				node = node.next;
			}
			
			return sb.toString();
		}
		
		
		//이 위치에 삽입이 가능한지 파악
		private void checkAddIndex(int index) {
			if (index < 0 || index > this.size) {
				throw new IndexOutOfBoundsException();
			}
		}
		
		//이 위치에서 노드를 탐색할 수 있는지 파악
		private void checkElementIndex(int index) {
			if (index < 0 || index >= this.size) {
				throw new IndexOutOfBoundsException();
			}
		}
		
	}

}
