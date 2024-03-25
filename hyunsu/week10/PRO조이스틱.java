class Solution {
    
    public int solution(String name) {
        int answer = 0;
        int leftRight = name.length() - 1;
        
        for (int i = 0; i < name.length(); i++) {
            //i번째 알파벳을 찾는 방법 -> 위로 올려서 찾기 vs 아래로 내려서 찾기 중 더 작은 것
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            //
            if (i < name.length() - 1 && name.charAt(i + 1) == 'A') {
                int a = i + 1;
                while (a < name.length() && name.charAt(a) == 'A') {
                    a++;
                }
                leftRight = Math.min(leftRight, i * 2 + (name.length() - a));
                leftRight = Math.min(leftRight, (name.length() - a) * 2 + i);
            }
        }
        
        return answer + leftRight;
    }
}
