import java.io.*;
import java.util.*;

public class Main {

	static int find_x, find_y;
	static String result = "";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int d = Integer.parseInt(st.nextToken());
		String num = st.nextToken();

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, d);

		findLocation(num, size);

		int new_x = find_x - y;
		int new_y = find_y + x; 

		if (new_x < 0 || new_x >= size || new_y < 0 || new_y >= size) {
			System.out.println(-1);
		} else {
			cal(new_x, new_y, size);
			System.out.println(result);
		}
	}

	private static void findLocation(String num, int size) { // num의 x, y 좌표 찾기
		for (int i = 0; i < num.length(); i++) {
			int k = num.charAt(i) - '0';
			if (k == 1) {
				find_y += size / 2;
			}
			if (k == 3) {
				find_x += size / 2;
			}
			if (k == 4) {
				find_x += size / 2;
				find_y += size / 2;
			}
			size = size / 2;
		}
	}

	private static void cal(int new_x, int new_y, int size) {
		if (size > 1) {
			if (new_x >= size / 2 && new_y >= size / 2) { // 4 사분면인 경우
				result += "4";
				cal(new_x - size / 2, new_y - size / 2, size / 2);
			} else if (new_x >= size / 2) { // 3 사분면인 경우
				result += "3";
				cal(new_x - size / 2, new_y, size / 2);
			} else if (new_y >= size / 2) { // 1사분면인 경우
				result += "1";
				cal(new_x, new_y - size / 2, size / 2);
			} else { // 2사분면인 경우
				result += "2";
				cal(new_x, new_y, size / 2);
			}
		}
	}
}
