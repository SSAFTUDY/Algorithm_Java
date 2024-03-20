import java.io.*;
import java.util.*;

public class Main {

    //두 배열에서 원소 하나씩 뽑아서 더한 모든 조합 반환
    private static int[] mix(int[] arr1, int[] arr2){
        int[] ret = new int[arr1.length * arr2.length];
        int idx = 0;

        for (int n1 : arr1) {
            for (int n2 : arr2) {
                ret[idx++] = n1 + n2;
            }
        }
        return ret;
    }

    /**
     * Arrays.binarySearch의 return 값
     * 찾는 키 값이 있을 경우 -> 해당 키 값의 index 반환
     * 찾는 키 값이 없을 경우 -> 해당 키 값 보다 큰 최초의 값을 index라 하면 (-index-1)을 리턴
     * ex) [1,3,4,7,9]에서 6을 key값으로 넣으면 -4 반환 (-(7의 index)-1)
     * 만약 10이 key값이라면 (-5 -1)로 -6을 리턴!
     */

    private static int binarySearch(int[] arr, int key){
        int idx = Arrays.binarySearch(arr, key);
        return (idx < 0) ? - idx - 1 : idx;
    }

    //근사치가 동일할 경우 작은 값을 리턴하기 위한 메서드
    private static int myCompare(int x, int y, int K){
        int X = Math.abs(K - x);
        int Y = Math.abs(K - y);
        if (X > Y){
            return y;
        } else if (X == Y){
            return Math.min(x, y);
        }
        return x;
    }

    private static int find(int[] mix1, int[] mix2, int K) {
        int res = Integer.MAX_VALUE;

        for (int n1 : mix1) {
            int idx = binarySearch(mix2, K - n1), minSum = Integer.MAX_VALUE;
            
            if (idx == 0){
                minSum = n1 + mix2[idx];
            }
            else if (idx == mix2.length){   //맨 끝 값일 때 예외처리
                minSum = n1 + mix2[idx - 1];
            }
            else if (n1 + mix2[idx] == K){
                return K;
            }
            else {
                minSum = myCompare(n1 + mix2[idx - 1], n1 + mix2[idx], K);
            }

            res = myCompare(res, minSum, K);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int[][] arr = new int[4][N];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] mix1 = mix(arr[0], arr[1]);
            int[] mix2 = mix(arr[2], arr[3]);
            Arrays.sort(mix2);
            
            sb.append(find(mix1, mix2, K)).append('\n');

        }
        System.out.println(sb);

    }
}
