import java.io.*;
import java.util.*;

public class BOJ18232 {

    static int N, M, S, E;
    static List<List<Integer>> connect;
    static boolean[] visited;

    static int bfs() {
        int ans = 0;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(S, 0));
        visited[S] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            if (cur.dot == E) {
                ans = Math.max(ans, cur.time);
                continue;
            }

            if (cur.dot - 1 >= 1 && !visited[cur.dot - 1]) {
                q.offer(new Pos(cur.dot - 1, cur.time + 1));
                visited[cur.dot - 1] = true;
            }
            if (cur.dot + 1 <= N && !visited[cur.dot + 1]) {
                q.offer(new Pos(cur.dot + 1, cur.time + 1));
                visited[cur.dot + 1] = true;
            }

            for (Integer dot : connect.get(cur.dot)) {
                if (!visited[dot]) {
                    q.offer(new Pos(dot, cur.time + 1));
                    visited[dot] = true;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        connect = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            connect.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            connect.get(x).add(y);
            connect.get(y).add(x);
        }

        System.out.println(bfs());
    }

    static class Pos {
        int dot, time;

        public Pos(int dot, int time) {
            this.dot = dot;
            this.time = time;
        }
    }
}
