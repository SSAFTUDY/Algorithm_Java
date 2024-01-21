import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Boj_3190 {

	public static class Node {
		int row;
		int col;
		Node right;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public boolean equals(Node node) {
			return this.row == node.row && this.col == node.col;
		} 

	}

	public static class Snake {
		Node head;
		Node tail;

		public Snake() {
			head = new Node(0, 0);
			tail = head;
		}

		public void go(int row, int col) {
			tail = tail.right;
		}

		public void headGo(int row, int col) {
			Node next = new Node(row, col);
			head.right = next;
			head = next;
		}
		
		public boolean headCheck() {
			Node iter = tail;
			
			while (iter != null) {
				if(iter == head) {
					return true;
				} else if(iter.equals(head)) {
					return false;
				}
				
				iter = iter.right;
			}
			return true;
		}
	}

	private final static String tc = "6\r\n" + 
			"3\r\n" + 
			"3 4\r\n" + 
			"2 5\r\n" + 
			"5 3\r\n" + 
			"3\r\n" + 
			"3 D\r\n" + 
			"15 L\r\n" + 
			"17 D";
	
	public static int[] drow = { 0, 1, 0, -1 };
	public static int[] dcol = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new StringReader(tc));
		StringTokenizer st;

		// get input
		int N = Integer.parseInt(br.readLine());

		int K = Integer.parseInt(br.readLine());
		boolean[][] apple = new boolean[N][N];

		// index 0번부터 시작
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apple[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}

		int L = Integer.parseInt(br.readLine());
		int[] sec = new int[L];
		char[] direction = new char[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			sec[i] = Integer.parseInt(st.nextToken());
			direction[i] = st.nextToken().charAt(0);
		}

		// process
		Snake snake = new Snake();
		int snakeDir = 0;
		int checkDirIdx = 0;
		
		int time = 0;
		while (true) {
			int row = snake.head.row + drow[snakeDir];
			int col = snake.head.col + dcol[snakeDir];
			time++;
			
			if (row < 0 || col < 0 || row >= N || col >= N) {
				break;
			}
			
			snake.headGo(row, col);
			if(!snake.headCheck()) {
				break;
			}
			
			if(apple[row][col]) {
				apple[row][col] = false;
			} else {
				snake.go(row, col);
			}
			
			if(checkDirIdx < direction.length) {
				if(time == sec[checkDirIdx]) {
					if(direction[checkDirIdx] == 'L') {
						snakeDir = (snakeDir + 3) % 4;
					} else {
						snakeDir = (snakeDir + 1) % 4;
					}
					
					checkDirIdx++;
				}
			}
		}
		System.out.println(time);
	}

}
