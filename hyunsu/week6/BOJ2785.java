import java.io.*;
import java.util.*;

public class BOJ2785 {

    static int N;
    static List<Integer> L;

    //가장 길이가 짧은 체인으로 고리를 만들어 가장 긴 체인에 연결하기
    static int solve() {
        int cnt = 0;

        Collections.sort(L);
        while (L.size() > 1) {
            L.set(0, L.get(0) - 1); //길이가 가장 짧은 체인의 고리를 하나 풀고
            L.remove(L.size() - 1); //가장 긴 체인에 연결
            
            if (L.get(0) == 0) {
                L.remove(0);
            }
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        L = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            L.add(Integer.parseInt(st.nextToken()));
        }

        System.out.println(solve());
    }

}
