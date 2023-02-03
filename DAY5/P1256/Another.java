package DAY4.P1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Another {
    static final int MAX = 1000000000;
    static int [][]paskal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String words = st.nextToken();

        int N = 0, M = 0;
        for (int i = 0; i < words.length(); i++) {
            if(words.charAt(i) == 'a') {
                N++;
            }
            else {
                M++;
            }
        }
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
        int result = 0;
        int total = paskal[N+M][M];
        int cnt = 0, length = N+M;
        while(cnt < length-1) {
            int num = paskal[N+M-1][M];
//            if(N+M-1 < M)
//                num = 1;
            System.out.println(num);
            if(words.charAt(cnt) == 'a') {
                N--;
            }
            else {
                M--;
                result += num;
            }
            cnt += 1;
        }
        System.out.println(result+1);
    }
}
