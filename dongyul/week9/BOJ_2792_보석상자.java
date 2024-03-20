package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2792 {
	
	//각 보석에 몇 명씩 배치할지를 결정해주자, 보석 개수 / 사람 수 + 나머지0일 때는 그대로, 아니면 +1
	//질투심을 기준으로 탐색..? -> 질투심이 x면 x만큼 전부 분배하고 사람이 남으면 질투심을 줄이고, 사람이 부족하면 질투심을 늘리기!
	//start = 1 , end = 10^9이면 충분할듯?
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[M];
		for(int i = 0; i<M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int start = 1;
		int end = 1000_000_000;
		while(start<=end) {
			int mid = (start+end)/2; //탐색할 질투심
			
			int cnt = 0;
			for(int i = 0; i<M; i++) {
				if(arr[i]%mid == 0) {
					cnt += arr[i]/mid;
				}else {
					cnt+= arr[i]/mid +1;
				}
			}
			if(cnt>N) { //질투심이 남을 때
				start = mid+1;
			}else if(cnt<=N) {
				end = mid-1;
			}

		}
		System.out.println(start);
		
	}

}
