import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	static int T, n;
	static String[] str_arr;
	static int[][] dir = new int[][] {{1, 0}, {1, 1}, {0, 1}, {1, -1}};
	static boolean bo;
	
	public static boolean check_five_in_row(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int cnt = 0, nx = x, ny = y;
			for (int j = 0; j < 4; j++) {
				nx += dir[i][0]; ny += dir[i][1];
				if (nx < n && nx >= 0 && ny < n && ny >= 0) {
					if (str_arr[nx].charAt(ny) == 'o') {
						cnt++;
					} else {break;}
				} else {break;}
			}
			if (cnt == 4) {
				return true;
			}
		}
		return false;
	}
	
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(bf.readLine());
        t1 : for (int tc = 1; tc < 1+T; tc++) {
        	bo = false;
        	n = Integer.parseInt(bf.readLine());
    		str_arr = new String[n];
    		for (int j = 0; j < n; j++) {
    			str_arr[j] = bf.readLine();
			}
    		for (int j = 0; j < n; j++) {
    			for (int i = 0; i < n; i++) {
					if (str_arr[j].charAt(i) == 'o') {
						if (check_five_in_row(j, i)) {
							sb.append("#").append(tc).append(" ").append("YES").append("\n");
							bo = true; continue t1;
						}
					}
				}
			}
    		if (!bo) {
    			sb.append("#").append(tc).append(" ").append("NO").append("\n");
    		}	
		}
        System.out.println(sb);
    }
}