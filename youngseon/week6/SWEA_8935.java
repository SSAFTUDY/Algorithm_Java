import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_8935 {

	static int[] A;
	static int[] B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			int result = 0;
			int N = Integer.parseInt(br.readLine());
			A = new int[N];
			B = new int[N];
			
			for(int i=0; i<N; i++) {
				A[i] = Integer.parseInt(br.readLine());
			}
			
			int M = Integer.parseInt(br.readLine());
			for(int i=0; i<M; i++) {
				B[i] = Integer.parseInt(br.readLine());
			}
			
			
			
			
			sb.append("#").append(t+1).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
