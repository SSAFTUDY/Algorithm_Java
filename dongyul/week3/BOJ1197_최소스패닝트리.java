import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /**
     * Kruskal 알고리즘
     * 시작점, 끝점, 가중치를 가지는 클래스 생성
     * 가중치가 작은 순으로 정렬
     * 최소인 가중치 부터 알고리즘 시작
     * 선택된 간선을 unionfind를 통해 이미 같은 집단인지 확인 (싸이클 확인)
     * 싸이클이 아니면 해당 간선을 선택, union을 통해 그룹 갱신
     */

    static class weight {

        int s;
        int e;
        int cost;

        public weight(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }

    static int v, e;
    static int[] parent;
    static weight[] weights;

    static int find(int x) {
        while (!(parent[x] == x)) {
            x = parent[x];
        }
        return x;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        parent = new int[v + 1];
        weights = new weight[e];

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            weights[i] = new weight(s, e, c);
        }

        int sum = 0;
        Arrays.sort(weights, (o1, o2) -> o1.cost - o2.cost); //가중치가 작은 순으로 정렬

        //크루스칼 알고리즘
        for (weight weight : weights) {
            if (!(find(weight.s) == find(weight.e))) {  //싸이클 여부
                sum += weight.cost;
                union(weight.s, weight.e);
            }
        }
        System.out.println(sum);

    }
}
