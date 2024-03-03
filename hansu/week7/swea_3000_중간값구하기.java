/**
 * 메모리: 101,668kb
 * 시간: 825ms
 */
import java.io.*;
import java.util.*;

class Heap{

    boolean isMinHeap;
    int[] heap;
    int size;

    public Heap(int maxSize, boolean isMinHeap){
        heap = new int[Integer.highestOneBit(maxSize) << 1];
        this.isMinHeap = isMinHeap;
        
    }

    public void add(int val){
        heap[++size] = val;
        percolateUp();
    }

    public int remove(){
        percolateDown();
        return heap[size--];
    }

    public int element(){
        return heap[1];
    }

    private void swap(int x, int y){
        int tmp = heap[x];
        heap[x] = heap[y];
        heap[y] = tmp;
    }

    private void percolateUp(){
        int idx = size;

        while (idx > 1 && (heap[idx] - heap[idx / 2]) * (isMinHeap ? 1 : -1) < 0){
            swap(idx / 2, idx);
            idx /= 2;
        }
    }

    private void percolateDown(){
        int idx = 1;

        swap(1, size);
        while(2 * idx < size){
            int nextChild = 2 * idx;
            if (nextChild + 1 < size && (heap[nextChild + 1] - heap[nextChild]) * (isMinHeap ? 1 : -1) < 0){
                nextChild += 1;
            }

            if ((heap[idx] - heap[nextChild]) * (isMinHeap ? 1 : -1) < 0){
                break;
            }
            swap(idx, nextChild);
            idx = nextChild;
        }
    }

}

public class Solution{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), A = Integer.parseInt(st.nextToken());
            long sum = 0;
            Heap minHalf = new Heap(2 * N, false);
            Heap maxHalf = new Heap(2 * N, true);

            minHalf.add(A);
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken()), Y = Integer.parseInt(st.nextToken());

                minHalf.add(Math.min(X, Y));
                maxHalf.add(Math.max(X, Y));
                if (minHalf.element() > maxHalf.element()){
                    minHalf.add(maxHalf.remove());
                    maxHalf.add(minHalf.remove());
                }

                sum = (sum + minHalf.element()) % 20171109;
            }
            sb.append('#').append(tc).append(' ').append(sum).append('\n');
        }
        System.out.println(sb);
    }

}
