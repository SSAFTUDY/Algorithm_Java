import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node{
        char value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(char value) {
            this.value = value;
        }
    }

    static class Editor{
        int size;
        Node cursor;
        Node head;

        public Editor(String str) {
            this.size = str.length();
            head = new Node();
            cursor = head;
            for (int i =0; i<str.length(); i++) {
                Node curr = new Node(str.charAt(i));
                cursor.right = curr;
                curr.left = cursor;
                cursor = curr;
            }
        }

        public void moveLeft() {
            if(cursor.left !=null){
                cursor = cursor.left;
            }
        }

        public void moveRight(){
            if(cursor.right !=null){
                cursor = cursor.right;
            }
        }

        public void delete(){
            if(cursor.left!=null){
                if(cursor.right!=null) {
                    cursor.left.right = cursor.right;
                    cursor.right.left = cursor.left;
                }
                else{
                    cursor.left.right = null;
                }
                cursor = cursor.left;
                size-=1;
            }
        }

        public void add(char value){
            Node node = new Node(value);
            if(cursor.right!=null){
                node.right = cursor.right;
                node.left = cursor;
                cursor.right.left = node;
                cursor.right = node;
            }
            else{
                node.left=cursor;
                cursor.right = node;
            }
            size+=1;
            cursor = node;
        }

        @Override
        public String toString() {
            Node curr =  head.right;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i<size; i++){
                sb.append(curr.value);
                curr = curr.right;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Editor editor = new Editor(br.readLine());
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            switch (order){
                case "L" : {
                    editor.moveLeft();
                    break;
                }
                case "D" : {
                    editor.moveRight();
                    break;
                }
                case "B" : {
                    editor.delete();
                    break;
                }
                case "P" : {
                    String s = st.nextToken();
                    editor.add(s.charAt(0));
                    break;
                }
            }
        }
        System.out.println(editor.toString());
    }
}
