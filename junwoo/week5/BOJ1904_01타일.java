import java.util.*;
import java.io.*;

public class Main {
	static int N, prevprev = 1, prev = 1, current = 1;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		for (int i = 2; i < N+1; i++) {
			current = (prev+prevprev) % 15746;
			prevprev = prev;
			prev = current;
		}
		System.out.println(current);
	}
}