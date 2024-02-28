import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10427 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        //갚아야 할 돈 : Max(a1+a2+a3...an) * n - (a1+a2+a3...an)
        //an+1이 들어올 경우 현재 max = m
        //1. Max값이 바뀌는 경우 -> 추가 금액 (an+1*(n+1) - am*n) - an+1 = n(an+1 - m)
        //2. Max값이 유지되는 경우 -> 추가 금액 증가-> (m-an+1)
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            long[] s = new long[N];
            long[] arr = new long[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            s[0] = 0;
            for (int i = 1; i < N; i++) {
                s[i] = Integer.MAX_VALUE;
            }

            int p1 = 0;
            int p2 = 1;
            boolean[] visited = new boolean[N];
            long currMax = 0;
            //2개까지만 완탐으로 구해주기
            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    if(i==j){
                        continue;
                    }
                    long cost = (Math.max(arr[i], arr[j]) * 2) - (arr[i] + arr[j]);
                    if(cost<s[1]){
                        s[1] = cost;
                        visited[p1] = false;
                        visited[p2] = false;
                        visited[i] = true;
                        visited[j] = true;
                        p1 = i;
                        p2 = j;
                        currMax = Math.max(arr[i],arr[j]);
                    }
                }
            }

            //3개부터 그리디 적용
            for (int i = 2; i < N; i++) {
                for(int j = 0; j<N; j++){

                    for (int k = 0; k < N; k++) {
                        if(!visited[k]){
                            p2 = k;
                            break;
                        }
                    }

                    long temp = Long.MAX_VALUE;
                    long tempMax = currMax;

                    System.out.println(Arrays.toString(visited));
                    if(!visited[j]){
                        if(currMax<arr[j]){
                            long cost = i*(arr[j] - currMax);
                            if(temp>s[i-1]+cost){
                                visited[p2] = false;
                                temp = s[i-1]+cost;
                                p2 = j;
                                visited[p2] = true;
                                tempMax = arr[j];
                            }
                        }
                        else{
                            long cost = currMax-arr[j];
                            if(temp>s[i-1]+cost){
                                visited[p2] = false;
                                temp = s[i-1]+cost;
                                p2 = j;
                                visited[p2] = true;
                            }
                        }
                    }
                    s[i] = temp;
                    currMax = tempMax;
                }
            }

            System.out.println(Arrays.toString(s));
        }

    }

}
