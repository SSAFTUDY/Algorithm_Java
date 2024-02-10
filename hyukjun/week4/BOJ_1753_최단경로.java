package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1753 {

	static List<Edge>[] adjList;
	static int[] minDist;

	static class Edge {
		int idx;
		int cost;

		public Edge(int idx, int cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}

	}

	// Dijkstra
	// 방향 그래프
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		int startIdx = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjList[v1].add(new Edge(v2, cost));
		}

		// process
		// 거리 배열 초기화
		minDist = new int[V + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);

		// 우선순위 큐 사용
		Queue<Edge> que = new PriorityQueue<Edge>(Comparator.comparingInt(o -> o.cost));
		// 시작 노드 세팅
		que.add(new Edge(startIdx, 0));
		minDist[startIdx] = 0;
		while (!que.isEmpty()) {
			Edge currentEdge = que.poll();
			int currentIdx = currentEdge.idx;
			// 현재 거리 배열에 존재하는 값보다 que에서 꺼낸 거리 값이 더 크다면 무시
			if (minDist[currentIdx] < currentEdge.cost)
				continue;

			for (Edge nextEdge : adjList[currentIdx]) {
				// que에서 꺼낸 노드 주변의 노드들을 탐색하기
				// 새로 계산한 cost = 현재까지 계산한 현재 노드까지의 거리 + 현재 노드에서 부터 다음 노드까지의 거리
				int newCost = nextEdge.cost + minDist[currentIdx];
				if (newCost < minDist[nextEdge.idx]) {
					// 새로 계산한 거리가 더 작다면 거리 배열을 업데이트 해주고 큐에 추가
					minDist[nextEdge.idx] = newCost;
					que.add(new Edge(nextEdge.idx, newCost));
				}
			}
		}

		// output
		for (int i = 1; i <= V; i++) {
			if (minDist[i] == Integer.MAX_VALUE)
				sb.append("INF");
			else
				sb.append(minDist[i]);
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
