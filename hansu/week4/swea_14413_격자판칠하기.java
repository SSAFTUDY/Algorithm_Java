import java.io.*;
import java.util.*;

class Solution {

    private static final int[][] D = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    private static boolean bfs(char[][] board, int[] sIdx){
        if (sIdx == null){
            return true;
        }
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        char[] color = {'#', '.'};
        int cIdx = board[sIdx[0]][sIdx[1]] == '#' ? 0 : 1;

        visited[sIdx[0]][sIdx[1]] = true;
        q.add(sIdx);
        while (!q.isEmpty()){
            int size = q.size();

            //한 사이클의 모양이 모두 적절한지 확인
            for (int i = 0; i < size; i++){
                int[] pos = q.remove();

                //4방 탐색
                for (int[] d : D) {
                    int I = pos[0] + d[0], J = pos[1] + d[1];

                    if (I < 0 || J < 0 || I >= board.length || J >= board[0].length || visited[I][J]){
                        continue;
                    }

                    if (board[I][J] == color[cIdx]) {
                        return false;
                    }
                    visited[I][J] = true;
                    q.add(new int[]{I, J});
                }
            }

            cIdx = (cIdx + 1) % 2;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
            char[][] board = new char[N][M];
            int[] startIdx = null;

            for (int i = 0; i < N; i++){
                String line = br.readLine();

                for (int j = 0; j < M; j++) {
                    board[i][j] = line.charAt(j);
                    if (startIdx == null && (board[i][j] == '#' || board[i][j] == '.')) {
                        startIdx = new int[]{i, j};
                    }
                }
            }
            sb.append('#').append(tc).append(bfs(board, startIdx) ? " possible\n" : " impossible\n");
        }
        System.out.println(sb);
    }

}
