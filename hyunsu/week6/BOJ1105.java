import java.io.*;
import java.util.*;

public class BOJ1105 {


    static String L, R;

    /*
    1. L과 R의 자릿수가 다른 경우: 무조건 1,00x..가 껴있기 때문에 0
    2. L과 R의 자릿수가 같은 경우: 앞자리부터 읽어가면서 자릿수가 같고 8인 경우를 세기. (자릿수의 값이 달라지는 순간 8이 아님)
    8822801, 8822833 -> 88228 이후에 0과 3으로 자릿수의 값이 다름 -> 이후에는 8이 등장하지 않을 수 있음.
     */
    static int solve() {
        if (L.length() != R.length()) {
            return 0;
        }
        
        int idx = 0, cnt = 0;
        while (idx < L.length() && L.charAt(idx) == R.charAt(idx)) {
            if (L.charAt(idx) == '8') {
                cnt++;
            }
            idx++;
        }
        
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = st.nextToken();
        R = st.nextToken();

        System.out.println(solve());
    }

}
