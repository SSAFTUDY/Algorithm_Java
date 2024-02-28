import java.io.*;
import java.util.*;

public class BOJ_2785 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}

		int result = 0;
		if (N == 2) { // n=2인 경우는 항상 결과가 2
			System.out.println(1);
		} else { // n이 3이상인 경우부터

			while (arr.size() > 2) {
				Collections.sort(arr);
				int first = arr.get(0);

				
				
				if (first <= arr.size() - 1) {
					arr.remove(0);
					int num = 0;
					N = arr.size();
					for (int i = N - 1; i >= N - first - 1; i--) {
						num += arr.get(i);
						arr.remove(i);
					}
					arr.add(num);
					result += first;
				}
				
				else {
					arr.remove(0);
					int num = 0;
					N = arr.size();
					for (int i = N - 1; i >= 1; i--) {
						num += arr.get(i);
						arr.remove(i);
					}
					arr.add(num);
					arr.add(first - N);
				}

			}

			if (arr.size() == 2) {
				System.out.println(result + 1);
			} else {
				System.out.println(result);
			}
		}
	}
}
