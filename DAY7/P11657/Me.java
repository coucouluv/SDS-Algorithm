package DAY7.P11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Me {
    static int V, E;
    static List<Node> bus = new ArrayList<>();
    static long[]dist;
    static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            bus.add(new Node(a,b,c));
        }
        dist = new long[V+1];
        Arrays.fill(dist, MAX);
        dist[1] = 0;
        boolean isCircle = false;
        for (int i = 0; i <= V; i++) {
            for(Node cur: bus) {
                if(dist[cur.a] != MAX && dist[cur.b] > dist[cur.a] + cur.c) {
                    if(i == V) {
                        isCircle = true;
                    }
                    dist[cur.b] = dist[cur.a] + cur.c;
                }
            }
        }
        if(isCircle) {
            System.out.println("-1");
        }
        else {
            for (int i = 2; i <= V; i++) {
                if(dist[i] == MAX) {
                    System.out.println("-1");
                }
                else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    static class Node {
        int a;
        int b;
        long c;

        public Node(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
