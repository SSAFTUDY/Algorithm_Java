
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MyArrayList<E>{
	
	private int size;
	
}

public class Solution {


	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		MyArrayList<Integer> str = new MyArrayList<Integer>(); // 암호문 list 생성
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			str.add(Integer.parseInt(st.nextToken()));    // 숫자 저장
		}
		
		int M = Integer.parseInt(br.readLine());
		int x, y, s;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String inputCh = st.nextToken();
			if (inputCh.equals("I")){
				
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				for (int j = 0; j < y; j++) {
					s = Integer.parseInt(st.nextToken());
					str.add(s);
				}
			}
			else if (inputCh.equals("D")){
				
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				for (int j = 0; j < y; j++) {
					str.remove();
				}
			}
			else {
				
				y = Integer.parseInt(st.nextToken());
				
				for (int j = 0; j < y; j++) {
					s = Integer.parseInt(st.nextToken());
					str.add(s);
				}
			}
		}
		System.out.println(str.toString());

	}

}
