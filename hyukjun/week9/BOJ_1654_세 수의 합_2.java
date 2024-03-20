import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_2295 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get input
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		// process
		// x와 z로 만들 수 있는 모든 합을 생성한다
		// 합을 정렬한 후 그 합과 y값을 이용해서 arr[i]값을 만들 수 있는지 확인
		// 확인하는 과정은 이분탐색을 이용
		Arrays.sort(arr);
		for (int k = arr.length - 1; k >= 0; k--) {
			for (int z = k-1; z >= 0; z--) {
				int x = 0;
				
				while (x <= z) {
					if (arr[x] + arr[z] >= arr[k]) {
						break;
					}

					// 값 찾기
					// 만족하는 y값이 없으면 x 증가
					int bs = Arrays.binarySearch(arr, x, z + 1, arr[k] - (arr[x] + arr[z]));
					if (bs >= 0) {
						System.out.println(arr[k]);
						System.exit(0);
					} else {
						x++;
					}
				}
				
			}

		}
	}

}
