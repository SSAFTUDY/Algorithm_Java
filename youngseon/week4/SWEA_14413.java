
import java.io.*;
import java.util.*;

public class SWEA_14413 {

	static char[][] arr;
	static int N, M;
	static boolean possible;
	static int sharp; // #의 인덱스 합이 홀수이면 -> 1, 짝수이면 -> 0
	static int dot; // #의 인덱스 합이 홀수이면 -> 1, 짝수이면 -> 0

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 0; t < T; t++) {
			boolean standard = false;

			possible = true;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			sharp = -1;
			dot = -1;

			arr = new char[N][M];
			for (int i = 0; i < N; i++) {

				String s = br.readLine();
				if (!standard) {
					if (s.indexOf("#") != -1) {
						sharp = (s.indexOf("#") + i) % 2; // 인덱스 row, col의 합이 짝수인지 홀수인지 판단
						standard = true;
					}
					if (s.indexOf(".") != -1) {
						dot = (s.indexOf(".") + i) % 2;
						standard = true;
					}
				}

				for (int j = 0; j < M; j++) {
					arr[i] = s.toCharArray();
				}
			}
			check();

			if (!possible) {
				sb.append("#").append(t + 1).append(" impossible\n");
			} else {
				sb.append("#").append(t + 1).append(" possible\n");
			}

		}
		System.out.println(sb);
	}

	private static void check() {
		if (sharp == -1 && dot == -1) {
			return;
		}
		if (sharp == dot) {
			possible = false;
			return;
		}
		if (sharp == 1) {
			dot = 0;
		}
		if (sharp == 0) {
			dot = 1;
		}
		if (dot == 1) {
			sharp = 0;
		}
		if (dot == 0) {
			sharp = 1;
		}
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '#') {
					if (!((i + j) % 2 == sharp)) {
						possible = false;
						return;
					}
				}
				if (arr[i][j] == '.') {
					if (!((i + j) % 2 == dot)) {
						possible = false;
						return;
					}
				}
			}
		}
	}
}
