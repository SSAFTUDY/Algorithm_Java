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

public class BOJ_1753_2 {

	static List<Edge>[] adjList;
	static int[] minDist;

	static class Edge {
		int startNode;
		int adjNode;
		int cost;

		public Edge(int startNode, int adjNode, int cost) {
			super();
			this.startNode = startNode;
			this.adjNode = adjNode;
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
			adjList[v1].add(new Edge(v1, v2, cost));
		}

		// process
		minDist = new int[V + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		minDist[startIdx] = 0;

		Queue<Edge> que = new PriorityQueue<Edge>(Comparator.comparingInt(o -> o.cost));
		// 시작 정점의 모든 인접 노드들을 우선순위 큐에 넣어주기
		que.addAll(adjList[startIdx]);
		while (!que.isEmpty()) {
			// 우선순위 큐에 들어간 정점을 하나씩 꺼내면서 탐색
			Edge edge = que.poll();
			
			if (minDist[edge.adjNode] == Integer.MAX_VALUE) {
				// 현재 방문한 노드가 처음 방문한 노드라면 거리를 업데이트 해주고 우선순위 큐에 
				// 모든 인접 노드들을 넣어주기
				que.addAll(adjList[edge.adjNode]);
				minDist[edge.adjNode] = minDist[edge.startNode] + edge.cost;
			} else {
				// 그렇지 않다면 값을 비교 후 갱신
				minDist[edge.adjNode] = Math.min(minDist[edge.adjNode], minDist[edge.startNode] + edge.cost);
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
