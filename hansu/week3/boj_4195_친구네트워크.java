import java.io.*;
import java.util.*;

class Solution {
	
	/** 해당 노드가 루트일 때의 네트워크 크기(subtree 노드 수) */
	private static Map<String, Integer> networkSize;
	private static Map<String, String> parent;
	
	/** Union 연산 후 합쳐진 네트워크 크기(tree 노드 수) 반환 */
	private static int union(String a, String b) {
		String x = find(a), y = find(b);
		
		if (x.compareTo(y) < 0) {
			parent.put(y, x);
			networkSize.put(x, networkSize.get(x) + networkSize.get(y));
			return networkSize.get(x);
		}
		if (x.compareTo(y) > 0) {
			parent.put(x, y);
			networkSize.put(y, networkSize.get(x) + networkSize.get(y));
			return networkSize.get(y);
		}
		
		return networkSize.get(x);
	}
	
	private static String find(String x) {
		if (parent.get(x) == x) return x;
		return find(parent.get(x));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			int F = Integer.parseInt(br.readLine());
			
			networkSize = new HashMap<>();
			parent = new HashMap<>();
			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken(), f2 = st.nextToken();
				
				networkSize.putIfAbsent(f1, 1);
				networkSize.putIfAbsent(f2, 1);
				parent.putIfAbsent(f1, f1);
				parent.putIfAbsent(f2, f2);				
				
				sb.append(union(f1, f2)).append('\n');
			}
		}
		
		System.out.println(sb);
	}
	
}
