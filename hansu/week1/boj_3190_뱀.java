import java.io.*;
import java.util.*;

class MyQueue<T> implements Iterable<T> {
    private Node head = new Node();
    private Node rear = head;
    private int size;
    private class Node{
        T val;
        Node next;

        Node(){}
        Node(T val){this.val = val;}
    }

    public void add(T val){
        size++;
        rear = rear.next = new Node(val);
    }

    public T remove(){
        if (size == 0)
            throw new NoSuchElementException();
        size--;
        return (head = head.next).val;
    }

    public T elementLast(){
        if (size == 0)
            throw new NoSuchElementException();
        return rear.val;
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<>(){
            Node node = head;

            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public T next() {
                return (node = node.next).val;
            }
        };
    }
}

public class Main {
    private static final int[][] D = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final BitSet apples = new BitSet();
    private static int[][] turns;
    private static int N, time = 0;
    private static boolean isOnApple(int[] now){
        boolean res = apples.get(N * now[0] + now[1]);
        apples.clear(N * now[0] + now[1]);
        return res;
    }
    private static boolean contains(MyQueue<int[]> q, int[] next){
        for (int[] body : q)
            if (Arrays.equals(body, next))
                return true;
        return false;
    }
    private static boolean go(MyQueue<int[]> q, int dir){
        int[] head = q.elementLast();
        int[] next = new int[]{head[0] + D[dir][0], head[1] + D[dir][1]};

        time++;
        if (next[0] >= 0 && next[1] >= 0 && next[0] < N && next[1] < N && !contains(q, next)) {
            if (!isOnApple(next))
                q.remove();
            q.add(next);
            return true;
        }
        return false;
    }
    private static int playGame(){
        MyQueue<int[]> q = new MyQueue<>();
        int[] head = {0, 0};
        int dir = 0;

        q.add(head);
        for (int[] turn : turns){
            for (int i = time; i < turn[0]; i++)
                if (!go(q, dir))
                    return time;
            dir = (dir + turn[1] + 4) % 4;
        }
        while (go(q, dir));
        return time;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine()), L;

        for (int i = 0; i < K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            apples.set(N * (Integer.parseInt(st.nextToken())-1) + Integer.parseInt(st.nextToken())-1);
        }
        L = Integer.parseInt(br.readLine());
        turns = new int[L][];
        for (int i = 0; i < L; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            turns[i] = new int[]{Integer.parseInt(st.nextToken()), st.nextToken().equals("D") ? 1 : -1};
        }

        System.out.println(playGame());
    }
}
