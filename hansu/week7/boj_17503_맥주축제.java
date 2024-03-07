/**
 * 메모리: 79,328kb
 * 시간: 952ms
 */
import java.io.*;
import java.util.*;

public class Solution {

    private static class Beer implements Comparable<Beer> {
        int prefer, level;

        public Beer(int prefer, int level) {
            this.prefer = prefer;
            this.level = level;
        }

        @Override
        public int compareTo(Beer o) {
            if (this.level == o.level){
                return o.prefer - this.prefer;
            }
            return this.level - o.level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int preferSum = 0, minLevel = 0;
        PriorityQueue<Beer> levelPq = new PriorityQueue<>();
        PriorityQueue<Beer> preferPq = new PriorityQueue<>(Comparator.comparingInt(b -> b.prefer));

        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int prefer = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            levelPq.add(new Beer(prefer, level));
        }

        for (int i = 0; i < N; i++){
            Beer beer = levelPq.remove();

            preferSum += beer.prefer;
            minLevel = beer.level;
            preferPq.add(beer);
        }
        while (!levelPq.isEmpty() && preferSum < M){
            Beer removedBeer = preferPq.remove();
            Beer addedBeer = levelPq.remove();

            preferSum -= removedBeer.prefer;
            preferSum += addedBeer.prefer;
            minLevel = addedBeer.level;
            preferPq.add(addedBeer);
        }

        if (preferSum >= M){
            System.out.println(minLevel);
            return;
        }
        System.out.println(-1);
    }

}
