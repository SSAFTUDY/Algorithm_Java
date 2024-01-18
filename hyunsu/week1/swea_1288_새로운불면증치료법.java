import java.io.*;

public class Solution {

	static int N;
	
	static int solve() {
		int result = 0, bitCnt = 0;
		
		//0~9까지 10개의 숫자가 모두 카운트된다 -> 10bit가 모두 1이면 된다.
		while (Integer.bitCount(bitCnt) != 10) {
			result += N;
			
			for (int i = result; i > 0; i /= 10) {
				//현재 result의 오른쪽 끝자리를 bitCnt에 업데이트
				bitCnt |= 1 << i % 10;
			}
		}
		
		//kN을 출력
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
			System.out.println("#" + tc + " " + solve());
        }

	}

}
