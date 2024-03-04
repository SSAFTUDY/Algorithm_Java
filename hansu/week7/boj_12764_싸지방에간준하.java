/**
 * 메모리: 49,172kb
 * 시간: 2,704ms
 */
import java.io.*;
import java.util.*;

class MinHeap<T>{
	
	Comparator<T> comparator;
	T[] heap;
	int size;
	
	public MinHeap(int initialCapacity, Comparator<T> comparator) {
		this.heap = (T[])new Object[initialCapacity + 1];
		this.comparator = comparator;
	}
	
	public void add(T val) {
		heap[++size] = val;
		percolateUp();
	}
	
	public T remove() {
		percolateDown();
		return heap[size--];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private void swap(int x, int y) {
		T tmp = heap[x];
		heap[x] = heap[y];
		heap[y] = tmp;
	}
	
	private void percolateUp() {
		int idx = size;
		
		while (idx > 1 && comparator.compare(heap[idx], heap[idx / 2]) < 0) {
			swap(idx, idx / 2);
			idx /= 2;
		}
	}
	
	private void percolateDown() {
		int idx = 1;
		
		swap(1, size);
		while (2 * idx < size) {
			int minChild = 2 * idx;
			if (minChild + 1 < size && comparator.compare(heap[minChild + 1], heap[minChild]) < 0) {
				minChild += 1;
			}
			
			if (comparator.compare(heap[idx], heap[minChild]) < 0) {
				return;
			}
			swap(idx, minChild);
			idx = minChild;
		}
	}
	
}

public class Main {
	
	/** 각 컴퓨터의 {사용 종료 시간, 사용 횟수} 기록 */
	private static int[][] computers;
	private static int computerCnt;
//	private static List<int[]> computers = new ArrayList<>();
	
	private static void takeSeat(int[] timestamp) {
		//기존 컴퓨터 중 사용 가능한 컴퓨터 탐색
		for (int i = 0; i < computerCnt; i++) {
			if (computers[i][0] <= timestamp[0]) {
				computers[i][0] = timestamp[1];
				computers[i][1]++;
				return;
			}
		}

		//앉을 자리가 없다면 새 컴퓨터 추가
		computers[computerCnt++] = new int[] {timestamp[1], 1};
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		MinHeap<int[]> minHeap = new MinHeap<>(N, Comparator.comparingInt(o -> o[0]));
		
		//parse inputs
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			minHeap.add(new int[] {start, end});
		}
		
		//run
		computers = new int[N][];
		computers[computerCnt++] = new int[2];
		while(!minHeap.isEmpty()) {
			int[] timestamp = minHeap.remove();
			takeSeat(timestamp);
		}
		
		//print
		sb.append(computerCnt).append('\n');
		for (int i = 0; i < computerCnt; i++) {
			sb.append(computers[i][1]).append(' ');
		}
		System.out.println(sb);
	}
	
}
