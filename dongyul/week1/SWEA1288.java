package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1288 {

    public static int sol(int num) {
		int m = num;
		int bitmap = 0;
		while (true) {
			int temp = num;
			while (temp > 0) {
				int idx = temp % 10;
				int bit = 1<<idx;
				bitmap = bitmap|bit;
				temp /= 10;
			}
			if (Integer.bitCount(bitmap) == 10) {
				break;
			}
			num += m;
		}
		return num;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			StringBuilder sb = new StringBuilder();
			int num = Integer.parseInt(br.readLine());
			sb.append("#").append(i+1).append(" ").append(sol(num));
			System.out.println(sb.toString());
		}
	}
}
