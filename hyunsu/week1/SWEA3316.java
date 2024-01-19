import java.io.*;
import java.util.*;

public class Solution {

	static final int A = 1, B = 2, C = 4, D = 8; //0001, 0010, 0100, 1000
	static String str;
	static long[][] dp;
	
	static int getABCD(char c) {
		switch(c) {
			case 'A':
				return 1;
			case 'B':
				return 2;
			case 'C':
				return 4;
			case 'D':
				return 8;
		}
		return 0;
	}

	static long solve() {
		long sum = 0L;
		int N = str.length();
		
		dp = new long[N + 1][16]; //ABCD가 각각 N일에 활동하는/활동하지 않는 경우의 수 총 16개
		dp[0][A] = 1; //맨 처음에는 A가 열쇠를 가지고 있음.
		
		List<Integer> prev = new ArrayList<>();
		//1일차에 나올 수 있는 경우의 수 -> 1일차에는 반드시 A를 포함하고 있어야 함
		for (int i = 1; i < 16; i++) {
			//A를 포함하면서 1일차의 책임자를 포함하는 경우의 수
			if ((i & A) != 0 && (i & getABCD(str.charAt(0))) != 0) {
				dp[1][i] = 1;
				prev.add(i);
			}
		}
		
		//2일차 이후
		for (int day = 1; day < N; day++) {
			List<Integer> cur = new ArrayList<>();
			//day일차의 책임자가 포함된 집합 만들기
			for (int j = 1; j < 16; j++) {
				if ((j & getABCD(str.charAt(day))) != 0) {
					cur.add(j);
				}
			}
			
			//day일과 day + 1일을 교집합하여 0이 나오지 않으면 가능한 경우 -> 이전날 있던 사람이 오늘 반드시 있어야 열쇠를 전달해줄 수 있으므로
			for (Integer c : cur) {
				for (Integer p : prev) {
					if ((c & p) != 0) {
						dp[day + 1][c] += (dp[day][p] % 1_000_000_007);
					}
				}
			}
			
			prev.clear();
			prev.addAll(cur); //이전날을 오늘로 업데이트
		}
		
		for (int i = 1; i < 16; i++) {
			sum += (dp[N][i] % 1_000_000_007);
		}
		return sum % 1_000_000_007;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			str = br.readLine();
			System.out.println("#" + tc + " " + solve());
		}

	}

}
