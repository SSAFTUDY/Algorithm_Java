import java.io.*;
import java.util.*;

public class BOJ22856 {

    static int N, cnt = 0;
    static List<Node>[] tree;
    static List<Integer> pos = new ArrayList<>();

    //1. 마지막에 방문하는 노드 찾기
    //2. 총 방문하는 간선 카운트하다가 마지막에 방문하는 노드면 종료
    static void dfs(int cur, int parent, boolean one) {
        for (Node n : tree[cur]) {
            //왼쪽 자식이 있다 -> 타고 들어가기
            if (n.left != -1) {
                dfs(n.left, cur, one);
                if (!one) {
                    cnt++;
                }
            }

            if (!one) {
                if (pos.get(pos.size() - 1) == cur) {
                    System.out.println(cnt);
                    return;
                }
                cnt++;
            } else {
                pos.add(cur);
            }

            if (n.right != -1) {
                dfs(n.right, cur, one);
                if (!one) {
                    cnt++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            tree[n].add(new Node(l, r));
        }

        dfs(1, 0, true);
        dfs(1, 0, false);
    }

    static class Node {

        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
