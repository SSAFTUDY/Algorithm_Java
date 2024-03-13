/**
 * 메모리: 38,532kb
 * 시간: 368ms
 */
import java.io.*;
import java.util.*;

public class Solution {

    private static int maxGcdSum;
    private static int[] arr;

    private static int gcd(int a, int b){
        int big = Math.max(a, b), small = Math.min(a, b);

        while (big % small > 0){
            int tmp = small;
            small = big % small;
            big = tmp;
        }
        return small;
    }

    private static int gcdAll(int start, int end){
        int res = arr[start];

        for (int i = start + 1; i < end; i++){
            res = gcd(res, arr[i]);
        }
        return res;
    }
    
    // l inclusive, r exclusive
    private static void getMaxGcd(int l, int r, int gcdSum){
        if (l >= r - 1){
            maxGcdSum = Math.max(maxGcdSum, gcdSum + arr[l]);
            return;
        }

        int m = (l + r) / 2;
        getMaxGcd(l, m, gcdSum + gcdAll(m, r)); //오른쪽 반 선택
        getMaxGcd(m, r, gcdSum + gcdAll(l, m)); //왼쪽 반 선택
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        //parsing
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        getMaxGcd(0, N, 0);
        System.out.println(maxGcdSum);
    }

}
