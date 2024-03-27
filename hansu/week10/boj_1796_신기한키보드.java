import java.io.*;
import java.util.*;

public class Main {

    /** Map<목표 문자, List<해당 문자의 인덱스>> */
    private static final TreeMap<Character, List<Integer>> indices = new TreeMap<>();;

    private static class Node {
        int idx;
        int cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        public static Node min(Node n1, Node n2){
            return n1.cnt <= n2.cnt ? n1 : n2;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    // {좌 우선 탐색, 우 우선 탐색} 결과쌍 반환
    private static Node[] search(Node node, char key){
        List<Integer> list = indices.get(key);
        int leftIdx = list.get(0), rightIdx = list.get(list.size() - 1); //목표 문자의 양 끝 인덱스
        int leftCnt = node.cnt, rightCnt = node.cnt; //각 우선 탐색별 총 횟수
        int nextLeftIdx, nextRightIdx; //각 우선 탐색별 최종 도착 인덱스

        //이동 횟수 추가
        if (node.idx <= leftIdx){
            //오른쪽으로만 이동
            leftCnt += rightIdx - node.idx;
            rightCnt += rightIdx - node.idx;
            nextLeftIdx = nextRightIdx = rightIdx;
        } else if (node.idx >= rightIdx){
            //왼쪽으로만 이동
            leftCnt += node.idx - leftIdx;
            rightCnt += node.idx - leftIdx;
            nextLeftIdx = nextRightIdx = leftIdx;
        } else {
            //한쪽 끝까지 갔다가 다른쪽 끝으로 이동
            leftCnt += rightIdx - 2 * leftIdx + node.idx;
            rightCnt += 2 * rightIdx - leftIdx - node.idx;
            nextLeftIdx = rightIdx;
            nextRightIdx = leftIdx;
        }

        //엔터 횟수 추가
        leftCnt += list.size();
        rightCnt += list.size();

        //{↪, ↩}
        return new Node[]{new Node(nextLeftIdx, leftCnt), new Node(nextRightIdx, rightCnt)};
    }

    private static int getMinCnt(){
        int N = indices.size();
        Node[][] dp = new Node[N + 1][2]; // dp[step][좌 우선 탐색: 0 / 우 우선 탐색: 1]
        Iterator<Character> it = indices.keySet().iterator();

        dp[0][0] = dp[0][1] = new Node(0, 0);
        for (int i = 1; it.hasNext(); i++){
            char key = it.next();
            Node[] leftSearch = search(dp[i - 1][0], key);  // 좌 -> {좌, 우} 탐색
            Node[] rightSearch = search(dp[i - 1][1], key); // 우 -> {좌, 우} 탐색

            dp[i][0] = Node.min(leftSearch[0], rightSearch[0]); // (좌 -> 좌), (우 -> 좌) 중 작은 것
            dp[i][1] = Node.min(leftSearch[1], rightSearch[1]); // (좌 -> 우), (우 -> 우) 중 작은 것
//            System.out.println(Arrays.toString(dp[i]));
        }
        return Node.min(dp[N][0], dp[N][1]).cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        for (int i = 0; i < s.length(); i++){
            indices.putIfAbsent(s.charAt(i), new ArrayList<>());
            indices.get(s.charAt(i)).add(i);
        }
        System.out.println(getMinCnt());
    }

}
