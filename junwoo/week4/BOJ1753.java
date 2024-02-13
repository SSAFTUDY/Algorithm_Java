import java.io.*;
import java.util.*;


public class Main {
	
	static class Node implements Comparable<Node>{
		int idx, cost;
		Node (int idx, int cost) {
			this.idx = idx; this.cost = cost;
		}
		@Override
        public int compareTo(Node n) {
            if (this.cost > n.cost) return 1;
            else return -1;
        }
	}
	
	static int n, m, start_node;
	static List<Node>[] neghibor;
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();
	static int[] dist;
	static BitSet bitset;
	
	public static void main(String [] args) throws IOException {
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = readInt();
		m = readInt();
		neghibor = new ArrayList[n];
		dist = new int[n]; bitset = new BitSet(n);
		Arrays.fill(dist, 3_000_000);
		for (int i = 0; i < n; i++) {
			neghibor[i] = new ArrayList<Node>();
		}
		start_node = readInt()-1;
		for (int i = 0; i < m; i++) {
//			st = new StringTokenizer(bf.readLine());
			int a = readInt()-1;
			int b = readInt()-1;
			int c = readInt();
//			for (Node v : neghibor[a]) {
//				if(v.idx == b) {if(c < v.cost) {v.cost = c;} continue;}
//			}
			neghibor[a].add(new Node(b, c));
		}
		pq.add(new Node(start_node, 0)); dist[start_node] = 0;
		while (!pq.isEmpty()) { // 
			Node ele = pq.poll();
//			System.out.println(Arrays.toString(dist));
//			System.out.println(bitset.cardinality());
			if (dist[ele.idx] < ele.cost) {continue;}
			bitset.set(ele.idx);
			if(bitset.cardinality() == n) {break;}
			for (Node v : neghibor[ele.idx]) {
				if (dist[v.idx] > ele.cost + v.cost) {
					dist[v.idx] = ele.cost + v.cost;
					pq.add(new Node(v.idx, dist[v.idx]));
				}
			}
//			System.out.println(Arrays.toString(dist));
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (dist[i] == 3_000_000) {sb.append("INF").append("\n");}
			else {sb.append(dist[i]).append("\n");}
		}
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
        int n = 0;
        boolean isNegative = false;
        while (true) {
            int input = System.in.read();
            if (input<=32)
                return isNegative ? n * -1 : n;
            else if(input=='-')
                isNegative = true;
            else
                n = (n<<3) + (n<<1) + (input&15);
        }
    }
}