import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	static int dx = 0;   // 초기에는 오른쪽으로 이동하므로
	static int dy = 1;
	
	static void change_direction(String dir) {
		int tmp;
		if(dir.equals("D")) {
			if(dx == 1 | dx == -1) {
				tmp = dy;                      
				dy = dx;
				dx = tmp;
				dy = -dy;
			}
		}
		else if(dir.equals("L")) {
			if(dy == 1 | dy == -1) {
				tmp = dy;                      
				dy = dx;
				dx = tmp;
				dx = -dx;
			}
		}
		else {
			tmp = dy;                      
			dy = dx;
			dx = tmp;
		}
		
	}

	public static void main(String[] args) throws IOException { // 아무것도 없으면 0, 사과는 1, 뱀은 2
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int time = 1;

		int[][] arr = new int[N + 1][N + 1];
		StringTokenizer st;
		int x, y;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			arr[x][y] = 1;
		}
		int L = Integer.parseInt(br.readLine());

		arr[1][1] = 2;
//		for (int i = 1; i < N + 1; i++) {
//			for (int j = 1; j < N + 1; j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
		int[] sec = new int[L];
		String[] dir = new String[L];
		for (int i = 0; i < L; i++) { // 방향 변화 초기화
			st = new StringTokenizer(br.readLine());
			sec[i] = Integer.parseInt(st.nextToken());
			dir[i] = st.nextToken();
		}

		int head_x = 1;
		int head_y = 1;
		int tail_x = 1;
		int tail_y = 1;

		int k = 0; // 방향 변화 인덱스
		while (true) {
			if (k < L) {
				if(time == sec[k]) {
					change_direction(dir[k]);
					k++;
				}
			}
			head_x += dx;
			head_y += dy;
			if (head_x <= 0 | head_y <= 0 | head_x > N | head_y > N | tail_x <= 0 | tail_y <= 0 | tail_x > N | tail_y > N) {
				break;
			}
			arr[head_x][head_y] = 2;
			arr[tail_x][tail_y] = 0;
			
			time += 1;

		}
		System.out.println(time);

	}
}
