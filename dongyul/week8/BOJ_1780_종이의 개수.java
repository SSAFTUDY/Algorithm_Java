import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1780 {

    private static void sol(int size, int x, int y) {
        if(size==1){
            answer[arr[x][y]+1]+=1;
            return;
        }

        int curr = arr[x][y];
        boolean flag = true;
        loop:
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (arr[i][j] != curr) {
                    flag = false;
                    break loop;
                }
            }
        }
        if(flag){
            answer[curr+1] +=1;
            return;
        }
        else{
            int newSize = size/3;

            sol(newSize, x, y); // 1사분면
            sol(newSize, x,y+newSize); // 2사분면
            sol(newSize, x,y+newSize*2); // 3사분면

            sol(newSize, x+newSize, y); // 4사분면
            sol(newSize, x+newSize,y+newSize); // 5사분면
            sol(newSize, x+newSize,y+newSize*2); // 6사분면

            sol(newSize, x+newSize*2, y); // 7사분면
            sol(newSize, x+newSize*2,y+newSize); // 8사분면
            sol(newSize, x+newSize*2,y+newSize*2); // 9사분면

        }

    }

    static int[][] arr;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        answer = new int[3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sol(N,0,0);
        for (int i = 0; i < 3; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }
}
