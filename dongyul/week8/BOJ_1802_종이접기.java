import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1802 {

    static String str;
    static StringBuilder sb = new StringBuilder();

    private static boolean sol(int len, int idx) {
        if(len == 3){
            if(str.charAt(idx-1) == str.charAt(idx+1)){
                return false;
            }
            return true;
        }

        //현재 중간지점에서 대칭되는 곳이 전부 다른지 확인
        for(int i = 1; i<len/2; i++){
            if(str.charAt(idx-i)==str.charAt(idx+i)){
                return false;
            }
        }

        //전부 다르다면 중앙점을 양쪽으로 이동해서 검사
        if(sol(len/2,len/4) && sol(len/2, idx+len/4+1)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            str = br.readLine();
            if(str.length()==1){
                sb.append("YES").append("\n");
                continue;
            }
            boolean flag = sol(str.length(),str.length()/2);
            if(flag){
                sb.append("YES").append("\n");
            }
            else{
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

}
