/**
 * 메모리: 127,936kb
 * 시간: 1,096ms
 */
import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
class Heap<T> implements Iterable<T>{
	
	private Comparator<T> comparator;
	private Object[] heap;
	private int size;
	
	public Heap(int initialCapacity, Comparator<T> comparator) {
		this.heap = new Object[initialCapacity + 1];
		this.comparator = comparator;
	}
	
	public void add(T val) {
		heap[++size] = val;
		shiftUp();
	}
	
	public T remove() {
		shiftDown();
		return (T) heap[size--];
	}
	
	public T element() {
		return (T) heap[1];
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
		
		while(idx > 1 && comparator.compare((T) heap[idx], (T) heap[idx / 2]) < 0) {
			swap(idx, idx / 2);
			idx /= 2;
		}
	}
	
	private void shiftDown() {
		int idx = 1;
		
		swap(1, size);
		while(2 * idx < size) {
			int minChild = 2 * idx;
			if (minChild + 1 < size && comparator.compare((T) heap[minChild + 1], (T) heap[minChild]) < 0) {
				minChild += 1;
			}
			
			if (comparator.compare((T) heap[idx], (T) heap[minChild]) < 0) {
				return;
			}
			swap(idx, minChild);
			idx = minChild;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int idx = 1;

			@Override
			public boolean hasNext() {
				return idx <= size;
			}

			@Override
			public T next() {
				return (T) heap[idx++];
			}
			
		};
	}
	
}

public class Solution {
	
	private static class Process{
		static Process running;
		
		int pid, begin, priority, runtime;

		public Process(int pid, int begin, int priority, int runtime) {
			this.pid = pid;
			this.begin = begin;
			this.priority = priority;
			this.runtime = runtime;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Process[] processes = new Process[N + 1];
		Heap<Process> minHeap = new Heap<>(N, new Comparator<Process>() {

			@Override
			public int compare(Process p1, Process p2) {
				//우선순위 + 대기시간 기준
				if (p1.priority - p1.begin != p2.priority - p2.begin) {
					return (p2.priority - p2.begin) - (p1.priority - p1.begin);
				}
				
				//실행 시간 기준
				if (p1.runtime != p2.runtime) {
					return p1.runtime - p2.runtime;
				}
				
				//프로세스 id 기준
				return p1.pid - p2.pid;
			}
			
		});

		//parse processes
		for (int pid = 1; pid <= N; pid++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int begin = Integer.parseInt(st.nextToken());
			int priority = Integer.parseInt(st.nextToken());
			int runtime = Integer.parseInt(st.nextToken());
			processes[pid] = new Process(pid, begin, priority, runtime);
		}

		//프로세스 실행
		int time = 0, pid = 1;
		while (pid <= N || !minHeap.isEmpty()) {
			//실행중인 프로세스가 끝나는 시간으로 점프
			if (Process.running != null) {
				time = Process.running.begin + Process.running.runtime;
			}
			//실행중인 프로세스가 없다면 다음 프로세스 요청 시간으로 점프
			else if (pid <= N) {
				time = Math.max(time, processes[pid].begin);
			}
			
			//새 프로세스 요청
			while(pid <= N && time >= processes[pid].begin) {
				minHeap.add(processes[pid++]);
			}
			
			//실행중인 프로세스가 없다면 프로세스 실행
			if (Process.running == null && !minHeap.isEmpty()) {
				Process.running = minHeap.remove();
				sb.append(Process.running.pid).append(' ');
			}
			
			//실행중인 프로세스가 있다면 Queuing
			else if (Process.running != null && time >= Process.running.begin + Process.running.runtime) {
				if (minHeap.isEmpty()) {
					Process.running = null;
					break;
				}
				Process.running = minHeap.remove();
				Process.running.begin = time;
				sb.append(Process.running.pid).append(' ');
			}
		}
		
		System.out.println(sb);
	}
	
}
