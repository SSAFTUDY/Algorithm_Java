/**
 * 메모리: 39,668kb
 * 시간: 324ms
 */
import java.io.*;
import java.util.*;

class Trie{

    private static class Node{
        Node[] children = new Node[26];
    }

    private final Node root;

    public Trie(){
        root = new Node();
    }

    public void add(String s){
        Node node = root;

        for (int i = 0; i < s.length(); i++){
            int idx = s.charAt(i) - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new Node();
            }
            node = node.children[idx];
        }
    }

    public boolean search(String s){
        Node node = root;

        for (int i = 0; i < s.length(); i++){
            int idx = s.charAt(i) - 'a';

            if (node.children[idx] == null){
                return false;
            }
            node = node.children[idx];
        }
        return true;
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int cnt = 0;
        Trie trie = new Trie();

        while (N-- > 0){
            trie.add(br.readLine());
        }

        while (M-- > 0){
            if (trie.search(br.readLine())){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}
