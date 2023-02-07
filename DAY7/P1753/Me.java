package DAY7.P1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Me {
    static int V, E;
    static List<List<Pair>> tree = new ArrayList<>();
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree.get(u).add(new Pair(v,w));
//            tree.get(v).add(new Pair(u,w));
        }

        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Pair> que = new PriorityQueue<>();
        que.add(new Pair(start, 0));

        while(!que.isEmpty()) {
            Pair cur = que.poll();
            for(Pair next :tree.get(cur.v)) {
                if(dist[next.v] > dist[cur.v] + next.w) {
                    dist[next.v] = dist[cur.v] + next.w;
                    que.add(new Pair(next.v, dist[next.v]));
                }
            }
        }
        //최단 경로값 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            }
            else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.print(sb);

    }
    public static class Pair implements Comparable<Pair>{
        int v;
        int w;

        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o) {
            return w - o.w;
        }
    }

}
