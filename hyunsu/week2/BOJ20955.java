import java.io.*;
import java.util.*;

public class BOJ20955 {

    static int N, M, cut = 0, group = 0;
    static int[] parent;

    //1. 두 뉴런이 하나의 사이클인지 확인 -> 맞으면 끊기
    //2. 개수 세면서 얘네가 하나의 트리인지 확인
    //-> 너무 union-find인데 문제 잘못 뽑았다;

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[b] = a;
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (find(u) == find(v)) {
                cut++;
            } else {
                union(u, v);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (parent[i] == i) {
                group++;
            }
        }

        System.out.println(cut + group - 1);
    }

}
