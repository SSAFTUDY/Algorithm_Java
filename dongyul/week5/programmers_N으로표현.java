/*
 * N개로 만들 수 있는 정수 -> N-i개로 만든 정수와 i개로 만든 정수의 조합
 * 중복방지를 위해 SET 사용
 * 55, 555와 같이 숫자를 하나 이어주는 것은 초기 값으로 설정하여 예외처리
 */
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int solution(int N, int number) {
        if(N == number){
            return 1;
        }

        //set배열 초기화
        Set<Integer>[] sets = new Set[9];
        for (int i = 0; i < 9; i++) {
            sets[i] = new HashSet<>();
        }

        //55, 555와 같은 수 미리 set에 넣어주기
        for (int i = 1; i < 9; i++) {
            int num = N;
            for (int j = 1; j < i; j++) {
                num+= N*Math.pow(10,j); 
            }
            if(num == number){
                return i;
            }
            sets[i].add(num);
        }

        //N-i + i개의 N으로 만든 숫자를 조합하여 set에 넣어주기, 넣어주면서 찾는 값이면 리턴
        for (int i = 2; i < 9; i++) {
            int p1 = 1;
            int p2 = i-1;
            while (p1<=i-1){
                Set<Integer> s1 = sets[p1];
                Set<Integer> s2 = sets[p2];
                for (Integer i1 : s1) {
                    for (Integer i2 : s2) {
                        if(i2 == 0){
                            continue;
                        }
                        if(i1+i2 == number || i1-i2 == number || i1*i2 == number || i1/i2 == number){
                            return i;
                        }
                        sets[i].add(i1+i2);
                        sets[i].add(i1-i2);
                        sets[i].add(i1*i2);
                        sets[i].add(i1/i2);
                    }
                }

                p1++;
                p2--;
            }
        }
        //최대 값이 8보다 크면 -1 리턴
        return -1;
    }
}
