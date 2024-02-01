import java.io.*;
import java.util.*;
import java.math.BigInteger;

class Main {

    private static final int P = 1_000_000_007;
    private static List<Integer>[] adj;
    private static int[] parent;
    private static int[] depths;
    private static int[] A;

    private static void setParent(){
        Queue<Integer> q = new LinkedList<>();
        int depth = 0;

        parent[1] = 1;
        q.add(1);
        while(!q.isEmpty()){
            int size = q.size();

            depth++;
            for (int i = 0; i < size; i++) {
                int p = q.remove();

                for (int c : adj[p]) {
                    if (parent[c] == 0) {
                        parent[c] = p;
                        depths[c] = depth;
                        q.add(c);
                    }
                }
            }
        }

    }

    private static int findCommonAncestor(int a, int b){
        while(depths[a] > depths[b]){
            a = parent[a];
        }
        while(depths[a] < depths[b]){
            b = parent[b];
        }

        while (a != b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static String getString(int s, int e){
        List<Integer> fromS = new ArrayList<>();
        Stack<Integer> toE = new Stack<>();
        StringBuilder res = new StringBuilder();
        int commonAncestor = findCommonAncestor(s, e);

        for (int n = s; n != commonAncestor; n = parent[n]){
            fromS.add(n);
        }
        fromS.add(commonAncestor);
        for (int n = e; n != commonAncestor; n = parent[n]){
            toE.push(n);
        }

        for (int n : fromS){
            res.append(A[n]);
        }
        while (!toE.isEmpty()){
            res.append(A[toE.pop()]);
        }

        return new BigInteger(res.toString()).mod(BigInteger.valueOf(P)).toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st =  new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());

        // 집 정보 생성
        A = new int[N + 1];
        st  = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 인접 리스트 생성
        adj = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        //parent 배열 및 depths 배열 생성
        parent = new int[N + 1];
        depths = new int[N + 1];
        setParent();

        //놀이 진행
        for (int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(getString(s, e)).append('\n');
        }

        System.out.print(sb);
    }

}
