import java.io.*;
import java.util.*;

public class BOJ_10427 {

	static long[] arr;
	static long[] s;
	static int N;
	static long sum, minVal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new long[N + 1];
			s = new long[N + 1];
			sum = 0;

			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);

			for (int i = 1; i <= N; i++) {
				if (i == 1) {
					s[i] = arr[i]; // 초기값 설정
				} else {
					s[i] = s[i - 1] + arr[i]; // 누적합 구하기
				}
			}

			for (int i = 2; i <= N; i++) { // i: 뽑는 개수
				minVal = Long.MAX_VALUE;
				for (int j = i; j <= N; j++) {
					minVal = Math.min(minVal, arr[j] * i - (s[j] - s[j - i])); // 뽑힌 수 중 가장 큰 수 * i - (뽑힌 수 의 합)
				}
				sum += minVal;
			}
			System.out.println(sum);
		}
	}
}
