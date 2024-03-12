
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 11368 kb, 84ms
// result가 아닌 return 타입으로 해서 오류가 났다...! , return 해도 이미 호출된 함수가 있기 때문이다.
public class BOJ_1802 {
	static String s;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			result = 0;
			s = br.readLine();
			check(0, s.length() - 1, s.length());
			if (result == 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	private static void check(int start, int end, int size) {
		int k = 0;
		int i = start;
		int j = end;
		while (i < j) {
			if (s.charAt(i) == s.charAt(j)) {
				result = 1;
			}
			i++;
			j--;
		}
		if (result == 0 && (size / 2 > 1)) {
			check(start, start + size / 2 - 1, size / 2);
			check(start + size / 2 + 1, end, size / 2);
		}

	}
}
