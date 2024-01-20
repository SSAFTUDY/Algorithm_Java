import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_1230 {

	public static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
		}
	}

	public static class LinkedList {
		Node start;
		Node end;
		int size = 0;

		public LinkedList(int[] arr) {
			start = new Node(arr[0]);
			start.left = new Node(-1);
			start.left.right = start;
			
			Node before = start;
			Node tmp;
			for (int i = 1; i < arr.length; i++) {
				tmp = new Node(arr[i]);
				tmp.left = before;
				before.right = tmp;
				before = tmp;
			}
			end = before;
			size += arr.length;
		}

		public void insert(int cnt, int[] arr) {
			Node insertFrom = null;
			Node insertTo = null;

			insertFrom = start.left;
			for (int i = 0; i < cnt; i++) {
				if (insertFrom == null)
					return;
				insertFrom = insertFrom.right;
			}
			insertTo = insertFrom.right;

			Node before = insertFrom;
			Node tmp;
			for (int i = 0; i < arr.length; i++) {
				tmp = new Node(arr[i]);
				tmp.left = before;
				before.right = tmp;
				before = tmp;
			}
			if (cnt == 0) {
				start = insertFrom.right;
				start.left = new Node(-1);
				start.left.right = start;
			}

			if (insertTo == null) {
				end = before;
			} else {
				before.right = insertTo;
				insertTo.left = before;
			}
			size += arr.length;
		}

		public void delete(int cnt, int num) {
			Node deleteFirst = start;
			for (int i = 0; i < cnt; i++) {
				deleteFirst = deleteFirst.right; // deleteFirst는 처음 삭제할 Node를 가리키고 있음
				if (deleteFirst == null)
					return;
			}

			Node deleteLast = deleteFirst;
			for (int i = 1; i < num; i++) {
				deleteLast = deleteLast.right; // deleteLast는 삭제할 마지막 Node를 가리키고 있음
				if (deleteLast == null)
					return;
			}

			if (cnt + num >= size) {
				end = deleteFirst.left;
				deleteFirst.left.right = null;
			} else {
				deleteFirst.left.right = deleteLast.right;
				deleteLast.right.left = deleteFirst.left;
			}

			size -= num;
		}

		public void add(int[] arr) {
			Node before = end;
			Node tmp;
			for (int i = 0; i < arr.length; i++) {
				tmp = new Node(arr[i]);
				tmp.left = before;
				before.right = tmp;
				before = tmp;
			}
			end = before;
			size += arr.length;
		}

		public Node returnNode() {
			return start;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

//		for (int T = 1; T <= 10; T++) {
		for (int T = 1; T <= 1; T++) {
			sb.append("#").append(T).append(" ");

			// get input
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			LinkedList ll = new LinkedList(arr);

			// process
			br.readLine();
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				String order = st.nextToken();
				if (order.equals("I")) {
					int cnt = Integer.parseInt(st.nextToken());
					int num = Integer.parseInt(st.nextToken());
					int[] insert = new int[num];
					for (int i = 0; i < num; i++) {
						insert[i] = Integer.parseInt(st.nextToken());
					}
					ll.insert(cnt, insert);
				} else if (order.equals("D")) {
					int cnt = Integer.parseInt(st.nextToken());
					int num = Integer.parseInt(st.nextToken());
					ll.delete(cnt, num);
				} else if (order.equals("A")) {
					int num = Integer.parseInt(st.nextToken());
					int[] insert = new int[num];
					for (int i = 0; i < num; i++) {
						insert[i] = Integer.parseInt(st.nextToken());
					}
					ll.add(insert);
				}
			}

			// output
			Node start = ll.returnNode();
			for (int i = 0; i < 10; i++) {
				if (start == null)
					break;
				sb.append(start.value).append(" ");
				start = start.right;
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
