import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1105 {


    //두 수의 자릿 수 다르면 0개
    //큰 자릿수 부터 두 수 비교
    //계속 8이어야 8이 나올 수 있음, 다른 수가 나오면 그대로 탐색 종료
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        String a = st.nextToken();
        String b = st.nextToken();
        int answer = 0;
        if (a.length() == b.length()) {
            for (int i = 0; i < a.length(); i++) {
                if(a.charAt(i) != b.charAt(i)){
                    break;
                }
                else {
                    if(a.charAt(i) == '8' && b.charAt(i) == '8'){
                        answer+=1;
                    }
                }
            }
        }
        System.out.println(answer);
    }

}
