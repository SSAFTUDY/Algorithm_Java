/**
 * 메모리: 11,928kb
 * 시간: 132ms
 */
import java.io.*;
import java.util.*;

public class Solution {

    private static boolean binarySearch(int[] arr, int fromIndex, int toIndex, int key) {
        int l = fromIndex, r = toIndex;
        
        while (l < r){
            int m = (l + r) / 2;

            if (arr[m] < key){
                l = m + 1;
            } else if (arr[m] > key){
                r = m;
            } else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        for (int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        // 세 수의 합이 될 수를 먼저 정함
        for (int sumIdx = N - 1; sumIdx > 0; sumIdx--) {
            // 세 수중 최대, 최소 수를 고름 (합이 되는 원소 포함시 불가능)
            for (int n3 = sumIdx - 1; n3 >= 0; n3--) {
                for (int n1 = 0; n1 <= n3; n1++) {
                    int n2 = nums[sumIdx] - (nums[n1] + nums[n3]);

                    //나머지 한 수가 최솟값보다 작으면 불가능
                    if (n2 < n1) {
                        break;
                    }

                    // 구간 이분탐색으로 사이에 있는 나머지 수 찾기
                    if (binarySearch(nums, n1, n3 + 1, n2)) {
                        System.out.println(nums[sumIdx]);
                        return;
                    }

                }
            }
        }
    }

}
