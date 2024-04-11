/**
 * 메모리: 17,308kb
 * 시간: 156ms
 */
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = 0, b = 0, c = 0, res = 0;
        String s = br.readLine();

        while (c < s.length()){
            while(b < s.length() && s.charAt(b) != 'B'){
                b++;
            }
            
            if (c < b){
                c = b;
            }
            
            while(c < s.length() && s.charAt(c) != 'C'){
                c++;
            }

            if (c < s.length()) {
                b++;
                c++;
                res++;
            }
        }

        while (b < s.length()){
            while (a < s.length() && s.charAt(a) != 'A'){
                a++;
            }
            
            if (b < a){
                b = a;
            }
            
            while(b < s.length() && s.charAt(b) != 'B'){
                b++;
            }

            if (b < s.length()) {
                a++;
                b++;
                res++;
            }
        }
        System.out.println(res);
    }

}
