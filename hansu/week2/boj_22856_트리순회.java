import java.io.*;
import java.util.*;

class Tree{
	
	private class Node{
		public int n;
		public Node lc, rc;
		Node(int n){this.n=n;}
	}
	
	private int size;
	private int circuitCnt;
	private int visitedCnt;
	private Node[] nodes;
	
	public Tree(int size) {
		this.size = size;
		nodes = new Node[size + 1];
		
		for (int i = 1; i <= size; i++)
			nodes[i] = new Node(i);
	}
	
	public void addChildren(int parent, int lc, int rc) {
		nodes[parent].lc = (lc > 1 ? nodes[lc] : null);
		nodes[parent].rc = (rc > 1 ? nodes[rc] : null);
	}
	
	public int getCircuitCnt() {
		this.circuitCnt = -1;
		this.visitedCnt = 0;
		
		pseudoInorderCircuit(nodes[1]);
		return this.circuitCnt;
	}

	private void pseudoInorderCircuit(Node node) {
		circuitCnt++;
		visitedCnt++;
		
		if (node.lc != null)
			pseudoInorderCircuit(node.lc);
		if (node.rc != null)
			pseudoInorderCircuit(node.rc);
		
		if (visitedCnt == size)
			return;
		/*
		 * 1 2 3
		 * 2 4 5
		 * 3 6 7
		 * 4 -1 -1
		 * 5 -1 -1
		 * 6 -1 -1
		 * 7 -1 -1
		 * 마지막에 부모 가고 나서 끝나야됨
		 */
		circuitCnt++;
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
