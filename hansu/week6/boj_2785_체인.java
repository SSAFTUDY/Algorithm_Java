/**
 * 메모리: 74692kb
 * 시간: 640ms
 */
import java.io.*;
import java.util.*;

class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] chains = new int[N];
        int l = 0, r = N - 1, res = 0;
        
        for (int i = 0; i < N; i++){
            chains[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(chains);
        while (l < r){
            if (--chains[l] == 0){
                l++;
            }
            r--;
            res++;
        }
        System.out.println(res);
    }

}
