import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> priorityQueueLowest = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            int k = Integer.parseInt(in.readLine());
            priorityQueueLowest.add(k);
        }
        int sum = 0;
        while(priorityQueueLowest.size() > 1) {
            int val = priorityQueueLowest.poll();
            int val2 = priorityQueueLowest.poll();
            sum += val + val2;
            priorityQueueLowest.add(val + val2);
        }
        System.out.print(sum);
    }
}