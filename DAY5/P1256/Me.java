package DAY4.P1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    static final int MAX = 1000000000;
    static int [][]paskal;
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        paskal = new int [N+M+1][N+M+1];


        for (int i = 0; i < N + M + 1; i++) {
            for (int j = 0; j <= i; j++) {
                if(i == 0 || j == 0 || i == j) {
                    paskal[i][j] = 1;
                }
                else {
                    paskal[i][j] = paskal[i-1][j] + paskal[i-1][j-1];
                    if(paskal[i][j] > MAX) {
                        paskal[i][j] = MAX;
                    }
                }
            }
        }
        //K번째 문자열 찾기
        if(K > paskal[N+M][M]) {
            System.out.println("-1");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int cnt = 0, length = N+M;
        while(cnt < length) {
            int num = paskal[N+M-1][M];
            if(num >= K) {
                sb.append("a");
                N--;
            }
            else {
                K -= num;
                sb.append("z");
                M--;
            }
            cnt += 1;
        }
        System.out.println(sb);
    }
    static void print() {
        for (int i = 0; i < N + M + 1; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.println(i + ", " + j + " : " + paskal[i][j]);
            }
        }
        System.out.println();
    }
}
