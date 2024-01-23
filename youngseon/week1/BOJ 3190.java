
import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int snakeSize = 1;

		int[][] apples = new int[N + 1][N + 1];
		StringTokenizer st;
		int x, y;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			apples[x][y] = 1;
		}
		int L = Integer.parseInt(br.readLine());
		
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				System.out.print(apples[i][j]);
			}
			System.out.println();
		}

	}
}
