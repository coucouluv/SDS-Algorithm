package DAY01.P1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Me {
    private static int[] visited = new int[27];
    private static int L, C;
    private static List<String> words = new ArrayList<>();
    private static String vowels = "aeiou";
    private static char[] keys;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        keys = new char[C];
        String tmp = "";
        for (int i = 0; i < C; i++) {
            tmp += st.nextToken();
        }
        keys = tmp.toCharArray();
        Arrays.sort(keys);

        dfs(0, 0, 0, 0);
        StringBuilder sb = new StringBuilder();
        for(String a: words) {
            sb.append(a).append("\n");
        }
        System.out.print(sb);
    }
    public static String makeKey() {
        String ret = "";
        for (int i = 0; i < 26; i++) {
            if(visited[i] == 1) {
                ret += (char)(i + 97);
            }
        }
        return ret;
    }
    public static void dfs(int index, int cnt, int consonant, int vowel) {
        if (cnt == L) {
            if (consonant >= 2 && vowel >= 1) {
                words.add(makeKey());
            }
            return;
        }
        for (int i = index; i < C; i++) {
            if (visited[keys[i] - 'a'] == 0) {
                visited[keys[i] - 'a'] = 1;
                if (vowels.contains(String.valueOf(keys[i]))) {
                    dfs(i, cnt + 1, consonant, vowel + 1);
                } else {
                    dfs(i, cnt + 1, consonant + 1, vowel);
                }
                visited[keys[i] - 'a'] = 0;
            }
        }
    }
}
