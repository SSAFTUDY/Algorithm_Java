import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
 
public class Solution {
 
    static class Node {
 
        int x;
        int y;
        char value;
 
        public Node(int x, int y, char value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
 
    static int N;
    static int M;
    static char[][] arr;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

  //주변에 같은 문자가 있는지 확인
    static boolean check(int x, int y, char ch) {
        for (int i = 0; i < 4; i++) {
            if (x + dx[i] >= 0 && x + dx[i] < N && y + dy[i] >= 0 && y + dy[i] < M) {
                if (arr[x + dx[i]][y + dy[i]] == ch) {
                    return false;
                }
            }
        }
        return true;
    }
 
    static boolean BFS(Node start) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.addLast(start);
        while (!deque.isEmpty()) {
            Node curr = deque.removeFirst();
            int x = curr.x;
            int y = curr.y;
            visited[x][y] = true;
            arr[x][y] = curr.value;
            if (!check(x, y, curr.value)) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                if (x + dx[i] >= 0 && x + dx[i] < N && y + dy[i] >= 0 && y + dy[i] < M && !visited[x + dx[i]][y + dy[i]]) {
                    if (curr.value == '.') {
                        deque.addLast(new Node(x + dx[i], y + dy[i], '#'));
                    } else {
                        deque.addLast(new Node(x + dx[i], y + dy[i], '.'));
                    }
                }
            }
        }
        return true;
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
 
            arr = new char[N][M];
            visited = new boolean[N][M];
 
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }
            boolean flag = true;
            loopOut:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == '#' || arr[i][j] == '.') {
                        flag = BFS(new Node(i, j, arr[i][j]));
                        break loopOut;
                    }
                }
            }
            sb.append("#").append(t+1).append(" ");
            if (flag) {
                sb.append("possible").append("\n");
            } else {
                sb.append("impossible").append("\n");
            }
        }
        System.out.println(sb);
 
    }

}
