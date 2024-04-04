import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ4358 {

    private static class Node{
        Map<Character, Node> childNode = new HashMap<>();
        boolean isEnd;
    }

    private static class Trie{
        Node rootNode = new Node();

        void insert(String str){
            Node curr = rootNode;
            for (int i = 0; i < str.length(); i++) {
                curr.childNode.putIfAbsent(str.charAt(i), new Node());
                curr = curr.childNode.get(str.charAt(i));
            }
            curr.isEnd = true;
        }

        boolean contains(String str){
            Node curr = rootNode;
            for (int i = 0; i < str.length(); i++) {
                if(curr == null){
                    return false;
                }
                Node next = curr.childNode.get(str.charAt(i));
                curr = next;
            }
            return curr.isEnd;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

//        Trie trie = new Trie();
        String input = "";
        int total = 0;
        Map<String, Integer> map = new HashMap<>();
        while ((input = br.readLine()) != null){
            total+=1;
            if(map.containsKey(input)){
                map.put(input,map.get(input)+1);
            }
             else{
                 map.put(input,1);
            }
        }
        Object[] arr = map.keySet().toArray();
        Arrays.sort(arr);

        for(int i = 0; i<arr.length; i++){
            String key = (String) arr[i];
            double cnt = ((double) map.get(key)/total) * 100;
            sb.append(key).append(" ").append(String.format("%.4f", cnt)).append("\n");
        }
        System.out.println(sb);
    }

}
