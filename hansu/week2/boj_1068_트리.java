import java.io.*;
import java.util.*;

public class Solution {

	private static int[] parent;
	private static int N;
	
	/** (재귀)
	 * 자기 자신을 부모로 갖게해서 getLeavesNum에서 제외되도록 함
	 * rmNode를 부모로 갖는 노드들도 재귀적으로 삭제 */
	private static void removeSubtree(int rmNode) {
		for (int i = 0; i < N; i++)
			if (parent[i] == rmNode)
				removeSubtree(i);
		
		parent[rmNode] = rmNode;
	}
	
	/** 부모가 아닌 노드의 개수를 구함 **/
	private static int getLeavesNum() {
		BitSet bs = new BitSet();
		
		// 0 ~ N-1중 누군가의 부모인 노드 삭제
		bs.set(0, N);
		for (int n : parent)
			if (n >= 0)
				bs.clear(n);
		
		// 남아있는 노드의 개수 반환
		return bs.cardinality();
	}
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rmNode = Integer.parseInt(br.readLine());
        
        // 초기화
        parent = new int[N];
        for (int i = 0; i < N; i++)
        	parent[i] = Integer.parseInt(st.nextToken());
        
        // solving
        removeSubtree(rmNode);
        System.out.println(getLeavesNum());
    }
    
}
