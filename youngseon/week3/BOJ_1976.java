import java.io.*;
import java.util.*;

public class BOJ_1976 {

	static int[] bossNode; // 대표 노드 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		bossNode = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			bossNode[i] = i;
		} // 대표 노드 초기화

		StringTokenizer st;
		int u, v, k;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int zeroOrOne = Integer.parseInt(st.nextToken());
				if (zeroOrOne == 1) {
					union(i, j);
				}
			}
		}

		int checkList[] = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			checkList[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < m - 1; i++) {    // 연결되어있는지 find 연산으로 확인
			if (find(checkList[i]) != find(checkList[i + 1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");

	}

	public static int find(int a) {  // find 연산
		if (a == bossNode[a]) {
			return a;
		}
		return bossNode[a] = find(bossNode[a]);
	}

	public static void union(int a, int b) {  // union 연산
		a = find(a);
		b = find(b);
		if (a != b) {
			bossNode[b] = a;
		}
	}
}
