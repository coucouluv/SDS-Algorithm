package DAY2.P2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    private static int N, M;
    private static int []inputs;
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inputs = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = 0;
        int sum = 0;
        while(start < N) {
            if(sum < M && end < N) {
                sum += inputs[end];
                end += 1;
            }
            else if(sum == M) {
                result += 1;
                sum -= inputs[start];
                start += 1;
            }
            else {
                sum -= inputs[start];
                start += 1;
            }
        }
        System.out.println(result);
    }

}
