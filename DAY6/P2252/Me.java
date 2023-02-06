package DAY6.P2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Me {
    static int N, M;
    static List<List<Integer>> people = new ArrayList<>();
    static int[] line;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        line = new int[N+1];
        for (int i = 0; i <= N; i++) {
            people.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            people.get(a).add(b);
            line[b] += 1;
        }
        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if(line[i] == 0) {
                que.add(i);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();
            sb.append(cur).append(" ");
            for(int a :people.get(cur)) {
                line[a] -= 1;
                if(line[a] == 0) {
                    que.add(a);
                }
            }
        }
        System.out.println(sb);

    }
}
