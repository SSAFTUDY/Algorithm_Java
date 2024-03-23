import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int TC = 0; TC < T; TC++) {
			// get input
			st = new StringTokenizer(br.readLine());
			int find = Integer.parseInt(st.nextToken());
			int classNum = Integer.parseInt(st.nextToken());
			int[][] arr = new int[4][classNum];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < classNum; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// process
//			1. 1번 반과 2번 반을 이용해서 만들 수 있는 모든 합 구하기
//			2. 3번 반과 4번 반을 이용해서 만들 수 있는 모든 합 구하기
//			3. 3번 반과 4번 반으로 만든 합 정렬하기
//			4. 1번 반과 2번 반을 이용해서 만든 모든 합을 반복문으로 순회
//			5. 3번 반과 4번 반의 값으로 만들 수 있는 값 이분 탐색으로 찾기
//			6. 이분 탐색이 끝난 후 left와 right 포인터가 가르키는 값을 비교
//			7. 만약 절대값이 작다면 그 값을 사용
//			8. 절대값이 같다면 더 작은 값을 사용
			int[] one = new int[classNum * classNum];
			int[] two = new int[classNum * classNum];

			// 1, 2
			int idx = 0;
			for (int i = 0; i < classNum; i++) {
				for (int j = 0; j < classNum; j++) {
					one[idx] = arr[0][i] + arr[1][j];
					two[idx] = arr[2][i] + arr[3][j];
					idx++;
				}
			}

			// 3
			Arrays.sort(two);

			// 4
			int result = 80_000_001;
			for (int i = 0; i < one.length; i++) {
				int oneValue = one[i];

				// 5
				int left = 0;
				int right = two.length - 1;
				boolean isFound = false;
				while (left <= right) {
					int mid = (left + right) / 2;

					if (oneValue + two[mid] == find) {
						isFound = true;
						break;
					}

					else if (oneValue + two[mid] > find) {
						right = mid - 1;
					}

					else {
						left = mid + 1;

					}
				}

				if (isFound) {
					result = find;
					break;
				} else {
					// 6, 7, 8
					// left
					if(left >= 0 && left < two.length) {
						if (Math.abs(result - find) > Math.abs(oneValue + two[left] - find)) {
							result = oneValue + two[left];
						} else if (Math.abs(result - find) == Math.abs(oneValue + two[left] - find)) {
							result = Math.min(result, oneValue + two[left]);
						}
					}
					
					// right
					if(right >= 0 && right < two.length) {
						if (Math.abs(result - find) > Math.abs(oneValue + two[right] - find)) {
							result = oneValue + two[right];
						} else if (Math.abs(result - find) == Math.abs(oneValue + two[right] - find)) {
							result = Math.min(result, oneValue + two[right]);
						}
					}
				}
			}
			
			// output
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}

}
