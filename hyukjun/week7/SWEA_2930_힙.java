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
 
        // 현재 노드와 현재 위치에서의 부모 노드
        int currentIdx = insertIdx;
        int parentIdx = currentIdx / 2;
 
        // 부모와 값을 비교하며 맞는 위치 찾기
        // 내림차순 정렬일 때 부모보다 자신의 값이 크다면 교환, 오름차순 정렬일 때 부모보다 자신의 값이 작다면 교환
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
 
        // 다시 힙 구조 만들기
        insertIdx--;
        array[1] = array[insertIdx];
        int currentIdx = 1;
 
        // 내림차순 정렬일 때 자신의 자식 두개의 값 중 큰 값을 계산, 본인과 두 자식 중 큰 값 중 뭐가 더 큰지 비교
        // 만약 본인보다 값이 큰 자식이 있다면 위치를 교환
        // 오름차순 정렬일 때 자신의 자식 두개의 값 중 작은 값을 계산, 본인과 두 자식중 작은 값 중 뭐가 더 작은지 비교
        // 만약 본인보다 값이 작은 자식이 있다면 위치를 교환
        int childIdx = -1;
        while (true) {
 
            // 자식의 허용 범위 확인
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
                // 값 교환
                int tmp = array[currentIdx];
                array[currentIdx] = array[childIdx];
                array[childIdx] = tmp;
                currentIdx = childIdx;
            } else {
                break;
            }
        }
 
        // output
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
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for (int TC = 1; TC <= T; TC++) {
            sb.append('#').append(TC).append(' ');
 
            // get input
            int N = Integer.parseInt(br.readLine());
            Heap heap = new Heap(N, true);
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int order = Integer.parseInt(st.nextToken());
                if (order == 1) {
                    heap.add(Integer.parseInt(st.nextToken()));
                } else if (order == 2) {
                    if (heap.isEmpty()) {
                        sb.append(-1).append(' ');
                    } else {
                        sb.append(heap.poll()).append(' ');
                    }
                }
            }
 
            sb.append('\n');
        }
 
        System.out.println(sb);
    }
 
}
