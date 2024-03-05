package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_12764 {
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
	}

	static class Play implements Comparable<Play> {
		int start, end;

		public Play(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Play o) {
			return this.start - o.start;
		}

	}

	static class IdxAndEndTime implements Comparable<IdxAndEndTime> {
		int idx;
		int endTime;

		public IdxAndEndTime(int idx, int endTime) {
			super();
			this.idx = idx;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(IdxAndEndTime o) {
			return this.endTime - o.endTime;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		// get input
		int N = Integer.parseInt(br.readLine());
		Play[] plays = new Play[N];
		int maxTime = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			plays[i] = new Play(start, end);
			maxTime = Math.max(maxTime, end);
		}

		// process
		// 배열을 시작 순서로 정렬
		// 만약 nextUseIdx가 가르키는 배열의 start 값이 현재 시간과 같으면
		// Heap에서 숫자를 하나 꺼내 (이용 가능한 번호 중 가장 작은 번호)
		// Heap에서 꺼낸 값을 인덱스로 하는 useCount를 1 증가시키고
		// currentUse의 현재 nextUseIdx에 heap에서 꺼낸 값을 저장한다
		// 그런 후 현재 인덱스와 끝나는 시간을 end에 저장한다
		
		// 만약 end의 맨 위에 있는 값의 endTime이 현재 시간과 같다면
		// end를 poll한 후 해당 사람이 사용했던 숫자를 Heap에 다시 추가한다 

		Arrays.sort(plays);
		int[] useCount = new int[N];
		int[] currentUse = new int[N];
		int nextUseIdx = 0;
		Heap available = new Heap(N);
		PriorityQueue<IdxAndEndTime> end = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			available.add(i);
		}
		
		for (int time = 0; time <= maxTime; time++) {
			if(nextUseIdx < N && plays[nextUseIdx].start == time) {
				int useNum = available.poll();
				useCount[useNum]++;
				currentUse[nextUseIdx] = useNum;
				end.add(new IdxAndEndTime(nextUseIdx, plays[nextUseIdx].end));
				nextUseIdx++;
			}
			
			
			if(!end.isEmpty() && end.peek().endTime == time) {
				IdxAndEndTime get = end.poll();
				available.add(currentUse[get.idx]);
			}
			
		}
		
		// output
		int count = 0;
		for (int i = 0; i < N; i++) {
			if(useCount[i] != 0) {
				count++;
				sb.append(useCount[i]).append(' ');
			}
			else {
				break;
			}
		}
		
		sb.insert(0, '\n').insert(0, count);
		System.out.println(sb);
	}
}
