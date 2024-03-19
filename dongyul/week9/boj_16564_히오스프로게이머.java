import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long start = 0;
        long end = 1_000_000_000+K;

        while (start<=end) {
            long mid = (start+end)/2;
//            System.out.println(start + " " + end + " " + mid);
            long sum = 0;
            for (int i = 0; i < N; i++) {
                if(mid>arr[i]){
                    sum+=mid-arr[i];
                }
            }
            if(sum>K){
                end = mid-1;
            }
            else if(sum<K){
                start = mid+1;
            }
            else{
                System.out.println(mid);
                System.exit(0);
            }
        }
        System.out.println(end);
    }
}
