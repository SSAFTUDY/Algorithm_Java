/**
 * 메모리: 35,600kb
 * 시간: 320ms
 */
import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M;

    private static int ceilingDiv(int x, int y){
        int q = x / y;
        return q * y == x ? q : q + 1;
    }

    private static int binarySearchByValue(int[] jewels, int max){
        int low = 1, high = max;

        while (low < high){
            int mid = (low + high) / 2;

            //질투심이 mid일 때 선물을 받는 아이의 수 구함
            int kids = 0;
            for (int n : jewels) {
                kids += ceilingDiv(n, mid);
            }

            if (kids > N){
                low = mid + 1;
            }
            else{
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] jewels = new int[M];
        int max = 0;

        for (int i = 0; i < M; i++){
            jewels[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, jewels[i]);
        }
        System.out.println(binarySearchByValue(jewels, max));
    }

}
