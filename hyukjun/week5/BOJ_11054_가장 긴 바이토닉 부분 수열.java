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
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] dpAsc = new int[n];
		int[] dpDesc = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// process
		// Asc
		for (int i = 0; i < n; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[i] > arr[j]) {
					max = Math.max(max, dpAsc[j]);
				}
			}
			dpAsc[i] = max + 1;
		}

		// Desc
		for (int i = n - 1; i >= 0; i--) {
			int max = 0;
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j]) {
					max = Math.max(max, dpDesc[j]);
				}
			}
			dpDesc[i] = max + 1;
		}

		// Bitonic
		int[] bitonic = new int[n];
		int max = -1;
		for (int i = 0; i < n; i++) {
			bitonic[i] = dpAsc[i] + dpDesc[i] - 1;
			max = Math.max(max, bitonic[i]);
		}

		// output

//		System.out.println(Arrays.toString(dpAsc));
//		System.out.println(Arrays.toString(dpDesc));
		System.out.println(max);
	}

}
