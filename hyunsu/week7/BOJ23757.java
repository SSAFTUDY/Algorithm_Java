import java.io.*;
import java.util.*;

public class BOJ23757 {

    static int N, M;
    static PQ c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        c = new PQ();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int cur = Integer.parseInt(st.nextToken());

            // 가장 큰 선물이 현재 아이를 만족할 수 있는지 확인
            // -> 만족하면 (선물-아이) 값을 다시 PQ에 넣고, 만족 못하면 끝
            int present = c.remove();
            if (present < cur) {
                System.out.println(0);
                return;
            }
            c.add(present - cur);
        }

        System.out.println(1);
    }

    static class PQ {

        final int MAX_SIZE = 1_000_000;
        int size;
        int[] heap;

        public PQ() {
            this.size = 0;
            this.heap = new int[MAX_SIZE + 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(int x) {
            this.heap[++size] = x;

            int cur = size;
            while (cur > 1) {
                // 부모가 나보다 작으면 부모 아래로 내리기
                int parent = cur / 2;
                if (heap[parent] < heap[cur]) {
                    int tmp = heap[parent];
                    heap[parent] = heap[cur];
                    heap[cur] = tmp;
                    cur = parent;
                } else {
                    break;
                }
            }
        }

        public int remove() {
            if (size == 0) {
                return -1;
            }

            int root = heap[1]; // return할 root값 저장

            heap[1] = heap[size]; //
            heap[size--] = 0;
            int cur = 1, left = 2, right = 3;
            while (cur < size) {
                // 왼쪽이 더 큰 경우 왼쪽 값과 현재 값을 바꾼다.
                if (heap[left] > heap[right]) {
                    if (heap[left] <= heap[cur]) {
                        break;
                    }

                    int tmp = heap[cur];
                    heap[cur] = heap[left];
                    heap[left] = tmp;

                    cur = left;
                } else {
                    // 반대의 경우
                    if (heap[right] <= heap[cur]) {
                        break;
                    }

                    int tmp = heap[cur];
                    heap[cur] = heap[right];
                    heap[right] = tmp;

                    cur = right;
                }

                left = cur * 2;
                right = cur * 2 + 1;
            }

            return root;
        }
    }

}
