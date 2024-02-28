import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("res/input.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	arr = new int[N];
    	for (int i = 0; i < N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
		}
    	Arrays.sort(arr);
    	int ans = 0;
    	int M = N-1;
    	int current = 0;
    	while (arr[current] < M) {
    		M -= arr[current]+1;
    		ans += arr[current];
    		current++;
    	}
    	ans += M;
    	System.out.println(ans);
    }
}