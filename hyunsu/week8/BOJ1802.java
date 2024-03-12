package 분할정복;

public class BOJ1802 {

    static int T;
    static String paper; 
    
    // 주어진 문자열로 종이를 접을 수 있는지 판단
    // 종이는 항상 절반씩 접기 때문에, 가운데를 기준으로 절반씩 분할해서 확인
    // 중앙을 기준으로 양쪽의 접힌 부분이 대칭이어야 접을 수 있는 종이
    // 1000110 => 가운데 0을 기준으로 100, 110 => 1과 0 대칭
    // 10101 => 가운데 1을 기준으로 10, 01 => 비대칭 (1과 1, 0과 0)
    static boolean check(int l, int r) {
        if (l == r) {
            return true;
        }

        int mid = (l + r) / 2;
        for (int i = l; i < mid; i++) {
            // 중앙을 기준으로 1과 0이 대칭되지 않으면 접힐 수 없음
            if (paper.charAt(i) == paper.charAt(r - i)) {
                return false;
            }
        }

        return check(l, mid - 1) && check(mid + 1, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            paper = br.readLine();
            if (check(0, paper.length() - 1)) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }

}
