import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1932 {
    static int n;
    static int[][] square;
    static int[][] dp;
    static List<PriorityQueue<Integer>> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        square = new int[n+1][n+1];
        dp = new int[n+1][n+1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                square[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = square[i][j];
            }
        }

        for(int i = n-1; i > 0; i--) {
//            System.out.println("i : "+i);
            for(int j = 0; j <= i; j++) {
//                System.out.println("j : "+j);
                if (j != i) {
                    dp[i - 1][j] = Math.max(dp[i - 1][j], dp[i][j] + square[i - 1][j]);
//                    System.out.println("dp[i-1][j]: "+ dp[i-1][j]);
                }
                if(j != 0) {
                    dp[i - 1][j-1] = Math.max(dp[i - 1][j-1], dp[i][j] + square[i - 1][j-1]);
//                    System.out.println("dp[i-1][j-1]: "+ dp[i-1][j-1]);
                }
            }
        }
        System.out.println(dp[0][0]);

    }
}
