import java.util.*;
import java.io.*;

public class Main {
	static int T, N, K;
	static int[] value, volume, kanpsack_arr;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc < 1+T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			value = new int[N];
			volume = new int[N];
			kanpsack_arr = new int[K+1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				volume[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				for (int j = K; j >= volume[i]; j--) {
					kanpsack_arr[j] = Math.max(kanpsack_arr[j], kanpsack_arr[j-volume[i]] + value[i]);
				}
			}
			sb.append("#").append(tc).append(" ").append(kanpsack_arr[K]).append("\n");
		}
		System.out.println(sb);
	}
}