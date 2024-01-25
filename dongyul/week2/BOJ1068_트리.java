import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 입력 순서대로 트리에 add하면 입력값 중 부모노드가 없는 상황 발생 (아래는 예시)
 * 5
 * 1 -1 0 1 1
 * 3
 * 출력값: 2
 */

public class Main {

    static class Node {
        Node Parent;
        List<Node> children;
        int index;

        public Node(int index) {
            children = new ArrayList<>();
            this.index = index;
        }
    }

    static class Tree {
        Node root;

        public Tree() {
        }

        public void addRoot(int index){
            root = new Node(index);
        }

        //root 부터 시작하여 탐색
        public Node search(int index) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node curr = stack.pop();
                if (curr.index == index) {
                    return curr;
                }
                if (!curr.children.isEmpty()) {
                    for (Node node : curr.children) {
                        stack.push(node);
                    }
                }
            }
            return null;
        }

        //트리에 삽입 (부모 노드가 트리에 없으면 예외발생)
        public void add(int parentIndex, int index){
            Node parent = search(parentIndex);
            Node child = new Node(index);
            parent.children.add(child);
            child.Parent = parent;
        }

        //부모 노드의 자식리스트에서 지우기
        public void remove(int index) {
            Node searchNode = search(index);
            if(searchNode == root){
                root = null;
            }
            else {
                searchNode.Parent.children.remove(searchNode);
                searchNode.Parent = null;
            }
        }

        //root 부터 시작하여 Stack에 넣으며 탐색 시작, 자식이 없으면 리프노드로 카운트, 있으면 스택에 넣어줌
        public int countLeap() {
            if(root == null){
                return 0;
            }
            int cnt = 0;
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node curr = stack.pop();
                if (curr.children.isEmpty()) {
                    cnt += 1;
                } else {
                    for (Node node : curr.children) {
                        stack.push(node);
                    }
                }
            }
            return cnt;
        }
    }

    //(x,y) 편의를 위해 만든 클래스
    static class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Tree tree = new Tree();

        Deque<Pair> deque = new LinkedList<>(); //부모가 트리에 존재하는 경우만 트리에 넣기 위해 사용
        int[] hasParent = new int[N];    //부모가 만들어져있는지 체크하기 위한 배열

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n == -1){    //루트 노드일때
                hasParent[i] = 1;
                tree.addRoot(i);
                continue;
            }
            deque.addLast(new Pair(n,i)); //(부모인덱스, 본인인덱스), 트리에 삽입하지 않고 우선 deque에 넣음
        }

        while (!deque.isEmpty()){
            Pair pair = deque.pollFirst();
            if (hasParent[pair.x] == 1){    //부모 노드가 트리에 있을 떼
                tree.add(pair.x, pair.y);
                hasParent[pair.y] = 1;
            }
            else{
                deque.addLast(pair);    //부모 노드가 아직 없으면 후순위로 미룸
            }
        }

        int removeIndex = Integer.parseInt(br.readLine());
        tree.remove(removeIndex);
        System.out.println(tree.countLeap());
    }

}
