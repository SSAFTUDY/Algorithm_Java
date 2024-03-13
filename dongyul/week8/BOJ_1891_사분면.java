package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1891 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int d = Integer.parseInt(st.nextToken());
		String str = st.nextToken();
		st = new StringTokenizer(br.readLine());
		long mx = Long.parseLong(st.nextToken());
		long my = Long.parseLong(st.nextToken());
		
		//좌표 구하기	
		long cx = 0;
		long cy = 0;
		for(int i = 0; i<d; i++) {
			if(str.charAt(i) == '1') {
				cy+=(long) Math.pow(2, d-i-1);
			}
			else if(str.charAt(i) == '2'){
				continue;
			}
			else if(str.charAt(i) == '3') {
				cx+=(long) Math.pow(2, d-i-1);
			}
			else {
				cx+=(long) Math.pow(2, d-i-1);
				cy+=(long) Math.pow(2, d-i-1);
			}
		}

		cx-=my;
		cy+=mx;

		long curr = 1L << (d-1);	//2^d
		if(cx>=curr*2 || cy>=curr*2 || cx<0 || cy<0) {
			System.out.println(-1);
			System.exit(0);
		}
		
		for(int i = 0; i<d; i++) {
			if(cx>=curr && cy>=curr) {	//4사분면
				sb.append(4);
				cx-=curr;
				cy-=curr;
			}
			else if(cx>=curr && cy<curr) { //3사분면
				sb.append(3);
				cx-=curr;
			}
			else if(cx<curr && cy>=curr) {	//1사분면
				sb.append(1);
				cy-=curr;
			}
			else {
				sb.append(2);
			}
			curr/=2;
		}
		System.out.println(sb);
	}

}
