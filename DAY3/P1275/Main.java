import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N, Q, S;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        S = 1;
        while(S < N) {
            S *= 2;
        }
        tree = new long[S*2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i + S] = Integer.parseInt(st.nextToken());
        }
        initTree();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long result = 0;
            if(x > y) {
                result = calculate(y, x, 1, 1, S);
            }
            else {
                result = calculate(x, y, 1, 1, S);
            }
            update(a, b);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
    
    public static void print() {
        for (int i = 1; i < S*2; i++) {
            System.out.println(i + ": " + tree[i]);
        }
    }
    public static void update(int a, int b) {
        int node = a + S - 1;
        long diff = tree[node] - b;
        while(node > 0) {
            tree[node] -= diff;
            node /= 2;
        }
    }
    public static void initTree() {
        for (int i = S-1; i > 0; i--) {
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    public static long calculate(int leftNode, int rightNode, int node, int left, int right) {
        if(rightNode < left || leftNode > right) {
            return 0;
        }
        else {
            if(left >= leftNode && rightNode >= right) {
                return tree[node];
            }
            else {
                int mid = (left + right)/2;
                long leftResult = calculate(leftNode, rightNode, node*2, left, mid);
                long rightResult = calculate(leftNode, rightNode, node*2+1, mid+1, right);
                return leftResult + rightResult;
            }
        }
    }
}


