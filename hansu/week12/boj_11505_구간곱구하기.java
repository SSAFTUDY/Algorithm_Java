/**
 * 메모리: 97,528kb
 * 시간: 472ms
 */
import java.io.*;
import java.util.*;

class SegmentTree{

    private static final int P = 1_000_000_007;
    private final long[] tree;
    private final int leafLen, arrLen;

    public SegmentTree(int[] arr){
        arrLen = arr.length;
        leafLen = Integer.bitCount(arrLen) > 1 ? Integer.highestOneBit(arrLen) << 1 : arrLen;
        tree = new long[leafLen << 1];
        init(arr, 1, 1, leafLen);
    }

    public long getProdBetween(int from, int to){
        return getProdBetween(1, from, to, 1, leafLen);
    }

    public void update(int idx, int newVal){
        update(idx, newVal, 1, 1, leafLen);
    }

    private long init(int[] arr, int node, int start, int end){
        if (start > arrLen){
            return tree[node] = 1;
        }

        if (start == end){
            return tree[node] = arr[start - 1];
        }

        int mid = (start + end) / 2;
        long left = init(arr, node * 2, start, mid);
        long right = init(arr, node * 2 + 1, mid + 1, end);
        return tree[node] = left * right % P;
    }

    private long getProdBetween(int node, int from, int to, int start, int end){
        int mid = (start + end) / 2;
        long left = 1, right = 1;

        if (start >= from && end <= to){
            return tree[node];
        }

        if (from <= mid){
            left = getProdBetween(node * 2, from, to, start, mid);
        }

        if (to >= mid + 1){
            right = getProdBetween(node * 2 + 1, from, to, mid + 1, end);
        }
        return left * right % P;
    }

    private void update(int idx, int newVal, int node, int start, int end){
        if (start == end){
            tree[node] = newVal;
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid){
            update(idx, newVal, node * 2, start, mid);
        }
        else {
            update(idx, newVal, node * 2 + 1, mid + 1, end);
        }
        tree[node] = tree[node * 2] * tree[node * 2 + 1] % P;
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        for (int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1){
                segmentTree.update(b, c);
            } else {
                sb.append(segmentTree.getProdBetween(b, c)).append('\n');
            }
        }
        
        System.out.println(sb);
    }

}
