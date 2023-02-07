package DAY7.P11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    static int n, m;
    static int [][]node;
    static final int MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        node = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i != j) {
                    node[i][j] = MAX;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            node[a][b] = Math.min(node[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int itok = node[i][k];
                    int ktoj = node[k][j];
                    if(node[i][j] > itok+ktoj) {
                        node[i][j] = itok+ktoj;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(node[i][j] == MAX) {
                    sb.append(0).append(" ");
                }
                else {
                    sb.append(node[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
