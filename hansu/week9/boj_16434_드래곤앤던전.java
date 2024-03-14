/**
 * 메모리: 52,392kb
 * 시간: 348ms
 */
import java.io.*;
import java.util.*;

public class Solution {

    // ⌈x / y⌉ 계산
    private static long ceilDiv(long x, long y){
        long q = x / y;

        return (x == y || q * y == x) ? q : q + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long heroAtk = Integer.parseInt(st.nextToken());
        long heroHp = 0, minHp = 0;

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int atk = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());

            if (t == 1){
                heroHp -= (ceilDiv(hp, heroAtk) - 1) * atk;
                minHp = Math.min(minHp, heroHp);
            }
            else {
                heroHp = Math.min(heroHp + hp, 0);
                heroAtk += atk;
            }
        }

        System.out.println(1 - minHp);
    }

}
