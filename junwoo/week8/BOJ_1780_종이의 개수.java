import java.io.*;
import java.util.*;
							

class Main {
	static int arr[][];
	static int ans[];
	public static void divide_and_conquer(int start_x, int start_y, int size) {
		int first_number = arr[start_x][start_y];
		for (int i = start_x; i < start_x + size; i++) {
			for (int j = start_y; j < start_y + size; j++) {
				if (first_number == arr[i][j]) {
					continue;
				}
				else {
					divide_and_conquer(start_x, start_y, size/3);
					divide_and_conquer(start_x, start_y + size/3, size/3);
					divide_and_conquer(start_x, start_y + 2 * size/3, size/3);
					divide_and_conquer(start_x + size/3, start_y, size/3);
					divide_and_conquer(start_x + size/3, start_y + size/3, size/3);
					divide_and_conquer(start_x + size/3, start_y + 2 * size/3, size/3);
					divide_and_conquer(start_x + 2 * size/3, start_y, size/3);
					divide_and_conquer(start_x + 2 * size/3, start_y + size/3, size/3);
					divide_and_conquer(start_x + 2 * size/3, start_y + 2 * size/3, size/3);
					return;
				}
			}
		}
		ans[first_number+1]++;
	}
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		ans = new int[3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		divide_and_conquer(0, 0, N);
		for (int i = 0; i < 3; i++) {
			System.out.println(ans[i]);
		}
		
		
		
		
	}
}