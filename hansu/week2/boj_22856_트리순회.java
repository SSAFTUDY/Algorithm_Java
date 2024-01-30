import java.io.*;
import java.util.*;


class Tree{

    /**
     * tree[parent][0] : 왼쪽 자식
     * tree[parent][1] : 오른쪽 자식 */
    private int[][] tree;
    private int circuitCnt; //이동 횟수
    private int visitedCnt; //(중위 순회시)방문 노드 수
    private final int SIZE;

    public Tree(int size) {
        SIZE = size;
        tree = new int[size + 1][];
    }

    public void addChildren(int parent, int lc, int rc) {
        tree[parent] = new int[]{lc, rc};
    }

    /** 이동경로를 반환하는 Wrapping 메소드 */
    public int getCircuitCnt() {
        this.circuitCnt = -1; //메인 알고리즘에서 0부터 시작하도록 세팅
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

        pseudoInorderCircuit(tree[node][0]); //왼쪽 subtree 순회
        if (visitedCnt++ == SIZE) {   //현재 노드 방문여부 체크
            return;                   //(모두 순회시 재귀 탈출)
        }
        pseudoInorderCircuit(tree[node][1]); //오른쪽 subtree 순회

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
