import java.util.*;
import java.io.*;

public class Main {
	static int N, maxi;
	static int[] seq, before_decrease, after_decrease;

	
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		seq = new int[N+1];
		before_decrease = new int[N+1];
		after_decrease = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < i; j++) {
				if (seq[j] < seq[i] && before_decrease[i] <= before_decrease[j]) {
					before_decrease[i] = before_decrease[j]+1;
					if (before_decrease[i] > maxi) maxi = before_decrease[i];
				}
				if (seq[j] > seq[i] && after_decrease[i] <= before_decrease[j]) {
					after_decrease[i] = before_decrease[j]+1;
				}
				if (seq[j] > seq[i] && after_decrease[i] <= after_decrease[j]) {
					after_decrease[i] = after_decrease[j]+1;
				}
				if (after_decrease[i] > maxi) maxi = after_decrease[i];
			}
//			System.out.println(i + " = ---------------------");
//			System.out.println(Arrays.toString(before_decrease));
//			System.out.println(Arrays.toString(after_decrease));
		}
		System.out.println(maxi);
	}
}