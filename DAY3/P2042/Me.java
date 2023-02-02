package DAY3.P2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    private static int N, M, K;
    private static long[] tree;
    private static int S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while(S < N) {
            S *= 2;
        }
        tree = new long[S*2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i+S] = Long.parseLong(st.nextToken());

        }
        //init 작업
        init();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1) {
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                update(b,c);
            }
            else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long query = query(b, c);
                sb.append(query).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void init() {
        for(int i = S -1; i > 0; i--) {
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }
    public static void update(int node, long c) {
        int targetNode = node + S - 1;
        long diff = c - tree[targetNode];
        while(targetNode > 0) {
            tree[targetNode] += diff;
            targetNode /= 2;
        }
    }

    public static long query(int left, int right) {
        int leftNode = S + left - 1;
        int rightNode = S + right - 1;

        long sum = 0;
        while(leftNode < rightNode) {
            if(leftNode % 2 == 1) {
                sum += tree[leftNode++];
            }

            if(rightNode % 2 == 0) {
                sum += tree[rightNode--];
            }
            leftNode /= 2;
            rightNode /= 2;
        }
        if(leftNode == rightNode) {
            sum += tree[leftNode];
        }
        return sum;
    }
}
