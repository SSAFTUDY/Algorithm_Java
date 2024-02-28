import java.io.*;
import java.util.*;

public class Main{
	static int T, N;
	static int[] arr, s;
	

	
	public static void main(String[] args)throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc < T+1; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			s = new int[N+1]; s[1] = 0;
			for (int i = 1; i < N+1; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			for (int M = 2; M < N+1; M++) {
				int ttl = 0;
				for (int i = 1; i < M; i++) {
					ttl += arr[M] - arr[i];
				}
				int mini = ttl;
				for (int i = 1; i < N+1-M; i++) {
					ttl += (arr[i+ M] - arr[i+M-1]) * M;
					ttl -= arr[i+ M] - arr[i];
					if (mini > ttl) {
						mini = ttl;
					}
				}
				s[M] = mini;
			}
			int ttl = 0;
			for (int i = 1; i < N+1; i++) {
				ttl += s[i]; 
			}
			sb.append(ttl).append("\n");
		}
		System.out.println(sb);
	}
}