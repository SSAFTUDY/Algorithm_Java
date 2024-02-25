/**
 * 메모리: 11508kb
 * 시간: 76ms
 */
import java.io.*;
import java.util.*;

class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String L = st.nextToken(), R = st.nextToken();
        int cnt = 0;

        //자릿수가 다르면 무조건 0
        if (L.length() < R.length()){
            System.out.println(0);
            return;
        }

        //앞에서부터 일치하는 숫자 중 '8'의 개수 구함
        for (int i = 0; i < L.length() && L.charAt(i) == R.charAt(i); i++){
            if (L.charAt(i) == '8') {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}
