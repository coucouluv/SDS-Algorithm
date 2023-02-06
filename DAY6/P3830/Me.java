package DAY6.P3830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {

    static int N, M;
    static int[] node;
    static long[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while(true) {
            if(N == 0 && M == 0) {
                System.out.print(sb);
                return;
            }
            node = new int[N+1];
            cost = new long[N+1];
            for (int i = 1; i <= N; i++) {
                node[i] = i;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if(cmd.equals("!")) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    long w = Integer.parseInt(st.nextToken());
                    merge(a, b, w);
                } else {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    if (find(a) != find(b)) {
                        sb.append("UNKNOWN").append("\n");
                    } else {
                        sb.append(cost[b] - cost[a]).append("\n");
                    }
                }
            }
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }
    }
    private static int find(int index) {
        if(node[index] == index) {
            return index;
        }
        int dist = find(node[index]);
        cost[index] +=  cost[node[index]];
        return node[index] = dist;
    }
    private static void merge(int a, int b, long w) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) {
            return;
        }
        cost[bRoot] = cost[a] + w - cost[b];
        node[bRoot] = aRoot;

    }

}
