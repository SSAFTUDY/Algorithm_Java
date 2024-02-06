package com.ssafy.ws.step2;

import java.io.*;
import java.util.*;

public class BOJ_4195 {

	static HashMap<String, Integer> friend; // 친구 이름과 parent 에서의 index 저장
	static int[] parent;  // 대표 노드 배열
	static int[] result;  // 이 노드를 대표 노드로 삼고있는 노드들의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) { // 테스트 케이스 크기 범위 안나와 있음

			int count = 0; // 현재 친구의 수

			friend = new HashMap<String, Integer>();

			int F = Integer.parseInt(br.readLine());
			parent = new int[F * 2];
			result = new int[F * 2]; 
			
			for (int j = 0; j < F; j++) { // 100000
				st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				String name2 = st.nextToken();

				int idx1 = 0, idx2 = 0; // idx1 -> 첫번째 친구의 parent 인덱스 , idx2 -> 두번째 친구의 parent 인덱스

				if (friend.containsKey(name1)) { // 첫번째 친구의 parent 인덱스 구하기
					idx1 = friend.get(name1);
				} else {
					friend.put(name1, count);
					idx1 = count;
					parent[count] = count;
					result[count] = 1;  // 대표노드 1개로 초기화
					count++;
				}

				if (friend.containsKey(name2)) { // 같은 방식으로 두번째 친구도 parent 인덱스 구하기
					idx2 = friend.get(name2);
				} else {
					friend.put(name2, count);
					idx2 = count;
					parent[count] = count;
					result[count] = 1;
					count++;
				}

				System.out.println(union(idx1, idx2)); // 대표노드 합치기
			}
		}
	}

	public static int union(int a, int b) { // union 연산
		a = find(a);
		b = find(b);
		if(a > b) {  // 값이 작은 것을 대표노드로 삼는다.-> b가 대표
			parent[a] = b;
			result[b] += result[a];
			return result[b];
		}
		if(a < b) {
			parent[b] = a;
			result[a]+= result[b];
			return result[a];
		}
		return result[a];  // 값이 같은 경우
	}

	public static int find(int n) { // find 연산
		if (n == parent[n]) {
			return n;
		}
		return parent[n] = find(parent[n]);
	}
}
