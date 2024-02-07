package ssaftudyweek3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 친구 관계가 생길때 마다 두 친구를 unionFind 해주면서 진행
 * 이 때, 각 그룹에 친구가 몇 명인지 알아야하고, union하면서 선택되는 그룹에는 나머지 그룹의 친구 수를 더해줌
 * input이 정수가 아니라서, HashMap을 통해 index 관리
 */
public class BOJ4195 {

	static int[] parent;
	static int[] count;	//그룹내의 정점 개수(최상위 정점만 값이 갱신 된다)

	static int find(int x) {
		while (x != parent[x]) {
			x = parent[x];
		}
		return x;
	}

	static int union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if (aP == bP) {	//이미 같은 그룹이면
			return count[aP];
		} else if (aP < bP) {
			parent[bP] = aP;	//parent[b] = aP 라 했다가 처음에 틀렸었음,union 할 때는 항상 최상위 부모를 바꿔줘야한다!
			count[aP] += count[bP];
			return count[aP];
		} else {
			parent[aP] = bP;
			count[bP] += count[aP];
			return count[bP];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int F = Integer.parseInt(br.readLine());

			parent = new int[F * 2]; //
			for (int i = 0; i < F * 2; i++) {
				parent[i] = i;
			}

			count = new int[F * 2];
			for (int i = 0; i < F * 2; i++) {
				count[i] = 1;
			}

			int idx = 0;
			Map<String, Integer> map = new HashMap<String, Integer>();	//이름 : index
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String str1 = st.nextToken();
				String str2 = st.nextToken();
				int a, b; // 두 input의 index
				if (map.get(str1) != null) {	//이미 나왔던 이름이면
					a = map.get(str1);
				} else {
					a = idx;
					map.put(str1, idx);
					idx++;
				}
				if (map.get(str2) != null) {
					b = map.get(str2);
				} else {
					b = idx;
					map.put(str2, idx);
					idx++;
				}
				sb.append(union(a, b)).append("\n");
			}
		}
		System.out.println(sb);
	}

}
