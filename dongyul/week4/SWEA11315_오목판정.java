import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA11315 {

    static class Point {

        int x;
        int y;
        int val;

        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    static int N;
    static char[][] arr;
    static boolean[][] visited; //왼쪽 부터 탐색, 방문한 값은 다시 탐색 안해도 됨 -> 그리디
    static int[] dx = {0, 0, -1, -1, -1, 1, 1, 1};
    static int[] dy = {1, -1, -1, 0, 1, -1, 0, 1};

    private static boolean DFS(Point p) {
        //8방향 DFS
        for (int i = 0; i < 8; i++) {
            Stack<Point> stack = new Stack<>();
            stack.add(p);
            while (!stack.isEmpty()) {
                Point curr = stack.pop();
                if (curr.val == 5) {
                    return true;
                }
                visited[p.x][p.y] = true;
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]
                    && arr[nx][ny] == 'o') {
                    stack.add(new Point(nx, ny, curr.val + 1));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new char[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }
            sb.append("#").append(t + 1).append(" ");
            boolean flag = false;
            loop:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[j][i] && arr[j][i] == 'o') {
                        flag = DFS(new Point(j, i, 1));
                        if (flag) {
                            sb.append("YES").append(" ").append("\n");
                            break loop;
                        }
                    }
                }
            }
            if (!flag) {
                sb.append("NO").append(" ").append("\n");
            }
        }
        System.out.println(sb);
    }

}
