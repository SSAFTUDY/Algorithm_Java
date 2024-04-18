/**
 * 메모리: 56,412kb
 * 시간: 592ms
 */
import java.io.*;
import java.util.*;

class SegmentTree{

    static class Node {
        int idx, val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        //두 노드 중 작은 노드 반환
        public static Node min(Node n1, Node n2){
            if (n1 == null && n2 == null){
                return null;
            }

            if (n1 == null || n2 == null){
                return n2 == null ? n1 : n2;
            }

            if (n1.val == n2.val) {
                return n1.idx < n2.idx ? n1 : n2;
            }
            return n1.val < n2.val ? n1 : n2;
        }

        @Override
        public String toString() {
            return idx + ":" + val;
        }
    }

    private final Node[] tree;
    private final int N;

    //리프노드가 arr인 완전이진트리 생성
    public SegmentTree(int[] arr){
        N = arr.length;
        tree = new Node[Integer.highestOneBit(N) << 2];
        init(arr, 1, 0, (Integer.highestOneBit(N) << 1) - 1);
    }

    //루트노드(tree[1])가 최소 노드
    public int getMinIdx(){
        return tree[1].idx;
    }

    public void update(int idx, int newVal){
        update(idx, newVal, 1, 0, (Integer.highestOneBit(N) << 1) - 1);
    }

    //재귀로 초기화
    //리프노드는 arr, 비리프노드는 arr의 [start,end] 범위 내의 최소 노드를 나타냄
    private Node init(int[] arr, int node, int start, int end){
        if (start >= arr.length){
            return null;
        }

        if (start == end){
            return tree[node] = new Node(start, arr[start]);
        }

        Node left = init(arr, 2 * node, start, (start + end) / 2);
        Node right = init(arr, 2 * node + 1, (start + end) / 2 + 1, end);
        return tree[node] = Node.min(left, right);
    }

    //재귀로 업데이트
    //리프노드까지 내려가 업데이트한 뒤 거슬러 올라오며 최소 노드 업데이트
    private void update(int idx, int newVal, int node, int start, int end){
        if (start == end){
            tree[node].val = newVal;
            return;
        }

        //수정할 인덱스 찾아가기
        if (idx <= (start + end) / 2) {
            update(idx, newVal, node * 2, start, (start + end) / 2);
        } else {
            update(idx, newVal, node * 2 + 1,(start + end) / 2 + 1, end);
        }
        tree[node] = Node.min(tree[node * 2], tree[node * 2 + 1]);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < tree.length; i++){
            sb.append(tree[i]).append(Integer.bitCount(i + 1) == 1 ? '\n' : ' ');
        }
        return sb.toString();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")){
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int newVal = Integer.parseInt(st.nextToken());
                segmentTree.update(idx, newVal);
            } else {
                sb.append(segmentTree.getMinIdx() + 1).append("\n");
            }
        }

        System.out.print(sb);
    }

}
