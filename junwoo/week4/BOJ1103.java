import java.io.*;
import java.util.*;

public class Main {
	static int n, m, maxi;
	static boolean Inf;
	static String[] arr;
	static int[][] dp_arr;
	static int[][] dir = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	
	static boolean[][] copyArray(boolean[][] arr) {
		boolean[][] new_arr = new boolean[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				new_arr[i][j] = arr[i][j];
			}
		}
		return new_arr;
	}
	
	static void printArray(boolean[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}
	static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}
	static int dfs(int depth, int x, int y, boolean[][] visited) {
		if (x < 0 || x >= n || y < 0 || y >= m ||arr[x].charAt(y) == 'H') {
			if (maxi < depth) {maxi = depth;}
			return 1;
		}
//		System.out.println(x + " " + y);
//		printArray(dp_arr);
//		printArray(visited);
		if (visited[x][y]) {Inf = true; return -1;}
		if (dp_arr[x][y] > 0) {
			if (maxi < depth + dp_arr[x][y]) {maxi = depth + dp_arr[x][y];}
			return dp_arr[x][y]+1;
		}
		visited[x][y] = true;
		boolean[][] temp_visited = copyArray(visited);
		int move_power = arr[x].charAt(y) - 48;
		int maxi = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0] * move_power;
			int ny = y + dir[i][1] * move_power;
			int cnt = dfs(depth+1, nx, ny, temp_visited);
			if (Inf) {return -1;}
			if (maxi < cnt) {maxi = cnt;}
			temp_visited = copyArray(visited);
		}
		dp_arr[x][y] = maxi;
		return dp_arr[x][y]+1;
	}
	
	public static void main(String [] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new String[n]; dp_arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			arr[i] = bf.readLine();
		}
		dfs(0, 0, 0, new boolean[n][m]);
		if (Inf) {System.out.println(-1);}
		else {System.out.println(maxi);}
//		printArray(dp_arr);
	}
}