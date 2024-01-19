package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SWEA3316 {

	static int getBit(char ch) {
		switch (ch) {
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			int N = str.length();
			int[][] dp = new int[N + 1][16];
			int A = getBit('A');
			dp[0][A] = 1;

			for (int day = 1; day <= N; day++) {
				int hasKey = getBit(str.charAt(day-1));
				List<Integer> today = new ArrayList();

				for (int i = 1; i < 16; i++) { //오늘 key를 가지고 있는 사람이 반드시 참여하는 경우 리스트에
					if ((hasKey & i) != 0) {
						today.add(i);
					}
				}

				for (int i = 1; i < 16; i++) {
					for (Integer td : today) {
						if ((i & td) != 0) {	//전 날 모든 경우 & 오늘 가능한 경우 -> 0 나오면 겹치는 사람 x
							dp[day][td] += dp[day - 1][i]; 
							dp[day][td]%= 1000000007;
						}
					}
				}

			}

			int rst = 0;
			for (int i = 0; i < 16; i++) {
				rst+=dp[N][i]; 
				rst%=1000000007;
			}
			StringBuilder sb = new StringBuilder("#").append(t+1).append(" ").append(rst);
			System.out.println(sb);
		}
	}
}
