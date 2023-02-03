package DAY4.P1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teacher {
    static int N;
    static boolean[] visited;
    static long []fact;
    static int []nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        fact = new long[N+1];
        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = i * fact[i-1];
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        if(a == 1) {
            long target = Long.parseLong(st.nextToken());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(visited[j] == true) {
                        continue;
                    }
                    //타켓이 경계보다 클 경우
                    if(target > fact[N-i-1]) {
                        target -= fact[N-i-1];
                    }
                    else {
                        sb.append(j).append(" ");
                        visited[j] = true;
                        break;
                    }
                    //타겟이 경계 이하일 경우
                }
            }
            System.out.println(sb);
        }
        else {
            nums = new int[N];
            for(int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            long result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < nums[i]; j++) {
                    if(visited[j] ==false) {
                        result += fact[N-i-1];
                    }
                }
                visited[nums[i]] = true;
            }
            System.out.println(result+1);
        }
    }
}
