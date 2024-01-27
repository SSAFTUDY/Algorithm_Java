import java.util.*;
import java.io.*;

public class Main {
    static int[] arr; // 유니온 파인드를 위한 배열

    static int find_root(int x) {
        int cur = x;
        while(arr[cur] != cur){
            cur = arr[cur];
        }
        arr[x] = cur;
        return cur;
    }
    static void merge(int x, int y) {
        arr[x] = y;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        for(int i = 1; i < n+1; i++) { 
            arr[i] = i;
        }
        int m = Integer.parseInt(st.nextToken());
        int u, v, u_root, v_root;
        int ans = 0; // answer
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            u_root = find_root(u); // 대표 노드를 계산한다.
            v_root = find_root(v); // 대표 노드를 계산한다.
            if (u_root == v_root) {
                ans += 1; // 두 노드가 연결되어 있다면, 무조건 끊어야 하므로 ans++ 를 해준다.
            }
            else { 
                merge(u_root, v_root); // 서로 다른 그룹이라면 병합한다.
            }
        }
        System.out.println(n - 1 - m + (2 * ans)); // n -  1 - (m - ans) + ans == n - 1 - m + (2 * ans)
        /**
         * n - 1 : n 개 노드를 가지고 트리를 만들 때 필요한 엣지의 수
         * (m - ans) : m개의 엣지에서 트리를 만드는 데에 쓸모없는 엣지를 뺀 값. 즉, 실질적으로 트리를 구성하기에 필수적인 엣지의 수
         * ans : 끊는 데에 필요한 연산 수
         */
    }
}