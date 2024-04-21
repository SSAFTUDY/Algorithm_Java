/**
 * 메모리: 105,584kb
 * 시간: 684ms
 */
import java.io.*;
import java.util.*;

class SegmentTree{

    private final int[] tree;
    private final int leafLen, arrLen;

    public SegmentTree(int[] arr){
        arrLen = arr.length;
        leafLen = Integer.highestOneBit(arrLen) << 1;
        tree = new int[leafLen << 1];
        init(arr, 1, 1, leafLen);
    }

    public char getFlagBetween(int from, int to){
        int flag = getFlagBetween(1, from, to, 1, leafLen);
        return flag != 0 ? flag > 0 ? '+' : '-' : '0';
    }

    public void update(int idx, int newVal){
        update(idx, newVal, 1, 1, leafLen);
    }

    private int getFlag(int num){
        return Integer.compare(num, 0);
    }

    private int init(int[] arr, int node, int start, int end){
        if (start > arrLen){
            return 0;
        }

        if (start == end){
            return tree[node] = Integer.compare(arr[start - 1], 0);
        }

        int left = init(arr, 2 * node, start, (start + end) / 2);
        int right = init(arr, 2 * node + 1, (start + end) / 2 + 1, end);
        return tree[node] = left * right;
    }

    private int getFlagBetween(int node, int from, int to, int start, int end){
        int mid = (start + end) / 2;
        int left = 1, right = 1;

        if (start > to || end < from){
            return 1;
        }

        if (start >= from && end <= to){
            return tree[node];
        }

        if (from <= mid){
            left = getFlagBetween(node * 2, from, to, start, mid);
        }
        if (end >= mid + 1){
            right = getFlagBetween(node * 2 + 1, from, to, mid + 1, end);
        }
        return left * right;
    }

    private void update(int idx, int newVal, int node, int start, int end){
        if (start == end){
            tree[node] = getFlag(newVal);
            return;
        }

        if (idx <= (start + end) / 2) {
            update(idx, newVal, node * 2, start, (start + end) / 2);
        } else {
            update(idx, newVal, node * 2 + 1,(start + end) / 2 + 1, end);
        }
        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s;

        while ((s = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            SegmentTree segmentTree = new SegmentTree(arr);
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char a = st.nextToken().charAt(0);
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (a == 'C') {
                    segmentTree.update(b, c);
                } else {
                    sb.append(segmentTree.getFlagBetween(b, c));
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

}
