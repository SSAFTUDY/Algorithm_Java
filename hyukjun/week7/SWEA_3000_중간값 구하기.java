// 메모리 : 100,836 kb, 실행시간 : 968 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3000 {

	static class Heap {
		private int[] array;
		private int maxSize;
		private int insertIdx;
		private boolean isDesc;

		public Heap(int size) {
			this(size, false);
		}

		public Heap(int size, boolean isDesc) {
			maxSize = size;
			array = new int[maxSize + 1];
			insertIdx = 1;
			this.isDesc = isDesc;
		}

		public void add(int value) {
			array[insertIdx] = value;

			int currentIdx = insertIdx;
			int parentIdx = currentIdx / 2;

			while (parentIdx != 0 && isLeftHasPriorityThanRight(array[currentIdx], array[parentIdx])) {
				int tmp = array[parentIdx];
				array[parentIdx] = array[currentIdx];
				array[currentIdx] = tmp;
				currentIdx = parentIdx;
				parentIdx = currentIdx / 2;
			}

			insertIdx++;
		}

		public int poll() {
			int value = array[1];

			insertIdx--;
			array[1] = array[insertIdx];
			int currentIdx = 1;

			int childIdx = -1;
			while (true) {

				if (currentIdx * 2 > insertIdx) {
					break;
				} else if (currentIdx * 2 + 1 > insertIdx) {
					childIdx = currentIdx * 2;
				} else {
					childIdx = isLeftHasPriorityThanRight(array[currentIdx * 2], array[currentIdx * 2 + 1])
							? currentIdx * 2
							: currentIdx * 2 + 1;
				}

				if (isLeftHasPriorityThanRight(array[childIdx], array[currentIdx])) {
					int tmp = array[currentIdx];
					array[currentIdx] = array[childIdx];
					array[childIdx] = tmp;
					currentIdx = childIdx;
				} else {
					break;
				}
			}

			return value;
		}

		private boolean isLeftHasPriorityThanRight(int left, int right) {
			if (isDesc) {
				return left > right;
			} else {
				return left < right;
			}
		}

		public boolean isEmpty() {
			return insertIdx == 1;
		}

		public boolean isFull() {
			return insertIdx > maxSize;
		}

		public int size() {
			return insertIdx - 1;
		}

		public int peek() {
			return array[1];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		final int REM = 20171109;

		int T = Integer.parseInt(br.readLine());
		for (int TC = 1; TC <= T; TC++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			Heap maxHeap = new Heap(N * 2 + 1, true);
			Heap minHeap = new Heap(N * 2 + 1);
			minHeap.add(count);

			int sum = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());

				minHeap.add(n1);
				maxHeap.add(minHeap.poll());
				minHeap.add(n2);
				if (minHeap.peek() < maxHeap.peek()) {
					minHeap.add(maxHeap.poll());
					maxHeap.add(minHeap.poll());
				}

				sum = (sum + minHeap.peek() % REM) % REM;
			}

			sb.append('#').append(TC).append(' ').append(sum).append('\n');
		}
		System.out.println(sb);
	}

}
