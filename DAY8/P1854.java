import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1854 {
    static int n,m,k;
    static List<List<Info>> node = new ArrayList<>();
    static boolean[] visited;
    static List<PriorityQueue<Integer>> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            node.add(new ArrayList<>());
            result.add(new PriorityQueue<>(Collections.reverseOrder()));
        }

        visited = new boolean[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            node.get(a).add(new Info(b,c));
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(1,0));
        visited[1] = true;
        result.get(1).add(0);
        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            for(Info next: node.get(cur.b)) {
                int value = cur.c + next.c;
                if(result.get(next.b).size() < k) {
                    result.get(next.b).add(value);
                    pq.add(new Info(next.b, value));
                }
                else {
                    if(result.get(next.b).peek() > value) {
                        result.get(next.b).poll();
                        result.get(next.b).add(value);
                        pq.add(new Info(next.b, value));
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if(result.get(i).size() < k) {
                sb.append("-1\n");
            }
            else {
                sb.append(result.get(i).poll()).append("\n");
            }
        }
        System.out.print(sb);
    }

    static class Info implements Comparable<Info>{
        int b;
        int c;

        public Info(int b, int c) {
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Info o) {
            return c - o.c;
        }
    }
}
