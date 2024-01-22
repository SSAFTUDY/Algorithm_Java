import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node{
        Node left;
        Node right;
        Point point;

        public Node() {
        }

        public Node(Point point) {
            this.point = point;
        }
    }

    static class LinkedList{
        Node head;
        Node tail;

        public LinkedList() {
            head = new Node();
            tail = new Node();
            head.right = tail;
            tail.left = head;
        }

        //이동하고 꼬리 칸 비워주기
        public Point removeLast() {
            Node curr = tail.left;
            curr.left.right = tail;
            tail.left = curr.left;
            return curr.point;
        }

        //사과를 먹는 경우
        public void add(int x, int y) {
            Node addNode = new Node(new Point(x,y));
            addNode.left = head;
            addNode.right = head.right;
            head.right.left = addNode;
            head.right = addNode;
        }
    }

    //방향
    static Point getDirection(String direction){
        switch (direction){
            case "R": return new Point(0,1);
            case "L": return new Point(0,-1);
            case "U": return new Point(-1,0);
            case "D": return new Point(1,0);
        }
        return null;
    }

    static String changeDirection(String curr, String C){
        if (C.equals("L")) {
            switch (curr){
                case "R": return "U";
                case "U" : return "L";
                case "L" : return "D";
                case "D" : return "R";
            }
        }
        if(C.equals("D")){
            switch (curr){
                case "R": return "D";
                case "D" : return "L";
                case "L" : return "U";
                case "U" : return "R";
            }
        }
        return null;
    }

    static int move(int x, int y, String dir, int cnt) {
        Point p = getDirection(dir);
        int dx = x + p.x;
        int dy = y + p.y;
        //벽일 때
        if(dx<0 || dx>=N || dy<0 || dy>=N){
            return cnt;
        }
        //자기 몸일 때
        if(graph[dx][dy] == 2){
            return cnt;
        }
        //사과가 있을 때
        if (graph[dx][dy] == 1) {
            snake.add(dx, dy);
            graph[dx][dy] = 2;
        }
        //아무것도 없을 때
        else{
            snake.add(dx, dy);
            graph[dx][dy] = 2;
            Point point = snake.removeLast();
            graph[point.x][point.y] = 0;
        }
        if(change[cnt+1] != null){
            String c = change[cnt+1];
            dir= changeDirection(dir,c);
        }
        cnt = move(dx,dy,dir,cnt+1);
        return cnt;
    }

    static int N;
    static int[][] graph;
    static String[] change = new String[10001];
    static LinkedList snake = new LinkedList();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        graph = new int[N][N];
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x-1][y-1] = 1;
        }
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String y = st.nextToken();
            change[x] = y;
        }

        snake.add(0,0);
        graph[0][0] = 2;
        String dir = "R";
        int result = move(0,0,dir,0);
        System.out.println(result+1);

    }
}
