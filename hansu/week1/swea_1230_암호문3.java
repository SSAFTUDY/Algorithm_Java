import java.io.*;
import java.util.*;

class MyLinkedList<T> implements Iterable<T>{
	private int size;
	private Node head = new Node(), rear = head;

	private class Node {
		T val;
		Node next;

		Node() {}
		Node(T val) {this.val = val;}
	}

	public int size() {
		return size;
	}

	public T get(int idx) {
		if (idx < 0 || idx >= size) 
			throw new IndexOutOfBoundsException();
		
		Node node = head;

		while (idx-- > 0)
			node = node.next;
		return node.val;
	}

	public void add(T val) {
		rear = rear.next = new Node(val);
		size++;
	}
	
	public void add(T val, int idx) {
		if (idx < 0 || idx > size)
			throw new IndexOutOfBoundsException();
		if (idx == size) {
			add(val);
			return;
		}
		
		Node tmp, node = head;
		
		while (idx-- > 0)
			node = node.next;
		
		tmp = node.next;
		node.next = new Node(val);
		node.next.next = tmp;
		size++;
	}

	public T remove(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException();
		if (idx == size - 1) {
			T ret = rear.val;
			rear = null;
			size--;
			return ret;
		}
		
		T ret;
		Node node = head;

		while (idx-- > 1)
			node = node.next;
		ret = node.next.val;
		node.next = node.next.next;
		size--;
		return ret;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node node = head;

			@Override
			public boolean hasNext() {
				return node.next != null;
			}

			@Override
			public T next() {
				return (node = node.next).val;
			}
			
		};
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;

		for (int tc = 1; (s = br.readLine()) != null; tc++) {
			MyLinkedList<Integer> codes = new MyLinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(s);
			int M = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++)
				codes.add(Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			while (M-- > 0) {
				String command = st.nextToken();
				int idx, cnt;
				
				switch(command) {
					case "I":
						idx = Integer.parseInt(st.nextToken());
						cnt = Integer.parseInt(st.nextToken());
						
						for (int i = 0; i < cnt; i++)
							codes.add(Integer.parseInt(st.nextToken()), idx + i);
						break;
					case "D":
						idx = Integer.parseInt(st.nextToken());
						cnt = Integer.parseInt(st.nextToken());
						
						for (int i = 0; i < cnt; i++)
							codes.remove(idx);
						break;
					default:
						cnt = Integer.parseInt(st.nextToken());
						
						for (int i = 0; i < cnt; i++)
							codes.add(Integer.parseInt(st.nextToken()));
				}
			}

			Iterator<Integer> it = codes.iterator();
			sb.append('#').append(tc).append(' ');
			for (int i = 0; i < 10; i++)
				sb.append(it.next()).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
