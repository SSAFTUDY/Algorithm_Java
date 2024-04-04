/**
 * 메모리: 88472kb
 * 시간: 792ms
 */
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new TreeMap<>();
        int total = 0;
        String s;

        while((s = br.readLine()) != null){
            map.put(s, map.getOrDefault(s, 0) + 1);
            total++;
        }

        for (String tree : map.keySet()){
            sb.append(String.format("%s %.4f\n", tree, 100.0 * map.get(tree) / total));
        }
        System.out.print(sb);
    }

}
