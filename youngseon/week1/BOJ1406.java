
import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException{
		
		StringBuilder result = new StringBuilder();  // 결과값 출력을 위해
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<String> str = new LinkedList<String>();    // 문자열 저장할 list 생성
		char[] ch = br.readLine().toCharArray();
		
		for (int i = 0; i < ch.length; i++) {
			str.add(String.valueOf(ch[i]));      // 문자 저장
		}
		int cursor = ch.length ;
	
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String inputCh = st.nextToken();
			if(inputCh.equals("P")) {   // 입력 받은 문자가 P라면
				String addCh = st.nextToken();    // 추가할 문자 받고
				str.add(cursor, addCh);	
				cursor++;
			}
			else if(inputCh.equals("L")) {
				if(cursor > 0) {
					cursor--;	
				}
			}
			else if(inputCh.equals("D")) {

				if(cursor <= str.size()-1) {
					cursor++;	
				}
			}
			else {
				if(cursor > 0) {
					str.remove(cursor-1);
					cursor--;
				}
			}
			
		}
		
		Iterator it = str.iterator();
		while(it.hasNext()){
			result.append(it.next());
		}
		
		System.out.println(result.toString());
	}

}
