import java.io.*;
import java.util.*;

class MyStack<T> implements Iterable<T> {

	private Object[] stack = new Object[10];
	private int top = 0;

	public boolean isEmpty() {
		return top == 0;
	}

	public void push(T val) {
		if (top == stack.length)
			stack = Arrays.copyOf(stack, (stack.length * 3) / 2 + 1);
		stack[top++] = val;
	}

	public T pop() {
		if (top == 0)
			throw new EmptyStackException();
		return (T) stack[--top];
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<>() {

			private int cur = 0;
			
			@Override
			public boolean hasNext() {
				return cur < top;
			}

			@Override
			public T next() {
				return (T) stack[cur++];
			}
			
		};
	}
	
}

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MyStack<Character> left = new MyStack<>(), right = new MyStack<>();
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < s.length(); i++) left.push(s.charAt(i));
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
				case "L":
					if (!left.isEmpty())
						right.push(left.pop());
					break;
				case "D":
					if (!right.isEmpty())
						left.push(right.pop());
					break;
				case "B":
					if (!left.isEmpty())
						left.pop();
					break;
				default:
					left.push(st.nextToken().charAt(0));
			}
		}
		for (char c : left) sb.append(c);
		while (!right.isEmpty()) sb.append(right.pop());
		System.out.println(sb);
	}
	
}
