// 메모리: 29204kb, 	시간: 408

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		long[] arr = new long[N];

		long min = 1_000_000_001;
		long max = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}

		// process
		Arrays.sort(arr);
		max += K;
		long output = -1;
		while (min <= max) {
			long mid = (min + max) / 2;

			// 최대 팀 레벨 총합이 mid일 때 가능한가?
			// 가능하다면 min = mid + 1
			if (isPossible(arr, mid, K)) {
				output = mid;
				min = mid + 1;
			}

			// 가능하지 않다면 max = mid - 1
			else {
				max = mid - 1;
			}

		}
		
		// output
		System.out.println(output);
	}

	private static boolean isPossible(long[] arr, long mid, long add) {
		
		for (int i = 0; i < arr.length; i++) {
			add -= (mid - arr[i]);
			if (add < 0) {
				return false;
			}
		}
		
		if(add >= 0)
			return true;
		return false;
	}

}
