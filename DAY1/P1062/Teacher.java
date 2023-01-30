package DAY01.P1062;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Teacher {

    private static int N, K;
    static boolean[] visited;
    static String[] words;
    static int max = 0;
    static int selectedCount;
    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("src/DAY01/P1062/input.txt"));
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = in.next().replaceAll("[antic]", "");
        }

        selectedCount = 5;
        if(K < 5) {
            max = 0;
        }
        else if(K == 5) {
            max = Math.max(countReadable(), max);
        }
        else {
            for (int i = 0; i < 26; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }
        }
        System.out.println(max);
    }
    static void dfs(int index) {
        //1. 체크인 -> visited, selectedCount
        visited[index] = true;
        selectedCount++;

        //2. 목적지인가 -> selectedCount == K -> 읽을 수 있는 단어 계산
        if(selectedCount == K) {
            max = Math.max(countReadable(), max);
        } else {
            //3. 연결된 곳을 순회 index + 1 ~ z
            for (int i = index+1; i < 26; i++) {
                //4. 갈 수 있는가? visited
                if(!visited[i]) {
                    //5. 간다 dfs
                    dfs(i);
                }
            }
        }
        //6. 체크아웃 -> visited, selectedCount
        visited[index] = false;
        selectedCount--;
    }

    static int countReadable() {
        int count = 0;
        for(int i = 0; i < N; i++) {
            boolean isReadable = true;
            String word = words[i];
            for(int j = 0; j < word.length(); j++) {
                if(!visited[word.charAt(j) - 'a']) {
                    isReadable = false;
                    break;
                }
            }
            if(isReadable) {
                count += 1;
            }
        }
        return count;
    }
}
