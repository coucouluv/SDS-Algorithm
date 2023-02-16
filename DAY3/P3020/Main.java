import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N, H;
    static int[] up;
    static int[] down;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[H+2];
        down = new int[H+2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            if(i % 2 == 0) {
                down[tmp] += 1;
            }
            else {
                up[H-tmp+1] += 1;
            }
        }
        for (int i = 1; i <= H; i++) {
            down[i] += down[i-1];
        }
        for (int i = H; i > 0; i--) {
            up[i] += up[i+1];
        }

        int resultMin = N;
        int resultCnt = 0;
        for (int i = 1; i <= H; i++) {
            int tmp = (down[H] - down[i-1]) + (up[1] - up[i+1]);
            if(tmp < resultMin) {
                resultMin = tmp;
                resultCnt = 1;
            }
            else if(tmp == resultMin) {
                resultCnt += 1;
            }
        }
        System.out.println(resultMin + " " + resultCnt);
    }
}


