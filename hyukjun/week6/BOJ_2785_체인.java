package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2785 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		int N = Integer.parseInt(br.readLine());
		List<Integer> chains = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			chains.add(Integer.parseInt(st.nextToken()));
		}

		// process
		Collections.sort(chains);
		int count = 0;
		while (chains.size() > 1) {
			if(chains.size() == 2) {
				count++;
				break;
			}
			int min = chains.get(0);
			int max1 = chains.get(chains.size() - 1);
			int max2 = chains.get(chains.size() - 2);

			chains.remove(chains.size() - 1);
			chains.remove(chains.size() - 1);
			chains.add(max1 + max2);
			min--;
			if (min == 0) {
				chains.remove(0);
			} else {
				chains.set(0, min);
			}
			count++;
		}

		// output
		System.out.println(count);
	}

}
