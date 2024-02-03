import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 그룹 내에서는 index가 제일 작은 노드를 최상위 노드로 설정
 * 두 정점이 이어져 있으면, union 해준다.
 * input으로만 unionFind 하면, input 순서에 따라 그룹화가 제대로 되지 않을 수 있음 (제일 아래 반례)
 * 여행 경로로 비교할 때, find를 한 번 더 해줘서 위 문제를 해결
 * 처음에 unionFind 함수를 만들어 한 번에 진행했는데 위 문제 때문에 함수를 나눔
 */
public class Main {

    //루트 찾기
    static int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    //같은 그룹 정점 루트 같게 만들어주기
    static void union(int a, int b) {
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(find(i), find(j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int curr = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M - 1; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (find(curr) != find(next)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
/*
5
5
0 1 0 0 0 
1 0 0 0 1 
0 0 0 1 0 
0 0 1 0 1 
0 1 0 1 0 
3 5 4 2 1
YES
*/
