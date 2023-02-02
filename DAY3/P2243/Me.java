package DAY3.P2243;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    private static int n;
    private static final int MAX = 1000000;
    private static int[] tree;
    private static int S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        S = 1;
        while(S < MAX) {
            S *= 2;
        }

        tree = new int[S*2];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());

            if(A == 2) {
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                putCandy(B,C);

            }
            else {
                int B = Integer.parseInt(st.nextToken());
                int pick = pickUp(1, S, 1,  B);
                sb.append(pick).append("\n");
                putCandy(pick, -1);

            }
        }
        System.out.print(sb);
    }
    public static void putCandy(int node, int flavor) {
        node = node + S -1;
        while(node > 0) {
            tree[node] += flavor;
//            System.out.println("node : " + node + " value = "+ tree[node]);
            node /= 2;
        }
    }
    public static int pickUp(int left, int right, int node, int rank) {
        //1. 포함하지 않음
//        System.out.println("node " + node +", num : "+left + " - " + right);
//        System.out.println("rank: "+ leftRank + " - " + rightRank);

        if(left == right) {
            return left;
        }
        else {
            int mid = (left + right) / 2;
            if(tree[node*2] >= rank) {
                return pickUp(left, mid, node*2, rank);
            }
            else {
                //오른쪽갈때 왼쪽 순위 빼야 함
                return pickUp(mid+1,right,node*2+1, rank-tree[node*2]);
            }
        }

    }
}
