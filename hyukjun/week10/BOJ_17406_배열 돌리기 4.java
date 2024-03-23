import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] process;
	static int[][] arr;
	static int min = 50001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		process = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			process[i] = new int[] { r, c, s };
		}

		// process
		// 배열의 값은 각 행에 있는 모든 수의 합 중 최솟값
		// 회전 연산은 (r, c, s)로 구성
		// (r-s, c-s)부터 (r+s, c+s)로 구성된 정사각형을 시계방향으로 한 칸씩 돌린다는 의미
		// 회전 연산은 모두 한 번씩 사용해야 하며, 순서는 임의로 정해도 된다.

		// 순열 만들기
		boolean[] isChecked = new boolean[K];
		int[] saved = new int[K];
		makePermutation(0, K, isChecked, saved);

		// output
		System.out.println(min);
	}

	private static void makePermutation(int depth, int N, boolean[] isChecked, int[] saved) {
		if (N == depth) {
			// 배열 복사
			int[][] arrCopy = new int[arr.length][];
			for (int i = 0; i < arr.length; i++) {
				arrCopy[i] = Arrays.copyOf(arr[i], arr[i].length);
			}

			// 순서대로 실행
			for (int i : saved) {
				rotate(arrCopy, process[i][0], process[i][1], process[i][2]);
			}

			// 최솟값 계산
			int currentMin = minSum(arrCopy);
			min = Math.min(min, currentMin);
		} else {
			for (int i = 0; i < process.length; i++) {
				if (!isChecked[i]) {
					isChecked[i] = true;
					saved[depth] = i;
					makePermutation(depth + 1, N, isChecked, saved);
					isChecked[i] = false;
				}
			}
		}
	}

	private static int minSum(int[][] arr) {
		int min = 5001;
		for (int row = 1; row < arr.length; row++) {
			int sum = 0;
			for (int col = 1; col < arr[0].length; col++) {
				sum += arr[row][col];
				if (sum > min) {
					break;
				}
			}
			min = Math.min(min, sum);
		}
		return min;
	}

	private static void rotate(int[][] arr, int R, int C, int S) {
		int[] drow = { 0, 1, 0, -1 };
		int[] dcol = { 1, 0, -1, 0 };

		for (int s = S; s > 0; s--) {
			// leftUp = (R-s, C-s)
			// rightUp = (R-s, C+s)
			// rightDown = (R+s, C+s)
			// leftDown = (R+s, C-s)

			int dir = 0;
			int[] cord = new int[] { R - s, C - s }; // leftUp
			int save = arr[cord[0] + 1][cord[1]];
			do {
				int tmp = arr[cord[0]][cord[1]];
				arr[cord[0]][cord[1]] = save;
				save = tmp;
				cord[0] += drow[dir];
				cord[1] += dcol[dir];
				if (changeDir(cord, R, C, s)) {
					dir++;
				}

			} while (!(cord[0] == R - s && cord[1] == C - s));
		}
	}

	private static boolean changeDir(int[] cord, int R, int C, int S) {
		return (cord[0] == R - S && cord[1] == C - S) || (cord[0] == R - S && cord[1] == C + S)
				|| (cord[0] == R + S && cord[1] == C + S) || (cord[0] == R + S && cord[1] == C - S);
	}

}
