import java.io.*;
import java.util.*;

public class SWEA2930 {

    static final int MAX_SIZE = 1_000_000;
    static int T, N, size;
    static int[] heap;
    static StringBuilder sb;

    static void add(int x) {
        // root 1부터 시작
        heap[++size] = x;

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

    static int remove() {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb = new StringBuilder();
            heap = new int[MAX_SIZE + 1];
            size = 0;

            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                if (op == 1) {
                    int x = Integer.parseInt(st.nextToken());
                    add(x);
                } else {
                    sb.append(remove()).append(" ");
                }

            }

            bw.write("#" + tc + " " + sb.toString() + "\n");
        }

        bw.flush();
        bw.close();
    }

}
