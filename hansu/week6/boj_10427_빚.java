/** 이왜맞?
 * 메모리: 14576kb
 * 시간: 2136ms
 */
import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            long res = 0;
            int[] borrows = new int[N + 1];
            int[] cumulative = new int[N + 1];

            //parse
            for (int i = 1; i <= N; i++){
                borrows[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(borrows);

            //누적합 생성
            for (int i = 1; i <= N; i++) {
                cumulative[i] = cumulative[i - 1] + borrows[i];
            }

            //size개의 숫자를 골랐을 때 최솟값, 최댓값 간의 차이가 가장 적은 숫자 선택 (n = 1일 땐 항상 0)
            for (int size = 2; size <= N; size++){
                int min = Integer.MAX_VALUE, minIdx = 0;

                // borrows[i]와 borrows[i + size - 1]의 차이가 최소가 되는 구간 선택
                for (int i = 1; i <= N - size + 1; i++){
                	int tmp = 0;
                	
                	//추가비용 구하기
                	for (int j = 0; j < size - 1; j++) {
                		tmp += borrows[i + size - 1] - borrows[i + j];
                	}
                	//최소 추가비용 계산
                    if (min > tmp){
                        min = tmp;
                        minIdx = i;
                    }
                }
                //S(n) = size * (n개의 숫자중 최댓값) - (n개의 숫자들의 합)
                res += (long)size * (borrows[minIdx + size - 1]) - (cumulative[minIdx + size - 1] - cumulative[minIdx - 1]);
            }
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }

}
