import java.io.*;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ3190 {

	static final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, L, dir, curTime = 0, curR, curC;
	static boolean[][] board;
	static MyLinkedList<int[]> snake = new MyLinkedList<>();

	static boolean isRanged(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}

	static boolean checkBompToBody(int r, int c) {
		//System.out.println("---현재 body---");
		for (int[] body : snake) {
			//System.out.println("(" + body[0] + ", " + body[1] + ")");
			if (body[0] == r && body[1] == c) {
				return true;
			}
		}
		return false;
	}

	static boolean checkGameOver(int r, int c) {
		return !isRanged(r, c) || checkBompToBody(r, c);
	}

	static void move() {
		switch (dir) {
		case UP:
			curR--;
			break;
		case DOWN:
			curR++;
			break;
		case LEFT:
			curC--;
			break;
		case RIGHT:
			curC++;
			break;
		}
	}

	static void turn(char c) {
		if (c == 'L') {
			if (dir == LEFT) {
				dir = DOWN;
			} else if (dir == RIGHT) {
				dir = UP;
			} else if (dir == UP) {
				dir = LEFT;
			} else {
				dir = RIGHT;
			}
		} else {
			if (dir == LEFT) {
				dir = UP;
			} else if (dir == RIGHT) {
				dir = DOWN;
			} else if (dir == UP) {
				dir = RIGHT;
			} else {
				dir = LEFT;
			}
		}
	}

	static void forward(boolean apple) {
		snake.addFirst(new int[] { curR, curC });
		board[curR][curC] = false;
		if (!apple) {
			snake.removeLast();
		}
	}

	static int solve() throws IOException {
		dir = RIGHT;
		curR = 1;
		curC = 1;
		snake.addFirst(new int[] { curR, curC });

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);

			while (true) {
				// 1. dir으로 한 칸 움직이기
				//System.out.println("현재 위치: (" + curR + ", " + curC + ") / " + curTime + "초" + " / " + "방향: " + dir);
				move();

				// 2. 벽이랑 부딪히는지 + 자신의 몸이랑 부딪히는지 판단
				if (checkGameOver(curR, curC)) {
					//System.out.println("부딪힘");
					return curTime + 1;
				}

				// 3. 사과가 있으면 머리만 한 칸 움직이기 / 사과가 없으면 머리와 꼬리 모두 한 칸 전진
				forward(board[curR][curC]);
				curTime++;
				
				if (curTime == x) {
					// x초가 되면 방향 변환하기
					//System.out.println((curTime) + "초에 방향 변환");
					turn(c);
					break;
				}
			}
		}
		
		move();
		while (!checkGameOver(curR, curC)) {
			//System.out.println("현재 위치: (" + curR + ", " + curC + ") / " + curTime + "초" + " / " + "방향: " + dir);
			forward(board[curR][curC]);
			move();
			curTime++;
		}

		return curTime + 1;
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		board = new boolean[N + 1][N + 1];
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x][y] = true;
		}

		L = Integer.parseInt(br.readLine());
		System.out.println(solve());
	}

	static class Node<E> {

		E item;
		Node<E> prev;
		Node<E> next;

		Node() {
		}

		Node(E item) {
			this.item = item;
			this.prev = null;
			this.next = null;
		}
	}

	// 이중 연결 리스트 구현
	static class MyLinkedList<E> implements Iterable<E> {

		private Node<E> head;
		private Node<E> tail;
		private int size;

		public MyLinkedList() {
			this.head = new Node<>();
			this.tail = head;
			this.size = 0;
		}

		// 삽입
		public void addFirst(E item) {
			this.tail = this.tail.next = new Node<>(item);
			this.size++;
		}

		// 삭제
		public void removeLast() {
			this.head = this.head.next;
			this.size--;
		}

		@Override
		public Iterator<E> iterator() {
			return new Iterator<>() {
				Node<E> node = head;

				@Override
				public boolean hasNext() {
					return node.next != null;
				}

				@Override
				public E next() {
					return (node = node.next).item;
				}
			};
		}

	}

}
