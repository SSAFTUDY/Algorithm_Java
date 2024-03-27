import java.util.*;

class Solution {
    
    class State {
        char[] arr;
        int idx;
        int count;
        
        State(char[] arr, int idx, int count) {
            this.arr = arr;
            this.idx = idx;
            this.count = count;
        }
        
        boolean isArrEquals(String name) {
            if(arr.length != name.length())
                return false;
        
            for(int i=0; i<name.length(); i++) {
                if(arr[i] != name.charAt(i))
                    return false;
            }
            return true;
        }
    }
    
    public int solution(String name) {
        int answer = Integer.MAX_VALUE;
        int length = name.length();
        
        int[] minCount = new int[length];
        
        char[] arr = new char[length];
        for(int i=0; i<length; i++) {
            arr[i] = 'A';
            minCount[i] = Integer.MAX_VALUE;
        }
        Queue<State> que = new ArrayDeque<>();
        minCount[0] = changeCost(name.charAt(0));
        arr[0] = name.charAt(0);
        que.add(new State(arr, 0, minCount[0]));
        
        while(!que.isEmpty()) {
            State state = que.poll();
            if(minCount[state.idx] < state.count)
                continue;
            // 2가지 경우 존재   

            // 1. 현재 위치에서 왼쪽으로 간 후 글자를 바꾸는 것이
                // 배열에 저장된 값보다 작다면 현재 위치를 특정 글자로 바꾼다
            // 2. 현재 위치에서 오른쪽으로 간 후 글자를 바꾸는 것이
                // 배열에 저장된 값보다 작다면 현재 위치를 특정 글자로 바꾼다
        
            // 글자를 바꾼 후 배열이 만들려는 이름과 일치하는지 항상 확인한다
            // 일치한다면 최소값을 비교한다
            // 일치하지 않는다면 큐에 집어넣는다
            
            int leftIdx = (state.idx + length - 1) % length;
            char[] leftArr = getArr(state.arr);
            int leftCount = state.count + changeCost(name.charAt(leftIdx)) + 1; // 왼쪽으로 이동 후 바꾸는 비용
            if (leftArr[leftIdx] != name.charAt(leftIdx) || minCount[leftIdx] > leftCount) {
                leftArr[leftIdx] = name.charAt(leftIdx);
                minCount[leftIdx] = leftCount;
                State leftState = new State(leftArr, leftIdx, leftCount);
                if(leftState.isArrEquals(name)) {
                    answer = Math.min(answer, leftCount);
                }
                else {
                    que.add(leftState);
                }
            }
            
            int rightIdx = (state.idx + 1) % length;
            char[] rightArr = getArr(state.arr);
            int rightCount = state.count + changeCost(name.charAt(rightIdx)) + 1;
            if (rightArr[rightIdx] != name.charAt(rightIdx) || minCount[rightIdx] > rightCount) {
                rightArr[rightIdx] = name.charAt(rightIdx);
                minCount[rightIdx] = rightCount;
                State rightState = new State(rightArr, rightIdx, rightCount);
                
                if(rightState.isArrEquals(name)) {
                    answer = Math.min(answer, rightCount);
                }
                else {
                    que.add(rightState);
                }
            }
            
        }
        
        return answer;
    }
    
    char[] getArr(char[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }
    
    int changeCost(char c) {
        return Math.min(c - 'A', ('Z' - 'A') - (c - 'A') + 1);
    }
}
