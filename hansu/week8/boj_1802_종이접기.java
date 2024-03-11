/**
 * 메모리: 13,128kb
 * 시간: 84ms
 */
import java.io.*;

public class Solution {

    private static boolean isFoldable(String s, int mid, int len){
        if (len == 0){
            return true;
        }

        for (int i = 1; i <= len; i++){
            if ((s.charAt(mid - i) ^ s.charAt(mid + i)) == 0){
                return false;
            }
        }
        return isFoldable(s, mid - (len + 1) / 2, len / 2) && isFoldable(s, mid + (len + 1) / 2, len / 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String s = br.readLine();

            if (isFoldable(s, s.length() / 2, s.length() / 2)) {
                sb.append("YES\n");
            } else{
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }

}
