import java.io.*;
import java.util.*;

public class SWEA1230 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static String[] original;
    static MyLinkedList<String> list;

    static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        
        //리스트에 원본 암호문 뭉치 삽입
        for (String s : original) {
            list.addLast(s);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            switch (oper) {
                case "I":
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < y; j++) {
                        list.add(++x, st.nextToken());
                    }
                    break;
                case "D":
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < b; j++) {
                        list.remove(a + 1);
                    }
                    break;
                case "A":
                    int z = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < z; j++) {
                        list.addLast(st.nextToken());
                    }
            }
        }

        for (int i = 0; i < 10; i++) {
            sb.append(list.get(i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            list = new MyLinkedList<>();
            N = Integer.parseInt(br.readLine());

            original = new String[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                original[j] = st.nextToken();
            }

            M = Integer.parseInt(br.readLine());
            bw.write("#" + i + " " + solve() + "\n");
        }
        bw.flush();
        bw.close();
    }

    static class Node<E> {

        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
            this.next = null;
        }
    }

    static class MyLinkedList<E> {

        Node<E> head;

        //index에 위치한 노드 다음에 삽입
        public void add(int index, E item) {
            if (index == 0) {
                Node<E> node = new Node<>(item);
                node.next = this.head;
                this.head = node;
            } else {
                Node<E> tmp = this.head;
                for (int i = 0; i < index - 1 && tmp != null; i++) {
                    tmp = tmp.next;
                }

                if (tmp != null) {
                    Node<E> node = new Node<>(item);
                    node.next = tmp.next;
                    tmp.next = node;
                }
            }
        }

        public void addLast(E item) {
            Node<E> node = new Node<>(item);
            if (this.head == null) {
                this.head = node;
            } else {
                Node<E> tmp = this.head;
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                tmp.next = node;
            }
        }

        //index에 해당하는 노드를 지운다.
        public void remove(int index) {
            if (index == 0 && this.head != null) {
                this.head = this.head.next;
            } else {
                Node<E> tmp = this.head;
                for (int i = 0; i < index - 1 && tmp != null; i++) {
                    tmp = tmp.next;
                }
                if (tmp != null && tmp.next != null) {
                    tmp.next = tmp.next.next;
                }
            }
        }

        public E get(int index) {
            Node<E> tmp = this.head;
            for (int i = 0; i < index && tmp != null; i++) {
                tmp = tmp.next;
            }
            return tmp != null ? tmp.item : null;
        }
    }

}
