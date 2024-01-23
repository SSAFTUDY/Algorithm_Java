import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

//담당자 기준, 전날에 본인이 포함되어 있으면 2*2*2
//담당자 기준, 전날에 본인이 포함되어 있지 않고
//	전날 한 명만 왔다면 2*2
//	전날 두 명만 왔다면 (2*2-1)*2
//	전날 세 명만 왔다면 2*2*2-1
//
//첫째 날	둘째 날	셋째 날
//AB	AC	(2*2-1)*2
//	BC	2*2*2
//	ABC	2*2*2
//	ADC	2*2*2-1	-> (2*2 - 1) * 2
//	BDC	2*2*2
//	ABDC	2*2*2
//
//ABC	C	2*2
//	AC	(2*2-1)*2
//	BC	2*2*2
//	DC	(2*2-1)*2
//	ABC	2*2*2	-> 2*2*2
//	ADC	2*2*2-1
//	BDC	2*2*2
//	ABDC	2*2*2
//
//ABD	AC
//	BC
//	DC
//	ABC		-> (2*2*2-1)
//	ADC
//	BDC
//	ABDC
//
//ABCD	C
//	AC
//	BC
//	DC
//	ABC		-> 2*2*2
//	ADC
//	BDC
//	ABCD
//
//다음 날 무조건 나와야 하는 사람 : 열쇠를 갖고 있는 사람
//
//AB
//
//AB -> 6
//ABC->8
//ABD->7
//ABCD->8

// {A, B, C, D}
//  0, 0, 0, 0 -> bit masking
//A : 3
//B : 2
//C : 1
//D : 0
//default = 0
//C 표현 : default << C;

public class Swea_3316 {

//	public static final String tc = "2\r\n" + "BC\r\n" + "ADCBBACDCBCBACBDCABDCBA";
	public static final String tc = "1\r\n" + "BC\r\n";

	public static int[] dp;
	public static int[] tmp;
	public static int sum;
	public static final int div = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(tc));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");

			// get input
			String input = br.readLine();
			char[] arr = new char[input.length() + 1];
			arr[0] = 'A';
			for (int i = 0; i < input.length(); i++) {
				arr[i + 1] = input.charAt(i);
			}
//			System.out.println(Arrays.toString(arr));
			
			// process
			dp = new int[16];
			dp[8] = 1;

			// dp
			for (int i = 0; i < arr.length; i++) {
				tmp = new int[16];

				for (int j = 1; j < 16; j++) {
					if (dp[j] == 0)
						continue;

					boolean[] current = intToBoolean(j); // 지금 누구 누구 있는지
					int next = arr[i + 1] - 'A';
					boolean[] available;
					boolean[] notAvailable = new boolean[4];
					notAvailable[next] = true;
					
					if (current[next]) {
						// 현재 인원 중 다음날 담당자인 사람이 있는 경우
						// 담당자 빼고 나머지 3명 마음대로 가능
//						int num = 15 ^ (1 << 3 - (next));
//						available = intToBoolean(num);
//						sum = 1 << 3 - (next);
					} else {
						// 다음날 담당자인 사람이 없는 경우
						// 현재 담당자도 빼야 함
//						int num = j;
//						num = num ^ (1 << 3 - (arr[i] - 'A'));
//						available = intToBoolean(num);
//						notAvailable[arr[i] - 'A'] = true;
//						sum = (1 << 3 - (next)) + (1 << 3 - (arr[i] - 'A'));
					}
					for (int k = 1; k <= 3; k++) {
						calculateTmp(available, notAvailable, 0, 0, k, j);
					}
				}
				System.out.println(Arrays.toString(tmp));
				dp = Arrays.copyOf(tmp, tmp.length);
			}
		}
		System.out.println(Arrays.toString(dp));
	}

	public static void calculateTmp(boolean[] available, boolean[] notAvailable, int currentCount, int startIdx, int max, int prevCount) {
		if (currentCount == max) {
			tmp[sum] = (tmp[sum] % div + dp[prevCount]) % div; // 이전 가지수
			return;
		} else {
			for (int i = startIdx; i < available.length; i++) {
				if (!available[i])
					continue;
				for (int j = i + 1; j < available.length; j++) {
					if(notAvailable[j])
						continue;
					sum += 1 << (3 - i);
					calculateTmp(available, notAvailable, currentCount + 1, i + 1, max, prevCount);
					sum -= 1 << (3 - i);
				}
			}
		}
	}

	public static boolean[] intToBoolean(int num) {
		boolean[] check = new boolean[4];
		for (int i = check.length - 1; i >= 0; i--) {
			if (num % 2 == 1) {
				check[i] = true;
			}
			num = num / 2;
		}
		return check;
	}

	public static int booleanToInt(boolean[] check) {
		int num = 0;
		for (int i = check.length - 1; i >= 0; i--) {
			if (check[i]) {
				num += 1 << (3 - i);
			}
		}
		return num;
	}
}
/*
 * 혁준아 열심히 하자^_^
 */
