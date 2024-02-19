import java.util.*;
import java.io.*;

public class Main {
	static int T;
	static String str1, str2;
	static int[][] lcs_map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc < 1+T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			str1 = st.nextToken();
			str2 = st.nextToken();
			lcs_map = new int[str1.length()+1][str2.length()+1];
			for (int i = 0; i < str1.length()+1; i++) {
				for (int j = 0; j < str2.length()+1; j++) {
					if (i == 0 || j == 0) {
						lcs_map[i][j] = 0; continue;
					}
					if (str1.charAt(i-1) == str2.charAt(j-1)) {
						lcs_map[i][j] = lcs_map[i-1][j-1]+1; continue;
					}
					lcs_map[i][j] = Math.max(lcs_map[i][j-1], lcs_map[i-1][j]); 
				}
			}
			sb.append("#").append(tc).append(" ").append(lcs_map[str1.length()][str2.length()]).append("\n");
		}
		System.out.println(sb);
	}
}