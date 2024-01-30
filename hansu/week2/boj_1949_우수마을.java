import java.io.*;
import java.util.*;

public class Solution {
	
	/** DP배열 
	 * dp[i][0]: i번째 마을이 우수마을이 아닐 때의 최대 우수마을 인원 합
	 * dp[i][1]: i번째 마을이 우수마을일 때의 최대 우수마을 인원 합 */
	private static int[][] dp;
	private static int[] population;
	private static int N;
	private static List<Integer>[] adj;
	
	/** 값을 반환하는 Wrapping 메소드 */
	private static int getGreatePopulation() {
		runDPForEachBranches(0, 1);
	    return Math.max(dp[1][0], dp[1][1]);
	}
	
	/** (재귀)
	 * 분기(자식노드)마다 DP 실행 후 최댓값을 갖는 결과 각각 저장 */
	private static void runDPForEachBranches(int parent, int node) {
		// 리프노드인 경우 계산
		if (node > 1 && adj[node].size() == 1) {
			dp[node][1] = population[node];
			return;
		}
		
		// 분기마다 DP 실행
		for (int next : adj[node])
			if (next != parent)
				runDPForEachBranches(node, next);
		
		// 분기별 DP 종료 후 분기 결과 중 최댓값을 갖는 경우 선택
		int maxSum = 0;	 // 인접한 노드의 모든 경우의 수 중 최대 합
		int zeroSum = 0; // 인접한 노드가 모두 우수마을이 아닌 경우의 합
		for (int next : adj[node]) {
			maxSum += Math.max(dp[next][0], dp[next][1]); 
			zeroSum += dp[next][0];
		}
		
		dp[node][0] = maxSum;  					  // 안뽑힌다면: 주변 노드들의 최대 합
		dp[node][1] = zeroSum + population[node]; // 뽑힌다면  : 주변이 다 안뽑힌 경우의 합 + 자기마을 인원수
	}
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    		
	    //initializing
	    dp = new int[N + 1][2];
	    population = new int[N + 1];
	    adj = new List[N + 1];
	    for (int i = 1; i <= N; i++) {
	    	adj[i] = new ArrayList<>();
	    }
	    
	    //parsing
	    for (int i = 1; i <= N; i++)
	    	population[i] = Integer.parseInt(st.nextToken());
	    for (int i = 1; i < N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken());
	    	int b = Integer.parseInt(st.nextToken());
	    	
	    	adj[a].add(b);
	    	adj[b].add(a);
	    }
	    
	    System.out.println(getGreatePopulation());
    }
    
}
