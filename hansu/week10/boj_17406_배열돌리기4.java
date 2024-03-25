/**
 * 메모리: 14,476kb
 * 시간: 188ms
 */
import java.io.*;
import java.util.*;

public class Main {

    private static int minVal = Integer.MAX_VALUE;

    private static void rotate(int[][] field, int[] rotation){
        int r = rotation[0], c = rotation[1], s = rotation[2];

        for (int peel = 1; peel <= s; peel++){
            int topLeft = field[r - peel][c - peel];

            //↑
            for (int i = r - peel; i < r + peel; i++){
                field[i][c - peel] = field[i + 1][c - peel];
            }
            //←
            for (int j = c - peel; j < c + peel; j++){
                field[r + peel][j] = field[r + peel][j + 1];
            }
            //↓
            for (int i = r + peel; i > r - peel; i--){
                field[i][c + peel] = field[i - 1][c + peel];
            }
            //→
            for (int j = c + peel; j > c - peel + 1; j--){
                field[r - peel][j] = field[r - peel][j - 1];
            }

            field[r - peel][c - peel + 1] = topLeft;
        }
    }

    private static void rotateReverse(int[][] field, int[] rotation){
        int r = rotation[0], c = rotation[1], s = rotation[2];

        for (int peel = 1; peel <= s; peel++){
            int topLeft = field[r - peel][c - peel];

            //←
            for (int j = c - peel; j < c + peel; j++){
                field[r - peel][j] = field[r - peel][j + 1];
            }
            //↑
            for (int i = r - peel; i < r + peel; i++){
                field[i][c + peel] = field[i + 1][c + peel];
            }
            //→
            for (int j = c + peel; j > c - peel; j--){
                field[r + peel][j] = field[r + peel][j - 1];
            }
            //↓
            for (int i = r + peel; i > r - peel + 1; i--){
                field[i][c - peel] = field[i - 1][c - peel];
            }

            field[r - peel + 1][c - peel] = topLeft;
        }
    }

    private static int getArrayValue(int[][] field){
        int minSum = Integer.MAX_VALUE;

        for (int[] row : field) {
            int sum = 0;

            for (int e : row){
                sum += e;
            }
            minSum = Math.min(minSum, sum);
        }
        return minSum;
    }

    private static void backtracking(int[][] field, int[][] rotations, int visited, int depth){
        if (depth == rotations.length){
            minVal = Math.min(getArrayValue(field), minVal);
            return;
        }

        for (int i = 0; i < rotations.length; i++){
            if ((visited & (1 << i)) == 0){
                rotate(field, rotations[i]);
                backtracking(field, rotations, visited | (1 << i), depth + 1);
                rotateReverse(field, rotations[i]);
            }
        }
    }

    private static int getMinVal(int[][] field, int[][] rotations){
        backtracking(field, rotations, 0, 0);
        return minVal;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] field = new int[N][M];
        int[][] rotations = new int[K][];

        //parse field
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //parse rotations
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotations[i] = new int[]{r - 1, c - 1, s};
        }

        System.out.println(getMinVal(field, rotations));
    }

}
