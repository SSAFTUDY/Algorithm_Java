import java.io.*;
import java.util.*;

public class BOJ_2785 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int L = 0, R = N - 1;
		int result = 0;
		while (L < R) {
			if (--arr[L] == 0) {
				L++;
			}
			result++;
			R--;
		}
		System.out.println(result);
	}
}
