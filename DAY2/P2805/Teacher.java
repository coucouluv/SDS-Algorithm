package DAY2.P2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teacher {
    private static int N;
    private static int []trees;
    private static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        trees = new int[N];

        int max = 0;
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(trees[i], max);
        }

        int s= 0, e = max, mid = 0, result = 0;
        while(true) {
            mid = (s+e)/2;
            long sum = calc(mid);
            if(sum == M) {
                result = mid;
                break;
            }
            else if(sum < M) {
                e = mid - 1;
            }
            else {
                result = mid;
                s = mid + 1;
            }
            if(s>e) {
                break;
            }
        }
        System.out.println(result);
    }
    static long calc(int value) {
        long result = 0;
        for(int tree: trees) {
            if(tree > value) {
                result += tree - value;
            }
        }
        return result;
    }
}
