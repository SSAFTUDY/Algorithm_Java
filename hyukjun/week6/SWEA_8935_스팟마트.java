package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_8935 {

	static final String TT = "1\r\n" + "5\r\n" + "10\r\n" + "12\r\n" + "6\r\n" + "14\r\n" + "7\r\n" + "3\r\n" + "8\r\n"
			+ "1\r\n" + "2";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(TT));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int T = 1; T <= TC; T++) {
			sb.append('#').append(T).append(' ');

			// get input
			int N = Integer.parseInt(br.readLine());
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(br.readLine()));
			}

			int M = Integer.parseInt(br.readLine());
			List<Integer> add = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				add.add(Integer.parseInt(br.readLine()));
			}

			// process
			/*
			 살릴 수 있는 max값들을 우선적으로 살리기
			 각각의 idx를 저장 후 값으로 내림차순 정렬
			 값으로 정렬한 배열을 읽으면서 해당 위치와, 그 위치의 왼쪽 값과 넣을 배열의 최대값을 비교
			 만약 두 값 모두 배열의 최대 값보다 크다면 넣을 배열의 최소값을 해당 위치에 넣고 다음 값 찾기
			 만약 왼쪽 값이 최대값보다 작다면 pass
			 두 값이 최대값보다 작다면 ?
				 최대값을 살리면서, 기존 배열의 가장 작은 값을 버릴 수 있는 위치로?
			 */
			
			
			
			
			// output

		}

		System.out.println(sb);
	}

	/*
	 수열이 주어졌을 때 최대값을 찾기
	 d[i] = i까지의 최대 합
	 i-1번째는 선택할 수 없음
	 i-4번째의 값은 i-2번째에 고려되어 있기 때문에 고려하지 않아도 된다
	 d[i] = Math.max(dp[i-2], dp[i-3]) + arr[i];
	 최대값은 Math.max(dp[N-1], dp[N-2]);
	*/ 
	private static int getMax(List<Integer> list) {
		// init
		int N = list.size();
		int[] dp = new int[N];
		dp[0] = list.get(0);
		
		if (N > 1) {
			dp[1] = list.get(1);
		}
		if (N > 2) {
			dp[2] = list.get(2);
		}

		// process
		for (int i = 3; i < N; i++) {
			dp[i] = Math.max(dp[i - 2], dp[i - 3]) + list.get(i);
		}

		// return
		if (N == 1) {
			return dp[0];
		} else {
			return Math.max(dp[N - 1], dp[N - 2]);
		}
	}
}
