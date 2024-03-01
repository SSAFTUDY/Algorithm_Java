package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Heap {
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

		// 부모와 값을 비교하며 맞는 위치 찾기
		int currentIdx = insertIdx;

		// 내림차순 정렬일 때 부모보다 자신의 값이 크다면 교환, 오름차순 정렬일 때 부모보다 자신의 값이 작다면 교환
		while ((isDesc && currentIdx / 2 != 0 && array[currentIdx / 2] < array[currentIdx])
				|| (!isDesc && currentIdx / 2 != 0 && array[currentIdx / 2] > array[currentIdx])) {
			int tmp = array[currentIdx / 2];
			array[currentIdx / 2] = array[currentIdx];
			array[currentIdx] = tmp;
			currentIdx /= 2;
		}

		insertIdx++;
	}

	public int poll() {
		int value = array[1];

		// 다시 힙 구조 만들기
		insertIdx--;
		array[1] = array[insertIdx];
		int currentIdx = 1;

		// 내림차순 정렬일 때 자신의 자식 두개의 값 중 큰 값을 계산, 본인과 두 자식 중 큰 값 중 뭐가 더 큰지 비교
		// 만약 본인보다 값이 큰 자식이 있다면 위치를 교환
		// 오름차순 정렬일 때 자신의 자식 두개의 값 중 작은 값을 계산, 본인과 두 자식중 작은 값 중 뭐가 더 작은지 비교
		// 만약 본인보다 값이 작은 자식이 있다면 위치를 교환
		int childIdx = -1;
		while(true) {
			
			// 범위 확인
			if (currentIdx * 2 > insertIdx) {
				break;
			} else if (currentIdx * 2 + 1 > insertIdx) {
				childIdx = currentIdx * 2;
			} else {
				if (isDesc) {
					childIdx = array[currentIdx * 2] > array[currentIdx * 2 + 1] ? currentIdx * 2 : currentIdx * 2 + 1;
				} else {
					childIdx = array[currentIdx * 2] < array[currentIdx * 2 + 1] ? currentIdx * 2 : currentIdx * 2 + 1;
				}
			}

			// 값 비교
			if((isDesc && array[currentIdx] < array[childIdx]) || (!isDesc && array[currentIdx] > array[childIdx])) {
				int tmp = array[currentIdx];
				array[currentIdx] = array[childIdx];
				array[childIdx] = tmp;
				currentIdx = childIdx;
			}
			else {
				break;
			}
		}

		// output
		return value;
	}

	public boolean isEmpty() {
		return insertIdx == 1;
	}

	public boolean isFull() {
		return insertIdx > maxSize;
	}
}

public class BOJ_23757 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

//		PriorityQueue<Integer> gift = new PriorityQueue<>(Comparator.reverseOrder());
		Heap gift = new Heap(N, true);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			gift.add(Integer.parseInt(st.nextToken()));
		}

		int[] child = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			child[i] = Integer.parseInt(st.nextToken());
		}

		// debug
//		while (!gift.isEmpty()) {
//			System.out.println(gift.poll());
//		}

		// process
		boolean flag = false;
		for (int i = 0; i < child.length; i++) {
			int giftRem = gift.poll() - child[i];

			if (giftRem < 0) {
				flag = true;
				break;
			} else if (giftRem > 0) {
				gift.add(giftRem);
			}
		}

		// output
		System.out.println(flag ? 0 : 1);
	}

}
