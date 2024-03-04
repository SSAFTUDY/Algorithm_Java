/**
 * 메모리: 45,276kb
 * 시간: 2,796ms
 */
import java.io.*;
import java.util.*;

/** 제네릭 타입 T가 Comparable을 상속받는 타입이라고 명시 */
@SuppressWarnings("unchecked")
class Heap<T extends Comparable<T>>{

    private final Object[] heap;
    private int size;

    public Heap(int initialCapacity) {
        this.heap = new Object[initialCapacity + 1];
    }

    public void add(T val) {
        heap[++size] = val;
        shiftUp();
    }

    public T remove() {
        shiftDown();
        return (T) heap[size--];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int x, int y) {
        Object tmp = heap[x];
        heap[x] = heap[y];
        heap[y] = tmp;
    }

    private void shiftUp() {
        int idx = size;

        while (idx > 1 && ((T) heap[idx]).compareTo((T) heap[idx / 2]) < 0) {
            swap(idx, idx / 2);
            idx /= 2;
        }
    }

    private void shiftDown() {
        int idx = 1;

        swap(1, size);
        while (2 * idx < size) {
            int minChild = 2 * idx;
            if (minChild + 1 < size && ((T) heap[minChild + 1]).compareTo((T) heap[minChild]) < 0) {
                minChild += 1;
            }

            if (((T) heap[idx]).compareTo((T) heap[minChild]) < 0) {
                return;
            }
            swap(idx, minChild);
            idx = minChild;
        }
    }

}

public class Main {

    private static class Timestamp implements Comparable<Timestamp> {
        int start, end;

        public Timestamp(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Timestamp o) {
            return start - o.start;
        }
    }

    private static class Computer {
        int endTime, numOfUser;

        public Computer(int endTime, int numOfUser) {
            this.endTime = endTime;
            this.numOfUser = numOfUser;
        }
    }

    /** 각 컴퓨터의 {사용 종료 시간, 사용 횟수} 기록 */
    private static List<Computer> computers;

    private static void takeSeat(Timestamp timestamp) {
        //기존 컴퓨터 중 사용 가능한 컴퓨터 탐색
        for (Computer computer : computers) {
            if (computer.endTime <= timestamp.start) {
                computer.endTime = timestamp.end;
                computer.numOfUser++;
                return;
            }
        }

        //앉을 자리가 없다면 새 컴퓨터 추가
        computers.add(new Computer(timestamp.end, 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Heap<Timestamp> minHeap = new Heap<>(N);

        //parse inputs
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            minHeap.add(new Timestamp(start, end));
        }

        //run
        computers = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            Timestamp timestamp = minHeap.remove();
            takeSeat(timestamp);
        }

        //print
        sb.append(computers.size()).append('\n');
        for (Computer computer : computers) {
            sb.append(computer.numOfUser).append(' ');
        }
        System.out.println(sb);
    }

}
