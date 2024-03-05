import java.io.*;
import java.util.*;

public class BOJ12764 {

    static int N, X;
    static int[] comIdx;
    static PQ<Time> times = new PQ<>();
    static PQ<Integer> nextCom = new PQ<>();
    static PQ<Computer> computers = new PQ<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
        - 다음 사람은 현재 빈 자리 중 가장 번호가 빠른 자리
        -> 이제 앉을 사람의 시작시간보다 빠른 종료시간을 가진 컴퓨터 자리 중 가장 작은 번호를 가진 컴퓨터
        -> 종료 시간에 관한 최소 힙 하나 / 컴퓨터 번호에 대한 최소 힙 하나 -> 총 2개의 최소 힙
        -> 추가적으로 
         */

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            times.add(new Time(p, q));
        }

        comIdx = new int[100_001];
        for (int i = 0; i < N; i++) {
            //
            while (!computers.isEmpty() && times.peek().p > computers.peek().end) {
                nextCom.add(computers.remove().idx);
            }

            if (nextCom.isEmpty()) {
                computers.add(new Computer(times.remove().q, X));
                comIdx[X]++;
                X++;
            } else {
                computers.add(new Computer(times.remove().q, nextCom.peek()));
                comIdx[nextCom.remove()]++;
            }
        }

        bw.write(X + "\n");
        for (int i = 0; i < X; i++) {
            bw.write(comIdx[i] + " ");
        }

        bw.flush();
        bw.close();
    }

    static class PQ<T extends Comparable<T>> {

        final int MAX_SIZE = 100_001; // 최대 크기 10만
        int size;
        T[] heap;

        public PQ() {
            this.size = 0;
            this.heap = (T[]) new Comparable[MAX_SIZE + 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        void add(T val) {
            if (size >= MAX_SIZE - 1) {
                throw new IndexOutOfBoundsException("Heap is full");
            }
            size++;
            int index = size;
            while (index > 1 && val.compareTo(heap[index / 2]) < 0) {
                heap[index] = heap[index / 2];
                index /= 2;
            }
            heap[index] = val;
        }

        public T remove() {
            if (size == 0) {
                throw new IndexOutOfBoundsException("Heap is empty");
            }
            T removed = heap[1];
            T last = heap[size];
            heap[size] = null;
            size--;

            int parent = 1;
            int child = 2;
            while (child <= size) {
                if (child < size && heap[child].compareTo(heap[child + 1]) > 0) {
                    child++;
                }
                if (last.compareTo(heap[child]) <= 0) {
                    break;
                }
                heap[parent] = heap[child];
                parent = child;
                child *= 2;
            }
            heap[parent] = last;
            return removed;
        }

        public T peek() {
            if (size == 0) {
                throw new IndexOutOfBoundsException("Heap is empty");
            }
            return heap[1];
        }
    }

    static class Time implements Comparable<Time> {

        int p, q;

        public Time(int p, int q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public int compareTo(Time o) {
            return Integer.compare(this.p, o.p);
        }
    }

    static class Computer implements Comparable<Computer> {

        int end, idx;

        public Computer(int end, int idx) {
            this.end = end;
            this.idx = idx;
        }


        @Override
        public int compareTo(Computer o) {
            return Integer.compare(this.end, o.end);
        }
    }
}
