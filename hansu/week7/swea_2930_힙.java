/**
 * 메모리: 94,568kb
 * 시간: 362ms
 */
import java.io.*;
import java.util.*;

class MaxHeap{

    private int[] heap;
    private int size;

    public MaxHeap(int maxSize){
        heap = new int[Integer.highestOneBit(maxSize) << 1];
    }

    public void add(int val){
        heap[++size] = val;
        percolateUp();
    }

    public int remove(){
        if (isEmpty()){
            return -1;
        }

        percolateDown();
        return heap[size--];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void swap(int x, int y){
        heap[x] ^= heap[y];
        heap[y] ^= heap[x];
        heap[x] ^= heap[y];
    }

    private void percolateUp(){
        int idx = size;

        while (idx > 1 && heap[idx / 2] < heap[idx]){
            swap(idx / 2, idx);
            idx /= 2;
        }
    }

    private void percolateDown(){
        if (size == 1){
            return;
        }
        int idx = 1;

        swap(idx, size);
        while (2 * idx <= size - 1){
            int maxChild = 2 * idx;
            if (maxChild + 1 < size && heap[maxChild + 1] > heap[maxChild]) {
                maxChild += 1;
            }

            if (heap[idx] < heap[maxChild]) {
                swap(maxChild, idx);
                idx = maxChild;
            } else {
                break;
            }
        }
    }

}

public class Solution{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            int N = Integer.parseInt(br.readLine());
            MaxHeap heap = new MaxHeap(N);

            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());

                if (op == 1){
                    heap.add(Integer.parseInt(st.nextToken()));
                }
                else {
                    sb.append(heap.remove()).append(' ');
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

}
