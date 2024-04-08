import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_16934 {

    static class Node {
        boolean isWord;
        int count;
        Map<Character, Node> childs;

        public Node() {
            isWord = false;
            childs = new HashMap<Character, Node>();
            count = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // get input
        int N = Integer.parseInt(br.readLine());
        Node root = new Node();
        for (int i = 0; i < N; i++) {
            Node head = root;
            String line = br.readLine();
            StringBuilder prefix = new StringBuilder();

            boolean flag = false;
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (!head.childs.containsKey(c)) {
                    head.childs.put(c, new Node());
                }
                head = head.childs.get(c);
                prefix.append(c);
                if (!head.isWord && !flag) {
                    flag = true;
                    sb.append(prefix.toString()).append('\n');
                }
                head.isWord = true;
            }
            if (!flag) {
                if (head.count == 0) {
                    sb.append(prefix.toString()).append('\n');
                } else {
                    sb.append(prefix.toString()).append(head.count + 1).append('\n');
                }
            }
            head.count++;
        }
        System.out.println(sb);
    }

}
