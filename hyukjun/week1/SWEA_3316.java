import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

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

//{A, B, C, D}
// 0, 0, 0, 0 -> bit masking

public class Swea_3316 {

	public static final String tc = "2\r\n" + "BC\r\n" + "ADCBBACDCBCBACBDCABDCBA";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(tc));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");

			// get input
			
		}
	}
}
