/**
 * 메모리: 20,688kb
 * 시간: 236ms
 */
import java.io.*;
import java.util.*;

public class Solution {

    private static int binarySearchForValue(int[] arr, int K, int min){
        int l = min, r = min + K;

        while (l <= r){
            int mid = (l + r) / 2;
            long needLv = 0;

            for (int lv : arr){
                if (mid > lv) {
                    needLv += mid - lv;
                }
            }

            if (needLv < K){
                l = mid + 1;
            } else if (needLv > K){
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return r;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;
        int[] arr = new int[N];

        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            min = Math.min(min, arr[i]);
        }

        System.out.println(binarySearchForValue(arr, K, min));
    }

}
