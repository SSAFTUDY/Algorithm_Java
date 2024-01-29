import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA1248 {

    static class Node {

        List<Node> children;
        Node parent;
        int index;

        public Node(int index) {
            this.index = index;
            children = new ArrayList<>();
        }
    }

    /**
     * 공통 조상 찾는 함수
     * 두 정점의 루트까지 경로를 전부 list에 담은 뒤 앞에서부터 검사하며 찾음
     */
    private static int findParent(int a, int b) {
        List<Integer> lst1 = new ArrayList<>();
        List<Integer> lst2 = new ArrayList<>();
        while (a != 1) {
            a = parent[a];
            lst1.add(a);
        }

        while (b != 1) {
            b = parent[b];
            lst2.add(b);
        }

        for (Integer integer : lst1) {
            if (lst2.contains(integer)) {
                return integer;
            }
        }
        return 1;
    }

    /**
     * DFS로 자식 개수 카운트
     */
    private static int findSub(int p) {
        int cnt = 0;
        int[] visited = new int[list.length];
        visited[p] = 1;
        Stack<Node> stack = new Stack<>();
        stack.push(list[p]);
        while (!stack.isEmpty()) {
            cnt += 1;
            Node curr = stack.pop();
            visited[curr.index] = 1;
            for (Node node : curr.children) {
                if (visited[node.index] == 0) {
                    stack.push(node);
                }
            }
        }
        return cnt;
    }

    static int[] parent;
    static Node[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int v, e, a, b;

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken()); //정점의 개수
            e = Integer.parseInt(st.nextToken()); //간선의 개수
            a = Integer.parseInt(st.nextToken()); //정점1
            b = Integer.parseInt(st.nextToken()); //정점2
            list = new Node[v + 1];
            for (int i = 0; i < v + 1; i++) {
                list[i] = new Node(i);
            }

            parent = new int[v + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < e; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list[x].children.add(list[y]);
                list[y].parent = list[x];
                parent[y] = x;
            }
            int p = findParent(a, b);
            int s = findSub(p);
            System.out.println("#" + (t + 1) + " " + p + " " + s);
        }

    }
}
