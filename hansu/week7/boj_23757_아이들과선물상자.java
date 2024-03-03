/**
 * 메모리: 34,156kb
 * 시간: 336ms
 */
import java.io.*;
import java.util.*;

class MaxHeap{

    int[] heap;
    int size;

    public MaxHeap(int capacity){
        heap = new int[capacity + 1];
    }

    public void add(int val){
        heap[++size] = val;
        percolateUp();
    }

    public int remove(){
        percolateDown();
        return heap[size--];
    }

    private void swap(int x, int y){
        int tmp = heap[x];
        heap[x] = heap[y];
        heap[y] = tmp;
    }

    private void percolateUp(){
        int idx = size;

        while (idx > 1 && heap[idx] > heap[idx / 2]){
            swap(idx, idx / 2);
            idx /= 2;
        }
    }

    private void percolateDown(){
        int idx = 1;

        swap(1, size);
        while (2 * idx < size){
            //오른쪽 자식이 존재하지 않거나 왼쪽 자식이 더 크면 왼쪽 선택, 아니면 오른쪽 선택
            int maxChild = (2 * idx + 1 >= size || heap[2 * idx] > heap[2 * idx + 1]) ?
                    2 * idx : 2 * idx + 1;

            if (heap[idx] > heap[maxChild]){
                break;
            }
            swap(idx, maxChild);
            idx = maxChild;
        }
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        MaxHeap maxHeap = new MaxHeap(N);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            maxHeap.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++){
            int gift = maxHeap.remove();
            int toGive = Integer.parseInt(st.nextToken());

            if (gift < toGive){
                System.out.println(0);
                return;
            }
            maxHeap.add(gift - toGive);
        }
        System.out.println(1);
    }

}
