import java.io.*;
import java.util.*;

class Node {
    Map<Character, Node> children = new HashMap<>();
    boolean isEOW;
}

class Trie {
    public final Node root = new Node();

    public void add(String s){
        Node node = root;

        for (int i = 0; i < s.length(); i++){
            node.children.putIfAbsent(s.charAt(i), new Node());
            node = node.children.get(s.charAt(i));
        }
        node.isEOW = true;
    }
}

public class Main {

    private static final int[][] D = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private static final Trie trie = new Trie();
    private static char[][] puzzle;
    private static int wordCnt;

    private static void dfs(Node node, int visited, int i, int j){
        node = node.children.get(puzzle[i][j]);

        if (node == null){
            return;
        }

        if (node.isEOW){
            wordCnt++;
            node.isEOW = false;
            if (node.children.isEmpty()){
                return;
            }
        }

        for (int[] d : D){
            int nextI = i + d[0], nextJ = j + d[1];

            if (nextI >= 0 && nextJ >= 0 && nextI < 5 && nextJ < 5 && (visited & (1 << nextI * 5 + nextJ)) == 0){
                dfs(node, visited | (1 << i * 5 + j), nextI, nextJ);
            }
        }
    }

    public static int getWordCnt(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                dfs(trie.root, 0, i, j);
            }
        }
        return wordCnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));

        //init dict
        String s;
        while((s = dict.readLine()) != null){
            trie.add(s);
        }

        //parse puzzle
        puzzle = new char[5][5];
        for (int i = 0; i < 5; i++){
            String line = br.readLine();
            for (int j = 0; j < 5; j++){
                puzzle[i][j] = line.charAt(2 * j);
            }
        }

        System.out.println(getWordCnt());
    }

}
