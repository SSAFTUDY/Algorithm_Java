import java.io.*;
 
public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            int res = 0, bitSet = 0, N = Integer.parseInt(br.readLine());
             
            while (bitSet < 1023) {
                res += N;
                for (int n = res; n > 0; n /= 10)
                    bitSet |= 1 << n % 10;
            }
            sb.append('#').append(tc).append(' ').append(res).append('\n');
        }
        System.out.println(sb);
    }
}
