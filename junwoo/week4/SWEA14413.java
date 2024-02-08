import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	static int T, n, m, Even; // even 이 1이면 블랙 2이면 화이트이다.
	// 직사각형 격자판을 두 가지 색으로 같은 색이 인접하지 않게 칠하려면
	// i + j 의 값이 odd 인 칸들과 even 인 칸들로 나뉘어서 칠해야 한다.
	// 때문에 처음 # 나 . 을 만나면 그 칸의 색깔을 even에 넣고
	// 자동적으로 다른 색깔을 반대 변수에 넣는다. 그 후 나머지 칸들을 확인하여
	// i + j의 값이 odd / even인지 확인한다.
	static String[] str_arr;

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(bf.readLine());
        t1 : for (int tc = 1; tc < 1+T; tc++) {
        	Even = 0;
        	st = new StringTokenizer(bf.readLine());
        	n = Integer.parseInt(st.nextToken());
    		m = Integer.parseInt(st.nextToken());
    		str_arr = new String[n];
    		for (int j = 0; j < n; j++) {
    			str_arr[j] = bf.readLine();
    			for (int i = 0; i < m; i++) {
					if (str_arr[j].charAt(i) == '#') {
						if ((i + j) % 2 == 1) {
							if (Even == 0) {
								Even = 2;
							} else if (Even == 1){
								sb.append("#").append(tc).append(" ").append("impossible").append("\n");
								for (int k = j+1; k < n; k++) {
									bf.readLine();
								}
								continue t1;
							}
						} else { 
							if (Even == 0) {
								Even = 1;
							} else if (Even == 2){
								sb.append("#").append(tc).append(" ").append("impossible").append("\n");
								for (int k = j+1; k < n; k++) {
									bf.readLine();
								}
								continue t1;
							}
						}
					} else if (str_arr[j].charAt(i) == '.') {
						if ((i + j) % 2 == 1) {
							if (Even == 0) {
								Even = 1;
							} else if (Even == 2){
								sb.append("#").append(tc).append(" ").append("impossible").append("\n");
								for (int k = j+1; k < n; k++) {
									bf.readLine();
								}
								continue t1;
							}
						} else { 
							if (Even == 0) {
								Even = 2;
							} else if (Even == 1){
								sb.append("#").append(tc).append(" ").append("impossible").append("\n");
								for (int k = j+1; k < n; k++) {
									bf.readLine();
								}
								continue t1;
							}
						}
					}
				}
			}
    		sb.append("#").append(tc).append(" ").append("possible").append("\n");
		}
        System.out.println(sb);
    }
}