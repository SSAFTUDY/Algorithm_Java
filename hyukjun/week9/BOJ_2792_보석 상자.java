// 참고 링크
// ttps://kosaf04pyh.tistory.com/95
// https://velog.io/@wonjwi/%EB%B0%B1%EC%A4%80-2792%EB%B2%88-%EB%B3%B4%EC%84%9D%EC%83%81%EC%9E%90

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2792 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] arr = new long[M];
		long max = -1;
		long min = 1;
		for (int i = 0; i < M; i++) {
			arr[i] = Long.parseLong(br.readLine());
			max = Math.max(max, arr[i]);
		}

		// process
		// 이분탐색 조건문 헷갈림...
		long answer = -1;
		while (min <= max) {
			long mid = (min + max) / 2;
//			System.out.println(min + ", " + mid + ", " + max);

			long num = 0;
			for (int i = 0; i < M; i++) {
				num += arr[i] / mid;
				if (arr[i] % mid != 0) {
					num++;
				}
			}

			// num : mid로 보석들을 나누었을 때 나누어줄 수 있는 학생들의 수

			// 학생 수보다 나눈 수가 더 작은 경우
			// 보석을 받지 못하는 학생이 있어도 괜찮으므로 답이 될 수 있음
			// 더 적은 숫자로 나누어야 함
			if (num <= N) {
				max = mid - 1;
				answer = max;
			}
			// 학생 수보다 나눈 수가 더 많은 경우
			// 보석을 전부 나누어줄 수 없는 경우이므로 답이 될 수 없음
			// 더 큰 수로 나누어야 함
			else {
				min = mid + 1;
			}
		}

		// output
		System.out.println(answer + 1);
	}

}
