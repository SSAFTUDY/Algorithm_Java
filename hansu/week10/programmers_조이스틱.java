import java.util.*;

class Solution {

    private static class Node{

        char[] name;
        int curIdx;
        int ctrCnt;     //현재까지 움직인 횟수

        public Node(char[] name, int curIdx, int ctrCnt) {
            this.name = name;
            this.curIdx = curIdx;
            this.ctrCnt = ctrCnt;
        }

        public Node(Node node){
            name = Arrays.copyOf(node.name, node.name.length);
            curIdx = node.curIdx;
            ctrCnt = node.ctrCnt;
        }

        //왼쪽 움직임은 새 노드로 (먼저 호출해야 함)
        public Node moveLeft(){
            Node newNode = new Node(this);

            while (newNode.name[newNode.curIdx] == 'A'){
                newNode.curIdx = (newNode.curIdx + newNode.name.length - 1) % newNode.name.length;
                newNode.ctrCnt++;
            }
            newNode.setCharToA();
            return newNode;
        }

        //오른쪽 움직임은 자기 자신으로 (나중에 호출해야 함)
        public Node moveRight(){
            while (name[curIdx] == 'A'){
                curIdx = (curIdx + 1) % name.length;
                ctrCnt++;
            }
            setCharToA();
            return this;
        }

        //현재 인덱스를 A로 만들고 횟수 가산
        private void setCharToA(){
            ctrCnt += Math.min(name[curIdx] - 'A', 'Z' - name[curIdx] + 1);
            name[curIdx] = 'A';
        }

    }


    public int solution(String name) {
        Queue<Node> q = new ArrayDeque<>();
        char[] chars = name.toCharArray();
        int notACnt = 0;

        //A가 아닌 문자의 개수 파악
        for (char c : chars){
            if (c != 'A'){
                notACnt++;
            }
        }

        //bfs
        q.add(new Node(chars, 0, 0));
        while (notACnt-- > 0){
            int size = q.size();

            while (size-- > 0){
                Node node = q.remove();
                Node left = node.moveLeft();
                Node right = node.moveRight();

                q.add(left);
                q.add(right);
            }
        }

        //마지막 경우 중 최소 횟수 선택
        int answer = Integer.MAX_VALUE;
        for (Node node : q){
            answer = Math.min(answer, node.ctrCnt);
        }
        return answer;
    }

}

class Programmers {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String name = "JEROEN";

        System.out.println(sol.solution(name));
    }
}
