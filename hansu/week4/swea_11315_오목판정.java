import java.io.*;

class Solution {

    private static final int[][] D = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    private static boolean isInBoard(int N, int i, int j){
        return i >= 0 && j >= 0 && i < N && j < N;
    }

    private static boolean isOmok(String[] board, int i, int j) {
        int N = board.length;

next:   for (int[] d : D){
            //오목을 만들 칸이 부족한 경우
            if (!isInBoard(N, i + 4 * d[0], j + 4 * d[1])){
                continue;
            }

            //해당 방향으로 4개의 돌이 있을 경우 true 반환
            for (int cnt = 1; cnt < 5; cnt++){
                if (board[i + cnt * d[0]].charAt(j + cnt * d[1]) == '.'){
                    continue next;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            int N = Integer.parseInt(br.readLine());
            String[] board = new String[N];
            boolean flag = false;

            for (int i = 0; i < N; i++){
                board[i] = br.readLine();
            }

        a:  for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (board[i].charAt(j) == 'o' && isOmok(board, i, j)){
                        flag = true;
                        break a;
                    }
                }
            }
            sb.append('#').append(tc).append(flag ? " YES\n" : " NO\n");
        }
        System.out.println(sb);
    }

}
