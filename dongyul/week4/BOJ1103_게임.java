import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1103 {

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int M;
    static char[][] arr;
    static boolean[][] visited;
    static int answer;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] dp;


    static int DFS(int depth, Node curr) {
        int x = curr.x;
        int y = curr.y;
        int cnt = 0;
        if (arr[x][y] == 'H') {
            return 1;
        }
        if(visited[x][y]){
            System.out.println(-1);
            System.exit(0);
        }
        if(dp[x][y] != 0){
            return dp[x][y];
        }
        visited[x][y] = true;
        answer = Math.max(answer, depth);
        int d = arr[x][y] - '0';    //움직여야 하는 횟수
        for (int i = 0; i < 4; i++) {
            int xx = x + (dx[i] * d);
            int yy = y + (dy[i] * d);
            if (xx >= 0 && xx < N && yy >= 0 && yy < M) {
                cnt = DFS(depth+1,new Node(xx,yy)) + 1;
                dp[xx][yy] = cnt;
                visited[xx][yy] = false;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];
        answer = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        DFS(1,new Node(0,0));
        System.out.println(answer);
    }
}
