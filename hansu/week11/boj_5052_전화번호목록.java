/**
 * 메모리: 102,304kb
 * 시간: 368ms
 */
import java.io.*;

class Trie {

    private final Node root = new Node();

    private static class Node {
        Node[] children = new Node[10];
        boolean isEOW;
    }

    public boolean add(String s){
        Node node = root;

        for (int i = 0; i < s.length(); i++){
            int num = s.charAt(i) - '0';

            if (node.children[num] == null){
                node.children[num] = new Node();
            }
            node = node.children[num];

            //접두어가 있는지 확인
            if (node.isEOW){
                return false;
            }
        }
        node.isEOW = true;

        //접두어인지 확인
        for (Node child : node.children){
            if (child != null){
                return false;
            }
        }
        return true;
    }

    public void clear(){
        for (int i = 0; i < 10; i++){
            root.children[i] = null;
        }
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        while (T-- > 0){
            int N = Integer.parseInt(br.readLine());
            boolean isConsistent = true;

            trie.clear();
            while (N -- > 0){
                String line = br.readLine();

                if (isConsistent){
                    isConsistent = trie.add(line);
                }
            }
            sb.append(isConsistent ? "YES\n" : "NO\n");
        }
        System.out.print(sb);
    }

}
