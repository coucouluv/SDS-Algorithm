package DAY01.P1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Me {
    private static int[] visited = new int[27];
    private static int N;
    private static int K;
    private static String[] words;
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words= new String[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            tmp = tmp.substring(4, tmp.length()-4);
            char[] charArr = tmp.toCharArray();
            Arrays.sort(charArr);
            words[i] = new String(charArr);
        }
        if(K < 5) {
            System.out.println(0);
            return;
        }
        //처음 세팅
        char []setting = {'a', 'c', 'i', 'n', 't'};
        for(int i = 0; i < setting.length; i++) {
            visited[setting[i] - 'a'] = 1;
        }
        dfs(0, 1);
        System.out.println(result);
    }
    public static int calculate() {
        int ret = 0;
        boolean possible;
        for(int i = 0; i < words.length; i++) {
            possible = true;
            for(int j = 0; j < words[i].length(); j++) {
                if (visited[words[i].charAt(j) - 'a'] == 0) {
                    possible = false;
                    break;
                }
            }
            if(possible) {
                ret += 1;
            }
        }
        return ret;
    }
    public static void dfs(int num, int index) {
        if(num == (K-5)) {
            int calculate = calculate();
            result = Math.max(calculate, result);
            return;
        }
        for(int i = index; i < 26; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                dfs(num+1, i);
                visited[i] = 0;
            }
        }
    }
}
