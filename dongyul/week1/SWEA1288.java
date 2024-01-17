package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1288 {

    public static Boolean all(int[] arr) {
        Boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static int sol(int num) {
        int m = num;
        int[] arr = new int[10];
        while (true) {
            int temp = num;
            while (temp > 0) {
                int idx = temp % 10;
                arr[idx] += 1;
                temp /= 10;
            }
            if (all(arr)) {
                break;
            }
            num += m;
        }
        return num;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringBuilder sb = new StringBuilder();
            int num = Integer.parseInt(br.readLine());
            sb.append("#").append(i+1).append(" ").append(sol(num));
            System.out.println(sb.toString());
        }
    }
}