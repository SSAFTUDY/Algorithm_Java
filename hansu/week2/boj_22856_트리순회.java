import java.io.*;
import java.util.*;

class Tree{
	
	private class Node{
		public Node lc, rc;
	}
	
	private int size;
	private int circuitCnt;
	private int visitedCnt;
	private Node[] nodes;
	
	public Tree(int size) {
		this.size = size;
		nodes = new Node[size + 1];
		
		for (int i = 1; i <= size; i++)
			nodes[i] = new Node();
	}
	
	public void addChildren(int parent, int lc, int rc) {
		nodes[parent].lc = (lc > 1 ? nodes[lc] : null);
		nodes[parent].rc = (rc > 1 ? nodes[rc] : null);
	}
	
	public int getCircuitCnt() {
		this.circuitCnt = 0;
		this.visitedCnt = 0;
		
		pseudoInorderCircuit(nodes[1]);
		return this.circuitCnt - 1;
	}

	private void pseudoInorderCircuit(Node node) {
		visitedCnt++;
		circuitCnt++;

		if (node.lc != null) {
			pseudoInorderCircuit(node.lc);
			if (visitedCnt < size)
				circuitCnt++;
		}
		if (node.rc != null) {
			pseudoInorderCircuit(node.rc);
			if (visitedCnt < size)
				circuitCnt++;
		}
		
	}
	
}

public class Solution {
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Tree tree = new Tree(N);
        
        for(int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int parent = Integer.parseInt(st.nextToken()),
    			lc = Integer.parseInt(st.nextToken()),
    			rc = Integer.parseInt(st.nextToken());
 
        	tree.addChildren(parent, lc, rc);
        }
        
        System.out.println(tree.getCircuitCnt());
    }
    
}
