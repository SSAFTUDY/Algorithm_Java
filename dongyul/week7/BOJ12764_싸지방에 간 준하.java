import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ12764 {

    private static class Node implements Comparable<Node>{
        int start;
        int end;
        int comIdx;
        int comCnt;

        public Node(int start, int end, int comIdx, int cnt) {
            this.start = start;
            this.end = end;
            this.comIdx = comIdx;
            this.comCnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.start - o.start;
        }
    }

    private static class ComputerList implements Comparable<ComputerList> {
        int idx;
        int cnt;

        public ComputerList(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(ComputerList o) {
            return this.idx - o.idx;
        }
    }

    /**
     * 두 개의 최소힙 사용. 하나는 시간을 기준으로 한 힙으로 시작시간, 끝나는 시간 두 경우로 나눠서 생각. / 나머지 큐는 컴퓨터 리스트를 관리 (컴퓨터 번호, 사용횟수)
     * 시작인 경우에는 (시작 시간, 끝 시간, -1, -1) 과 같이 데이터가 들어가 있음.
     * 끝인 경우는 (끝 시간, -1, 컴퓨터 번호, 사용 횟수)와 같이 데이터가 들어가 있음.
     * 큐에서 꺼낸 데이터가 시작인 경우에는 컴퓨터리스트 최소 힙에서 꺼내서 (끝 시간, -1, 번호, 횟수) 넣어줌, 만약 컴퓨터 리스트에 값이 없으면 새로 만듬
     * 큐에서 꺼낸 데이터가 끝인 경우에는 컴퓨터 리스트 최소 힙에 (번호, 사용횟수)를 넣어주어 다음 시작할 때 사용할 수 있도록 관리
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> timeList = new PriorityQueue<>();
        PriorityQueue<ComputerList> computerList = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            timeList.add(new Node(s,e,-1,-1));
        }
        int idx = 0; //새로운 컴퓨터를 만들 때 사용될 인덱스
        while (!timeList.isEmpty()) {
            Node curr = timeList.poll();
            //꺼낸 데이터가 시작인 경우
            if(curr.comIdx == -1){
                if(computerList.isEmpty()){
                    timeList.add(new Node(curr.end,-1,idx++,1));
                }
                else{
                    ComputerList com = computerList.poll();
                    timeList.add(new Node(curr.end,-1,com.idx,com.cnt+1));
                }
            }
            //끝인 경우
            else if(curr.end == -1){
                computerList.add(new ComputerList(curr.comIdx,curr.comCnt));
            }
        }
        int answer = computerList.size();   //사용한 컴퓨터 개수
        int[] arr = new int[answer];
        for (ComputerList comList : computerList) {
            arr[comList.idx] = comList.cnt;
        }
        sb.append(answer).append("\n");
        for (int i = 0; i < answer; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
}
