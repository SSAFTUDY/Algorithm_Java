import java.util.*;

class Solution {

    public int solution(int N, int number) {
        Set<Integer>[] numSet = new Set[9];

        if (N == number){
            return 1;
        }

        for (int n = 1; n < 9; n++){
            numSet[n] = new HashSet<>();
            numSet[n].add(Integer.parseInt(String.valueOf(N).repeat(n)));
            for (int i = 1; i <= n / 2; i++){

                for (int num1 : numSet[i]){
                    for (int num2 : numSet[n - i]){
                        numSet[n].add(num1 + num2);
                        numSet[n].add(num1 * num2);
                        numSet[n].add(num1 - num2);
                        numSet[n].add(num2 - num1);
                        if (num2 != 0) numSet[n].add(num1 / num2);
                        if (num1 != 0) numSet[n].add(num2 / num1);
                    }
                }

                if (numSet[n].contains(number)){
                    return n;
                }

            }
        }
        return -1;
    }
    
}
