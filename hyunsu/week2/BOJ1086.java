import java.io.*;
import java.util.*;

public class BOJ1068 {

    static int N, rootIdx, remove, ans = 0;
    static int[] parents;
    static boolean[] visited;
    static void removeNode(int idx) {
        //idx번 노드를 삭제 == 해당 노드의 부모 연결 끊기
        parents[idx] = -99;

//        for (int parent : parents) {
//            System.out.print(parent + " ");
//        }
//        System.out.println();
        //idx를 부모로 가지는 노드들 타고 들어가서 다 연결 끊기
        for (int i = 0; i < N; i++) {
            if (parents[i] == idx) {
                removeNode(i);
            }
        }
    }

    static void countLeafNode(int idx) {
        //idx번째 노드를 리프 노드라고 가정하고 부모 찾아서 올라가기
        boolean isLeaf = true;
        visited[idx] = true;

        //부모가 -99 -> 연결끊긴 노드 -> 리프 아님
        if (parents[idx] != -99) {
            //부모타고 올라가서 부모의 자식들이 리프인지 확인
            for (int i = 0; i < parents.length; i++) {
                //idx번쨰 노드를 부모로 가진다 -> idx번째 노드는 리프 노드가 아님
                if (parents[i] == idx && !visited[i]) {
                    countLeafNode(i);
                    isLeaf = false;
                }
            }

            if (isLeaf) {
                ans++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parents = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
            if (parents[i] == -1) {
                rootIdx = i;
            }
        }

        remove = Integer.parseInt(br.readLine());
        removeNode(remove);

        visited = new boolean[N];
        countLeafNode(rootIdx);
        System.out.println(ans);
    }
}
