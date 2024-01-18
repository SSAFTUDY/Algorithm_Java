import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Swea_1288 {

	public static int bit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");

			// get input
			int multi = Integer.parseInt(br.readLine());

			// process
			int k = 0;
			bit = 0;

			while (bit != 1023) {
				k++;
				solve(multi * k);
			}
			sb.append(multi * k).append("\n");
		}
		System.out.println(sb);
	}

	public static void solve(int num) {
		char[] arr = Integer.toString(num).toCharArray();
		for (int i = 0; i < arr.length; i++) {
			bit = bit | (int) Math.pow(2, Character.getNumericValue(arr[i]));
		}
	}
}
