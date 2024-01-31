import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 출처 : https://loosie.tistory.com/226

public class Boj_1949 {

	static int[] population;
	static List<Integer>[] neighbor;
	static boolean[] checked;
	static int[][] dp;

	final static int NO = 0;
	final static int YES = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// get input
		int N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		checked = new boolean[N + 1];
		neighbor = new ArrayList[N + 1];
		dp = new int[N + 1][2];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			neighbor[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			neighbor[a].add(b);
			neighbor[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			dp[i][0] = -1;
			dp[i][1] = -1;
		}

		// process
		int max = Math.max(find(1, YES) + population[1], find(1, NO));
		
		// output
		System.out.println(max);
	}

	private static int find(int startIdx, int flag) {
		if(neighbor[startIdx].size() == 0) {
			return 0;
		}
		if(dp[startIdx][flag] != -1) {
			return dp[startIdx][flag];
		}
		
		dp[startIdx][flag] = 0;
		checked[startIdx] = true;
		for (int child : neighbor[startIdx]) {
			if(checked[child]) {
				continue;
			}
			if(flag == YES) {
				// 현재 우수마을인 경우
				dp[startIdx][YES] += find(child, NO);
			} else {
				// 현재 우수마을이 아닌 경우
				dp[startIdx][NO] += Math.max(find(child, YES) + population[child], find(child, NO));
			}
		}
		checked[startIdx] = false;
		return dp[startIdx][flag];
	}
}
