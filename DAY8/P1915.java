import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;


public class Main {

    static int n, m;
    static int [][] space;
    static int [][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        space = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            for (int j = 0; j < m; j++) {
                space[i][j] = tmp.charAt(j) - '0';
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            dp[i][0] = space[i][0];
            result = Math.max(result, dp[i][0]);
        }

        for (int j = 0; j < m; j++) {
            dp[0][j] = space[0][j];
            result = Math.max(result, dp[0][j]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if(space[i][j] > 0) {
                    int minNum = 0;
                    minNum = Math.min(dp[i][j-1], dp[i-1][j]);
                    minNum = Math.min(minNum, dp[i-1][j-1]);
                    dp[i][j] = minNum + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        System.out.print(result*result);

    }
}




