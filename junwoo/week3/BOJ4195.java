import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int T, F; 
    static HashMap<String, String> mapp = new HashMap<String, String>();
    static HashMap<String, Integer> map_cnt = new HashMap<String, Integer>();

    public static String find_parent(String str) {
    	String cur = str;
    	while (!mapp.get(cur).equals(cur)) {
    		cur = mapp.get(cur);
    	}
    	return cur;
    }
    
    public static int make_relation(String str1, String str2) {
    	if (mapp.containsKey(str1)) {
    		str1 = find_parent(str1);
    	} else { mapp.put(str1, str1); map_cnt.put(str1, 1);}
    	if (mapp.containsKey(str2)) {
    		str2 = find_parent(str2);
    	} else { mapp.put(str2, str2); map_cnt.put(str2, 1);}
    	String temp;
    	if (str1.equals(str2)) { return map_cnt.get(str2);}
    	if (str1.compareTo(str2) < 0) {
			temp = str1;
			str1 = str2;
			str2 = temp;
		}
    	mapp.put(str1, str2); map_cnt.put(str2, map_cnt.get(str1) + map_cnt.get(str2));
    	return map_cnt.get(str2);
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
        	F = Integer.parseInt(bf.readLine());
        	mapp.clear(); map_cnt.clear();
        	for (int i = 0; i < F; i++) {
        		st = new StringTokenizer(bf.readLine());
        		String str1 = st.nextToken();
        		String str2 = st.nextToken(); 
        		
//        		System.out.println(str1 +" " +  str2);
        		int ans = make_relation(str1, str2);
        		sb.append(ans).append("\n");
			}
//        	for(String key : mapp.keySet()) {
//                String value = (String) mapp.get(key);
//                int val = (int) map_cnt.get(key);
//                System.out.println(key + " : " + value + " " + val);
//            }
		}
        
        System.out.println(sb);
    }
}