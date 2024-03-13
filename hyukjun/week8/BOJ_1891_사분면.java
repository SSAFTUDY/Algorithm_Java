package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1891 {

	static StringBuilder sb = new StringBuilder();

	static int length;
	static String num;

	static long rightMove;
	static long upMove;

	static long[] startCoord = new long[2];
	static long[] endCoord = new long[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		num = st.nextToken();

		st = new StringTokenizer(br.readLine());
		rightMove = Long.parseLong(st.nextToken());
		upMove = Long.parseLong(st.nextToken());

		// process
		// (0, 0)이 왼쪽 맨 위
		findStartCoord((long) 1 << length, 0, 0, 0);
		endCoord[0] = startCoord[0] - upMove;
		endCoord[1] = startCoord[1] + rightMove;

		if (endCoord[0] < 0 || endCoord[0] >= ((long) 1 << length) || endCoord[1] < 0
				|| endCoord[1] >= ((long) 1 << length)) {
			// 범위 밖으로 나가는 경우
			System.out.println(-1);
		} else {
			getEndString((long) 1 << length, 0, 0);
			System.out.println(sb);
		}

	}

	private static void getEndString(long squareLength, long startRow, long startCol) {
		long rowCriteria = startRow + squareLength / 2;
		long colCriteria = startCol + squareLength / 2;

//		System.out.println(startRow + ", " + startCol);
		// 1사분면
		if (endCoord[0] < rowCriteria && endCoord[1] >= colCriteria) {
			sb.append(1);
			if (squareLength != 2)
				getEndString(squareLength / 2, startRow, startCol + squareLength / 2);
		}

		// 2사분면
		else if (endCoord[0] < rowCriteria && endCoord[1] < colCriteria) {
			sb.append(2);
			if (squareLength != 2)
				getEndString(squareLength / 2, startRow, startCol);
		}

		// 3사분면
		else if (endCoord[0] >= rowCriteria && endCoord[1] < colCriteria) {
			sb.append(3);
			if (squareLength != 2)
				getEndString(squareLength / 2, startRow + squareLength / 2, startCol);
		}

		// 4사분면
		else {
			sb.append(4);
			if (squareLength != 2)
				getEndString(squareLength / 2, startRow + squareLength / 2, startCol + squareLength / 2);
		}

	}

	private static void findStartCoord(long squareLength, long startRow, long startCol, int idx) {
		int div = num.charAt(idx) - '0';

		if (squareLength == 2) {
			if (div == 1) {
				startCoord[0] = startRow;
				startCoord[1] = startCol + squareLength / 2;
			} else if (div == 2) {
				startCoord[0] = startRow;
				startCoord[1] = startCol;
			} else if (div == 3) {
				startCoord[0] = startRow + squareLength / 2;
				startCoord[1] = startCol;
			} else {
				startCoord[0] = startRow + squareLength / 2;
				startCoord[1] = startCol + squareLength / 2;
			}
		}

		else {
			if (div == 1) {
				findStartCoord(squareLength / 2, startRow, startCol + squareLength / 2, idx + 1);
			} else if (div == 2) {
				findStartCoord(squareLength / 2, startRow, startCol, idx + 1);
			} else if (div == 3) {
				findStartCoord(squareLength / 2, startRow + squareLength / 2, startCol, idx + 1);
			} else {
				findStartCoord(squareLength / 2, startRow + squareLength / 2, startCol + squareLength / 2, idx + 1);
			}
		}
	}

}
