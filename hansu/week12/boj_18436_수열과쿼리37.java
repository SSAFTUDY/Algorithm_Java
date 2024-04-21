/**
 * 메모리: 113,420kb
 * 시간: 752ms
 */
import java.io.*;
import java.util.*;

class SegmentTree{

    static class Node {
        int odd, even;

        public Node(int odd, int even) {
            this.odd = odd;
            this.even = even;
        }

        public static Node combine(Node n1, Node n2){
            if (n1 == null){
                return n2;
            }

            if (n2 == null){
                return n1;
            }

            return new Node(n1.odd + n2.odd, n1.even + n2.even);
        }
    }

    private final Node[] tree;
    private final int leafLen, arrLen;

    public SegmentTree(int[] arr){
        arrLen = arr.length;
        leafLen = Integer.highestOneBit(arrLen) << 1;
        tree = new Node[leafLen << 1];
        init(arr, 1, 1, leafLen);
    }

    public Node getMinIdx(int from, int to){
        return getAnswerBetween(1, from, to, 1, leafLen);
    }

    public void update(int idx, int newVal){
        update(idx, newVal, 1, 1, leafLen);
    }

    private Node init(int[] arr, int node, int start, int end){
        if (start > arrLen){
            return null;
        }

        if (start == end){
            return tree[node] = new Node(arr[start - 1] % 2, (arr[start - 1] + 1) % 2);
        }

        Node left = init(arr, 2 * node, start, (start + end) / 2);
        Node right = init(arr, 2 * node + 1, (start + end) / 2 + 1, end);
        return tree[node] = Node.combine(left, right);
    }

    private Node getAnswerBetween(int node, int from, int to, int start, int end){
        Node left = null, right = null;
        int mid = (start + end) / 2;

        if (start > to || end < from){
            return null;
        }

        if (start >= from && end <= to){
            return tree[node];
        }

        if (from <= mid){
            left = getAnswerBetween(node * 2, from, to, start, mid);
        }
        if (end >= mid + 1){
            right = getAnswerBetween(node * 2 + 1, from, to, mid + 1, end);
        }
        return Node.combine(left, right);
    }

    private void update(int idx, int newVal, int node, int start, int end){
        if (start == end){
            tree[node].even = (newVal + 1) % 2;
            tree[node].odd = newVal % 2;
            return;
        }

        if (idx <= (start + end) / 2) {
            update(idx, newVal, node * 2, start, (start + end) / 2);
        } else {
            update(idx, newVal, node * 2 + 1,(start + end) / 2 + 1, end);
        }
        tree[node] = Node.combine(tree[node * 2], tree[node * 2 + 1]);
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1){
                segmentTree.update(b, c);
            } else {
                SegmentTree.Node node = segmentTree.getMinIdx(b, c);
                sb.append(a == 2 ? node.even : node.odd).append("\n");
            }
        }

        System.out.print(sb);
    }

}
