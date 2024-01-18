import java.io.*;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	
		
		int N;
		for (int i = 0; i < T; i++) {
			int m = 1;     // 곱하는 수
			int check[] = new int[10];          // 0 ~ 9 숫자 나왔는지 체크 -> 없으면 0, 있으면 1
			N = Integer.parseInt(br.readLine());
			
			while (Arrays.stream(check).sum() <10 ) {
				
				
				char[] arr = String.valueOf(N*m).toCharArray(); 
			
				for (int j = 0; j < arr.length; j++) {
					
					int tf = check[arr[j]-'0'];   // 이미 나왔는지 확인 
					if(tf == 0) {
						check[arr[j]-'0']=1;    // 안나왔다면 1로 변경
					}
				}
				m++;
			}
			System.out.println("#" + (i+1) + " "+ (N*(m-1)));
		}
	}

}
