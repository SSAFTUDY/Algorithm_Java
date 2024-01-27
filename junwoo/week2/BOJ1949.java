import java.util.*;
import java.io.*;

class Tree {
    int size;
    List<Node> node_list;
    List<Integer> leaf_nodes;
    boolean[] visited;

    Tree (int n, int[] members) {
        size = n;
        visited = new boolean[n+1];
        leaf_nodes = new LinkedList<Integer>();
        this.node_list = new ArrayList<Node>();
        Node node = new Node(0);
        node_list.add(node);
        for (int i = 1; i < n+1; i++) {
            node = new Node(members[i]);
            node_list.add(node);
        }
    }

    class Node {
        int[] selected; //index 0 : 우수 마을로 선택되었을때, index 1 : 우수마을로 선택되지 않았을 때
        List<Integer> near_nodes;
        
        Node (int mem) {
            selected = new int[] {mem, 0};
            near_nodes = new LinkedList<Integer>();
        }
    }
    
    void findLeaf() { // 맨 처음 트리 생성시 leaf_nodes를 찾는 메소드
        for (int i = 1; i < size+1; i++ ) {
            if (node_list.get(i).near_nodes.size() == 1) {
                
                leaf_nodes.add(i);
            }
        }
    }
    
    void addEdge(int x, int y) {
        Node x_ = node_list.get(x);
        Node y_ = node_list.get(y);
        x_.near_nodes.add(y);
        y_.near_nodes.add(x);
    }

    void update_dp(int cur, int nxt) { // 현재 노드 cur를 이용해 다음 노드 nxt의 값을 업데이트
        Node node_cur = node_list.get(cur);
        Node node_nxt = node_list.get(nxt);
        
        node_nxt.selected[0] += node_cur.selected[1];
        if (node_cur.selected[0] > node_cur.selected[1]) {
            node_nxt.selected[1] += node_cur.selected[0];
        }
        else {
            node_nxt.selected[1] += node_cur.selected[1];
        }
        // 업데이트 후 서로 연결을 끊는다.
        node_cur.near_nodes.remove(Integer.valueOf(nxt));
        node_nxt.near_nodes.remove(Integer.valueOf(cur));
        // System.out.println(node_nxt.selected[0] + " " + node_nxt.selected[1]);
    }

    int update_main() {
        int cur = 0, nxt = 0;
        for (int i = 0; i < leaf_nodes.size(); i++) {
            cur = leaf_nodes.get(i);
            while (node_list.get(cur).near_nodes.size() == 1) { // 갈림길이 나오기 전까지 계속 업데이트 한다.
                nxt = node_list.get(cur).near_nodes.get(0);
                update_dp(cur, nxt);
                cur = nxt;
            }
        }
        // 모든 노드를 지나고 마지막 업데이트 하는 노드의 selected[0], selected[1] 값을 비교해서 큰 값이 정답이 된다.
        if (node_list.get(cur).selected[0] > node_list.get(cur).selected[1]) {
            return node_list.get(cur).selected[0];
        }
        else {
            return node_list.get(cur).selected[1];
        }
    }
}

public class Main {
    static int[] members;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());
        members = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            members[i] = Integer.parseInt(st.nextToken());
        }
        Tree tree = new Tree(n, members);
        int x, y;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(bf.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            tree.addEdge(x, y);
        }
        // 여기까지 입력 및 트리 구현
        
        tree.findLeaf();
        int ans = tree.update_main();
        System.out.println(ans);
    }
}