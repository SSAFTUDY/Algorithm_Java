import java.io.*;

public class BOJ1406 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static char[] arr;
    static MyLinkedList<Character> list = new MyLinkedList<>();

    static String solve() throws IOException {
        for (char c : arr) {
            list.add(c);
        }
        
        for (int i = 0; i < M; i++) {
            String oper = br.readLine();
            //System.out.println(list.toString());
            switch (oper.charAt(0)) {
                case 'L':
                    list.moveLeft();
                    break;
                case 'D':
                    list.moveRight();
                    break;
                case 'B':
                    list.remove();
                    break;
                case 'P':
                    list.add(oper.charAt(2));
                    break;
            }
        }

        return list.toString();
    }

    public static void main(String[] args) throws IOException {
        arr = br.readLine().toCharArray();
        N = arr.length;
        M = Integer.parseInt(br.readLine());
        System.out.println(solve());
    }

    static class Node<E> {

        E item;
        Node<E> prev;
        Node<E> next;

        public Node() {}

        Node(E item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    static class MyLinkedList<E> {

        private Node<E> first; //비어있는 노드 (이 다음부터 값 등장)
        private Node<E> cursor;

        public MyLinkedList() {
            this.first = new Node<>();
            this.cursor = first;
        }
        
        //커서 움직이기
        public void moveLeft() {
            if (this.cursor.prev != null) {
                this.cursor = this.cursor.prev;
            }
        }
        
        public void moveRight() {
            if (this.cursor.next != null) {
                this.cursor = this.cursor.next;
            }
        }

        //삽입
        public void add(E item) {
            Node<E> node = new Node<>(item);
            if (this.cursor.next != null) {
                //중간
                node.next = this.cursor.next;
                node.prev = this.cursor;
                this.cursor.next.prev = node;
            } else {
                node.prev = this.cursor;
            }
            this.cursor.next = node;
            this.cursor = node;
        }

        //삭제
        public void remove() {
            if (this.cursor.prev != null) {
                //커서가 중간에 위치한 경우 -> 커서의 양쪽을 끊고 서로를 잇는다.
                if (this.cursor.next != null) {
                    this.cursor.prev.next = this.cursor.next;
                    this.cursor.next.prev = this.cursor.prev;
                } else {
                    //커서가 오른쪽 맨 끝에 위치한 경우 -> 왼쪽만 끊는다.
                    this.cursor.prev.next = null;
                }
                this.cursor = this.cursor.prev;
            }
        }

        //현재까지 리스트 안에 들어있는 값 문자열로 반환
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node<E> node = this.first.next;
            while (node != null) {
                sb.append(node.item);
                node = node.next;
            }

            return sb.toString();
        }


//        //이 위치에 삽입이 가능한지 파악
//        private void checkAddIndex(int index) {
//            if (index < 0 || index > this.size) {
//                throw new IndexOutOfBoundsException();
//            }
//        }
//
//        //이 위치에서 노드를 탐색할 수 있는지 파악
//        private void checkElementIndex(int index) {
//            if (index < 0 || index >= this.size) {
//                throw new IndexOutOfBoundsException();
//            }
//        }

    }
}
