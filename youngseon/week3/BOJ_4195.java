
import java.io.*;
import java.util.*;

public class BOJ_4195 {

	static HashMap<String, Integer> friend; // 친구 이름과 parent 에서의 index 저장
	static int[] parent = new int[100000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {

			int count = 0; // 현재 친구의 수
			for (int k = 0; k < 100000; k++) {
				parent[k] = k; // parent 배열 초기화
			}
			friend = new HashMap<String, Integer>();

			int F = Integer.parseInt(br.readLine());
			for (int j = 0; j < F; j++) {
				st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				String name2 = st.nextToken();

				int idx1 = 0, idx2 = 0; // idx1 -> 첫번째 친구의 parent 인덱스 , idx2 -> 두번째 친구의 parent 인덱스

				if (friend.containsKey(name1)) { // 첫번째 친구의 parent 인덱스 구하기
					idx1 = friend.get(name1);
				} else {
					friend.put(name1, count);
					idx1 = count;
					count++;
				}

				if (friend.containsKey(name2)) { // 같은 방식으로 두번째 친구도 parent 인덱스 구하기
					idx2 = friend.get(name2);
				} else {
					friend.put(name2, count);
					idx2 = count;
					count++;
				}

				union(idx1, idx2); // 대표노드 합치기
				
				int p = find(parent[idx1]);
				int result = 0;
				for (int l = 0; l < count; l++) { // 대표 노드가 같으면 친구 네트워크 인원 추가
					if(find(l) == p) {
						result++;
					}
				}
				System.out.println(result);
			}
			
		}
	}

	public static void union(int a, int b) { // union 연산
		a = find(a);
		b = find(b);
		if (a != b) {
			parent[b] = a;
		}
	}

	public static int find(int n) { // union 연산
		if (n == parent[n]) {
			return n;
		}
		return parent[n] = find(parent[n]);
	}
}
