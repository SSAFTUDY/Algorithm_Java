/**
 * 메모리: 42,564kb
 * 시간: 480ms
 * 
 * (처음 실패했던 코드 수정본)
 * 이분탐색 내부에 이분탐색 써서 풂
 * 이분탐색 내부에 순회로 푼 풀이보다 느린건 정렬 때문인듯
 */
import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M;

    //fromIndex inclusive, toIndex exclusive
    private static int binarySearchByIndex(int[] jewels, int fromIndex, int toIndex, int key){
        int left = fromIndex, right = toIndex;

        while (left < right){
            int mid = (left + right) / 2;

            if (jewels[mid] <= key){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private static int binarySearchByValue(int[] jewels){
        int low = 1, high = jewels[M - 1];

        while (low < high){
            int mid = (low + high) / 2;

            //kids: 질투심이 mid일 때 선물을 받는 아이의 수 (M명부터 시작)
            int lowIdx = 0, kids = M;
            for (int m = mid; m <= jewels[M - 1]; m += mid) {
                //mid만큼 떼내도 mid보다 큰 값들에 대해 mid만큼 추가로 떼내는 작업
                //  ex) 최대 보석 개수가 7이고 mid가 3이면 3번 반복
                int midIdx = binarySearchByIndex(jewels, lowIdx, M, m);

                if (midIdx >= M){
                    break;
                }
                //mid보다 큰 값의 개수 더함
                kids += M - midIdx;
                lowIdx = midIdx;
            }

            if (kids > N){
                low = mid + 1;
            }
            else{
                high = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] jewels = new int[M];

        for (int i = 0; i < M; i++){
            jewels[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(jewels);

        System.out.println(binarySearchByValue(jewels));
    }

}
