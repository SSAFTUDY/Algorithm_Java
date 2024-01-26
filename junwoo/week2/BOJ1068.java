import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
public class Main {
	static int root;
	static int[] leaf; // 하위 트리의 리프 노드의 수 (본인이 리프 노드라면 1 값을 갖는다.)
	static int[] child; // 직속 자식의 수
	static int[] parent; // 부모의 index
 	
	public static void addNode(int i, int p) { // i : 추가되는 노드 num, p : 추가되는 노드의 부모 num
		if (p == -1 && child[i] == 0) { // 맨 처음 노드가 루트 노드일 경우
			return;
		}
		else if (p == -1 && child[i] > 0) { // 맨 처음 노드가 루트 노드가 아닐 경우
			return;
		}
		else { // 루트 노드가 아닐 경우
			parent[i] = p;
			child[p] += 1;
			int cur = p;
			if (child[p] > 1) { // 부모에 자식이 이미 하나 이상 있었을 경우
				while (cur != -1) {
					leaf[cur] += leaf[i];
					cur = parent[cur];
				}
			}
			else { // 부모에 자식이 하나도 없었을 경우
				while (cur != -1) {
					leaf[cur] += leaf[i] - 1;
					cur = parent[cur];
				}
			}
		}		
	}
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        leaf = new int[n];
        child = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) { parent[i] = -1; leaf[i] = 1;}
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
        	int node_num = Integer.parseInt(st.nextToken());
        	if (node_num == -1) {root = i;}
        	addNode(i, node_num);
//        	System.out.println(Arrays.toString(child));
		}
        int m = Integer.parseInt(in.readLine());
        if (m == root) {System.out.println(0); return;}
        int child_leaf_cnt = leaf[m];
        int parent_leaf_cnt = leaf[parent[m]];
        int ans;
        if (parent_leaf_cnt == child_leaf_cnt) {
        	ans = leaf[root] - child_leaf_cnt + 1;
        }
        else {
        	ans = leaf[root] - child_leaf_cnt;
        }
        System.out.println(ans);
//        System.out.println(Arrays.toString(parent));
//    	System.out.println(Arrays.toString(leaf));
//    	System.out.println(Arrays.toString(child));
    }
}