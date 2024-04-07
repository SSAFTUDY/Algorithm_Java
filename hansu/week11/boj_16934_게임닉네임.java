/**
 * 메모리: 78,120kb
 * 시간: 384ms
 */
import java.io.*;
import java.util.*;

class Node {
    Map<Character, Node> children = new HashMap<>();
    int eowCnt;
}

class Trie {
    public final Node root = new Node();

    public String add(String s){
        Node node = root;
        int id = -1;

        for (int i = 0; i < s.length(); i++){
            if (!node.children.containsKey(s.charAt(i))) {
                node.children.put(s.charAt(i), new Node());
                if (id < 0){
                    id = i;
                }
            }
            node = node.children.get(s.charAt(i));
        }

        if (++node.eowCnt > 1){
            return s + node.eowCnt;
        }
        return id < 0 ? s : s.substring(0, id + 1);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        while (N-- > 0){
            sb.append(trie.add(br.readLine())).append('\n');
        }
        System.out.print(sb);
    }

}
