import java.io.*;
import java.util.*;

import javax.sql.rowset.serial.SerialBlob;

public class Main {
	static int n, m;
	static int[][] arr;
	public static void main(String [] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		m = Integer.parseInt(bf.readLine());
		arr = new int[n][n];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					arr[i][j] = 0;
				} else {
					arr[i][j] = 2000_000_000;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			if (arr[a][b] > c) {
				arr[a][b] = c;
			}
		}
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[k][j] != 2000_000_000 && arr[i][k] != 2000_000_000 && arr[k][j] + arr[i][k] < arr[i][j]) {
						arr[i][j] = arr[k][j] + arr[i][k];
					}
				}
			}
		}
		sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 2000_000_000) {sb.append("0 ");}
				else {	sb.append(arr[i][j]).append(" "); }
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}