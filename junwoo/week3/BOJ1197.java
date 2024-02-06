import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int v, e, num1, num2, num3, ans, ttl;
    static List<int[]>[] nearby;
    static boolean[] node_visited;
    static PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o1[0] - o2[0]);
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        nearby = new ArrayList[v+1];
        for (int i = 1; i < v+1; i++) {
			nearby[i] = new ArrayList<int[]>();
		}
        node_visited = new boolean[v+1];
        for(int i = 1; i < e+1; i++) {
        	st = new StringTokenizer(bf.readLine());
        	num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
            num3 = Integer.parseInt(st.nextToken());
            nearby[num1].add(new int[] {num2, num3});
            nearby[num2].add(new int[] {num1, num3});
        }
        pq.add(new int[] {0, 1});
        while (ttl < v) {
        	int[] edge = pq.poll();
        	System.out.println(Arrays.toString(edge));
        	if (!node_visited[edge[1]]) {
        		for (int i = 0; i < nearby[edge[1]].size(); i++) {
        			if (!node_visited[nearby[edge[1]].get(i)[0]]) {
        				int vertex = nearby[edge[1]].get(i)[0];
        				int edge_val = nearby[edge[1]].get(i)[1];
        				pq.add(new int[] {edge_val, vertex});
        			}
				}
        		ans += edge[0];
        		node_visited[edge[1]] = true;
        		ttl++;
        	}
        }
        System.out.println(ans);
    }
}