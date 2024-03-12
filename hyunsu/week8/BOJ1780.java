import java.util.*;
import java.io.*;

public class BOJ1780 {

    static int N, ans1, ans0, ansM1;
    static int[][] paper;

    static boolean check(int sR, int sC, int eR, int eC) {
        int n = paper[sR][sC];
        for (int i = sR; i <= eR; i++) {
            for (int j = sC; j <= eC; j++) {
                if (paper[i][j] != n) {
                    return false;
                }
            }
        }

        return true;
    }

    static void solve(int size, int row, int col) {
        if (check(row, col, row + size - 1, col + size - 1)) {
            if (paper[row][col] == 1) {
                ans1++;
            } else if (paper[row][col] == -1) {
                ansM1++;
            } else {
                ans0++;
            }
            return;
        }

        //1 2 3
        //4 5 6
        //7 8 9
        //로 9등분 한다고 가정
        solve(size / 3, row, col); //1
        solve(size / 3, row, col + (size / 3)); //2
        solve(size / 3, row, col + ((size / 3) * 2)); //3
        solve(size / 3, row + (size / 3), col); //4
        solve(size / 3, row + (size / 3), col + (size / 3)); //5
        solve(size / 3, row + (size / 3), col + ((size / 3) * 2)); //6
        solve(size / 3, row + ((size / 3) * 2), col); //7
        solve(size / 3, row + ((size / 3) * 2), col + (size / 3)); //8
        solve(size / 3, row + ((size / 3) * 2), col + ((size / 3) * 2)); //9
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            if (n == 1) {
                ans1++;
            } else if (n == 0) {
                ans0++;
            } else {
                ansM1++;
            }
        } else {
            paper = new int[N][N];
            for (int i = 0; i < paper.length; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < paper[i].length; j++) {
                    paper[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve(N, 0, 0);
        }

        System.out.println(ansM1);
        System.out.println(ans0);
        System.out.println(ans1);
    }
}
