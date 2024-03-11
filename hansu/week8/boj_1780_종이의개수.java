/**
 * 메모리: 316,540kb
 * 시간: 748ms
 */
import java.io.*;
import java.util.*;

class Main {

	private static int[][] paper;
	private static int[] ans = new int[3];

	private static boolean isOnePiece(int i, int j, int size) {
		int kind = paper[i][j];

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (paper[i + x][j + y] != kind)
					return false;
			}
		}
		return true;
	}

	private static void divideAndConquer(int i, int j, int N) {
		if (isOnePiece(i, j, N)) {
			ans[paper[i][j] + 1]++;
			return;
		}

		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				divideAndConquer(i + N / 3 * a,j + N / 3 * b, N / 3);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divideAndConquer(0, 0, N);
		for (int n : ans) {
			System.out.println(n);
		}
	}

}
