import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int TC = 0; TC < T; TC++) {
			flag = true;
			// get input
			String in = br.readLine();
			int[] arr = new int[in.length()];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = in.charAt(i) - '0';
			}

			// process
			solve(arr, 0, arr.length - 1);

			// output
			sb.append(flag ? "YES" : "NO").append('\n');
		}
		System.out.println(sb);
	}

	private static void solve(int[] arr, int start, int end) {
		
		if(arr.length == 1) {
			return;
		}

		// 현재 위치에서 확인
		int mid = (end - start) / 2 + start;
		for (int count = 0; count < (end - start) / 2; count++) {
			if (arr[start + count] != arr[end - count]) {
				// 접을 수 있음
			} else {
				flag = false;
				return;
			}
		}
		// 그 하위 요소까지 확인
		if (end - start > 1) {
			solve(arr, start, mid - 1);
			solve(arr, mid + 1, end);
		}
	}

}
