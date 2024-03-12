/**
 * 메모리: 11,420kb
 * 시간: 80ms
 */
import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static int d;

    /** 사분면을 좌표로 (i:행, j:열) */
    private static long[] quadrant2Coords(char[] quadrant){
        long i = 0, j = 0;

        //큰 단위의 사분면부터 변환
        for (char n : quadrant){
            //세분화(각 좌표 두배씩 증가)
            i *= 2;
            j *= 2;

            //3, 4사분면은 아래쪽으로 한칸 증가
            if (n == '3' || n == '4'){
                i++;
            }
            //1, 4사분면은 오른쪽으로 한칸 증가
            if (n == '1' || n == '4'){
                j++;
            }
        }
        return new long[]{i, j};
    }

    /** 좌표 이동 후 범위 안인지 확인 */
    private static boolean moveIfCan(long[] coords, long x, long y){
        coords[0] -= y; //위로 y만큼 이동
        coords[1] += x; //오른쪽으로 x만큼 이동
        return coords[0] >= 0 && coords[1] >= 0 && coords[0] < (1L << d) && coords[1] < (1L << d);
    }

    /** 좌표를 사분면으로 */
    private static String coords2Quadrant(long[] coords){
        char[] quadrant = new char[d];

        //작은 범위의 사분면부터 파악
        for (int n = d - 1; n >= 0; n--){
            long i = coords[0], j = coords[1];

            //0행 1열은 1사분면
            if (i % 2 == 0 && j % 2 == 1){
                quadrant[n] = '1';
            }
            //0행 0열은 2사분면
            else if (i % 2 == 0 && j % 2 == 0){
                quadrant[n] = '2';
            }
            //1행 0열은 3사분면
            else if (i % 2 == 1 && j % 2 == 0){
                quadrant[n] = '3';
            }
            //1행 1열은 4사분면
            else if (i % 2 == 1 && j % 2 == 1){
                quadrant[n] = '4';
            }

            //그룹화 (각 좌표 1/2씩 감소)
            coords[0] /= 2;
            coords[1] /= 2;
        }
        return new String(quadrant);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st1.nextToken());
        char[] quadrant = st1.nextToken().toCharArray();
        long x = Long.parseLong(st2.nextToken());
        long y = Long.parseLong(st2.nextToken());

        //사분면 -> 좌표
        long[] coords = quadrant2Coords(quadrant);
        //이동
        if (!moveIfCan(coords, x, y)){
            System.out.println(-1);
            return;
        }
        //좌표 -> 사분면
        System.out.println(coords2Quadrant(coords));
    }

}
