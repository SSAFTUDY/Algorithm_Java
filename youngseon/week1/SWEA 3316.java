
import java.io.*;
import java.util.*;

public class Solution {          // 1 ~ 16까지 dp 배열을 만들어 경우의 수 갱신 (  여기서, CA - 0101 (5), DB - 1010 (10)   )

	static String manager;
	static int[][] dp;

	static int possibleCount() {

		for (int i = 1; i < 16; i++) {             //첫 날의 경우
			if ((i & 1) == 0) {                    // A가 없으면 패스
				continue;
			}
			if ((i & (1 >> (manager.charAt(0) - 'A'))) == 0) {    // 첫번째 매니저 포함 안되어 있으면 패스
				continue;
			}
			dp[0][i] = 1;
		}
		
		for (int i = 1; i < manager.length(); i++) {         // 해당 날짜까지    
			for (int j = 1; j < 16; j++) {                   // 다음 멤버 설정 
				for (int k = 1; k < 16; k++) {               // 이전 멤버 설정
					if ((j & (1 >> (manager.charAt(i) - 'A'))) == 0) {       // 매니저가 포함되어 있지 않으면
						continue;
					}
					if ((j & k) == 0) {   // 이전날과 다음 멤버가 겹치지 않을 경우
						continue;
					}
					dp[i][j] += dp[i - 1][k];
					dp[i][j] %= 1000000007;
				}
			}
		}
		int result = 0;
		for (int i = 1; i < 16; i++) {
			result += dp[manager.length() - 1][i];     // 마지막 날 가능한 경우 모두 더해 결과값 도출
			result %= 1000000007;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			manager = br.readLine();
			dp = new int[manager.length() + 1][16];
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(i+1).append(" ").append(possibleCount());
			System.out.println(sb.toString());
		}

	}
}
