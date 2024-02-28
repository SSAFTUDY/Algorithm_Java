// 11512kb, 	76ms
import java.io.*;
import java.util.*;

public class BOJ_1105 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String L = st.nextToken();
		String R = st.nextToken();
		int result = 0;

		if (L.length() != R.length()) {
			System.out.println(0);
			return;
		} else {
			for (int i = 0; i < L.length(); i++) {
				if (L.charAt(i) != R.charAt(i)) { // 값이 다르면 그 뒷자리는 보지 않아도 0이다.
					break;
				} else {
					if (L.charAt(i) == '8') {  // 값이 같으면 8인 경우만 result+1
						result++;
					}
				}
			}
		}
		System.out.println(result);
	}
}
