import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


class Main{ //boj 1796
	static int[] start;
	static int[] end;
	static int mini = 100000000;
	static void dfs(int idx, int pos, int ttl) {
		int i = 0;
		for (i = idx; i < 123; i++) {
			if (start[i] > 0) {
				idx = i;
				break;
			}
		}
//		System.out.println(idx + " " + ttl);

		if (i > 122) {
			if (mini > ttl) {
				mini = ttl;
			}
			return;
		}
		dfs(idx+1, end[idx], ttl + Math.abs(pos - start[idx]) + Math.abs(start[idx] - end[idx]));
		dfs(idx+1, start[idx], ttl + Math.abs(pos - end[idx]) + Math.abs(start[idx] - end[idx]));
	}
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		start = new int[150];
		end = new int[150];
		String str = br.readLine();
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (start[ch] == 0) {
				start[ch] = i+1;
				end[ch] = i+1;
			} else {
				end[ch] = i+1;
			}
		}
//		for (int i = 97; i < 123; i++) {
//			if (start[i] > 0) {
//				System.out.println(start[i] + " " + end[i]);
//			}
//		}
		
		int idx = 97;
		for (int i = idx; i < 123; i++) {
			if (start[idx] > 0) {
				idx = i;
				break;
			}
		}
		dfs(idx+1, end[idx], end[idx]-1);
		System.out.println(mini + str.length());
	}
}