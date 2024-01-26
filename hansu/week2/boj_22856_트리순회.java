import java.io.*;
import java.util.*;


class Tree{

    private final int SIZE;
    private int circuitCnt; //이동 횟수
    private int visitedCnt; //(중위 순회시)방문 노드 수
    private int[][] tree;

    public Tree(int size) {
        SIZE = size;
        tree = new int[size + 1][];
    }

    /**
     * tree[parent][0] : 왼쪽 자식
     * tree[parent][1] : 오른쪽 자식
     */
    public void addChildren(int parent, int lc, int rc) {
        tree[parent] = new int[]{lc, rc};
    }

    /** 메인 알고리즘을 담은 Wrapping 메소드 */
    public int getCircuitCnt() {
        //루트에서 출발할 때(메소드가 시작할 때)의 움직임 고려
        this.circuitCnt = -1;
        this.visitedCnt = 0;

        pseudoInorderCircuit(1);
        return this.circuitCnt;
    }

    /** 메인 알고리즘 */
    private void pseudoInorderCircuit(int node){
        if (node < 0){
            return;
        }

        //들어갈 때 체크
        circuitCnt++;

        pseudoInorderCircuit(tree[node][0]);
        if (visitedCnt++ == SIZE) return; //중위 순회 종료 조건
        pseudoInorderCircuit(tree[node][1]);

        //나갈 때 체크(순회 종료시 실행 X)
        if (visitedCnt < SIZE)
            circuitCnt++;
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Tree tree = new Tree(N);

        //parsing
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()),
                    lc = Integer.parseInt(st.nextToken()),
                    rc = Integer.parseInt(st.nextToken());
            tree.addChildren(parent, lc, rc);
        }

        //answer
        System.out.println(tree.getCircuitCnt());
    }

}
