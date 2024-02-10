package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_14413 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int TC = 1; TC <= T; TC++) {
			sb.append('#').append(TC).append(' ');

			// get input
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

			char[][] arr = new char[N][M];
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					arr[i][j] = input.charAt(j);
				}
			}

			// process
			boolean flag;
			if (arr[0][0] == '#') {
				flag = find(arr, '#');
			} else if (arr[0][0] == '.') {
				flag = find(arr, '.');
			} else {
				flag = find(arr, '#') ? true : find(arr, '.');
			}

			if (flag)
				sb.append("possible").append('\n');
			else
				sb.append("impossible").append('\n');
		}
		System.out.println(sb);
	}

	// start에는 배열에서 가장 처음 시작되는 문자가 들어간다
	// 만약 start가 검은색으로 칠해져 있다고 가정
	// 행과 열의 인덱스를 합한 값이 짝수인 곳은 검은색으로 칠해져 있어야 함
	// 행과 열의 인덱스를 합한 값이 홀수인 곳은 흰색으로 칠해져 있어야 함
	// 처음 시작되는 문자가 ?일 경우에는 처음 시작 지점을 검은색으로 칠했을 때와 흰색으로 칠했을 때 두가지 경우로 나눔
	private static boolean find(char[][] arr, char start) {
		char evenSum = start;
		char oddSum;
		if (start == '#') {
			oddSum = '.';
		} else {
			oddSum = '#';
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if ((i + j) % 2 == 0 && arr[i][j] == oddSum) {
					return false;
				} else if ((i + j) % 2 == 1 && arr[i][j] == evenSum) {
					return false;
				}
			}
		}

		return true;
	}
}
