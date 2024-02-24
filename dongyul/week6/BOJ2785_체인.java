import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2785 {

    /**
     * case
     * 5
     * 4 3 5 7 9
     * 3 고리를 전부 열어 4 - 5 - 7 - 9
     * 정렬하여 가장 적은 체인부터 연결 고리로 사용
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        //연결에는 체인의 개수 -1 개의 고리가 필요
        int cnt = 0;
        int num = N;
        for (int i = 0; i < N; i++) {
            if(arr[i]+cnt>num-2){   //현재 체인을 전부 고리로 사용할 필요가 없는 경우
                cnt = num-1;    //고리 num-1개만 필요
                break;
            }
            else if(arr[i]+cnt == num-2){
                cnt = arr[i]+cnt;   //현재 체인을 전부 고리로 사용하면 나머지를 전부 이을 수 있을 때
                break;
            }
            else{
                cnt+=arr[i];    //현재 체인을 고리로 전부 사용해도 다 이을 수 없음
                num-=1;
            }
        }
        System.out.println(cnt);
    }

}
