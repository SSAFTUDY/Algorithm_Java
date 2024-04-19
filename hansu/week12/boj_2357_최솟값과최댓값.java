/**
 * 메모리: 97,696kb
 * 시간: 648ms
 */
import java.io.*;
import java.util.*;

class SegmentTree{

    private final Node[] tree;
    private final int leafLen, arrLen;

    static class Node{
        int minVal, maxVal;

        public Node(int minVal, int maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

        public static Node getComparedNode(Node n1, Node n2){
            if (n1 == null){
                return n2;
            }

            if (n2 == null){
                return n1;
            }

            return new Node(Math.min(n1.minVal, n2.minVal), Math.max(n1.maxVal, n2.maxVal));
        }
    }

    public SegmentTree(int[] arr){
        arrLen = arr.length;
        leafLen = Integer.bitCount(arrLen) > 1 ? Integer.highestOneBit(arrLen) << 1 : arrLen;
        tree = new Node[leafLen << 1];
        init(arr, 1, 1, leafLen);
    }

    public Node getNodeBetween(int from, int to){
        return getNodeBetween(1, from, to, 1, leafLen);
    }

    private Node init(int[] arr, int node, int start, int end){
        if (start > arrLen){
            return null;
        }

        if (start == end){
            return tree[node] = new Node(arr[start - 1], arr[start - 1]);
        }

        return tree[node] = Node.getComparedNode(
                init(arr, node * 2, start, (start + end) / 2),
                init(arr, node * 2 + 1, (start + end) / 2 + 1, end)
        );
    }

    private Node getNodeBetween(int node, int from, int to, int start, int end){
        Node left = null, right = null;
        int mid = (start + end) / 2;

        if (from <= start && to >= end){
            return tree[node];
        }

        if (from <= mid){
            left = getNodeBetween(node * 2, from, to, start, mid);
        }
        if (to >= mid + 1){
            right = getNodeBetween(node * 2 + 1, from, to, mid + 1, end);
        }

        return Node.getComparedNode(left, right);
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        SegmentTree segmentTree;

        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        segmentTree = new SegmentTree(arr);
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            SegmentTree.Node node = segmentTree.getNodeBetween(from, to);
            sb.append(node.minVal).append(' ').append(node.maxVal).append('\n');
        }
        System.out.print(sb);
    }

}
