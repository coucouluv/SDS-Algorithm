package DAY4.P14476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    static int []left;
    static int []right;
    static int maxGcd = 0;
    static int number = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        left = new int[N];
        right = new int[N];
        st = new StringTokenizer(br.readLine());
        int []numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            if(i == 0) {
                left[i] = numbers[i];
            }
            else {
                if(left[i-1] > numbers[i]) {
                    left[i] = gcd(left[i - 1], numbers[i]);
                }
                else {
                    left[i] = gcd(numbers[i], left[i-1]);
                }
            }
        }
        right[N-1] =numbers[N-1];
        for (int i = N-2; i >= 0; i--) {
            if(right[i+1] > numbers[i]) {
                right[i] = gcd(right[i+1], numbers[i]);
            }
            else {
                right[i] = gcd(numbers[i], right[i+1]);
            }
        }
        int calNum = 0;
        for (int i = 0; i < N; i++) {
            int minus = numbers[i];
            if(i == 0) {
                calNum = right[1];
            }
            else if(i == N-1) {
                calNum = left[N-1];
            }
            else {
                if(left[i-1] > right[i+1]) {
                    calNum = gcd(left[i - 1], right[i + 1]);
                }
                else {
                    calNum = gcd(right[i + 1], left[i-1]);
                }
            }
            if(minus % calNum != 0 && maxGcd < calNum) {
                maxGcd = calNum;
                number = minus;
            }
        }
        if(maxGcd == 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(maxGcd + " "+ number);
        }
    }
    static int gcd(int a, int b) {
        //gcd(a,b) == gcd(b, a%b) a%b가 0일때 b가 답
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
