package DAY4.P3955;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teacher {
    static long K, C;
    static final long MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        //X : 인당 나눠줄 사탕의 수
        //Y: 사탕 봉지의 수
        //A * X + 1 = B * Y
        //AX + BY = C의 형태로 변환
        //A(-X) + BY = 1의 형태로 변환
        //A(-X)+ BY = 1의 형태로 변환 -> 추후 K를 구할때 X의 볌위가 반전된다.

        //AX + BY = C일때 C % gcd(A, B) == 0 이어야 해를 가질 수 있다. :배주 항등식

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            K = Long.parseLong(st.nextToken());
            C = Long.parseLong(st.nextToken());
            EGResult result = egcd(K, C);
            if(result.r != 1) {
                System.out.println("IMPOSSIBLE");
            }
            else {
                //As + Bt = r, AX + BY = C 두 식에서
                //C와 r을 일치시켜서 x0, y0를 구함 -> 초기해
                //x0 = s*C/r
                //y0 = t*C/r
                long x0 = result.s;
                long y0 = result.t;
                //일반해 공식
                //x = x0 + b/gcd*k
                //y = y0 - a/gcd*k

                //x < 0
                //x0 + b*K < 0
                //k < -x0/b

                //0 < y <=1e9
                //0 < y0 - a*k <- 1e9
                //(y0 - 1e9) / a <= k < y0/a
                long kFromY = (long) Math.ceil((double)y0/(double) K - 1);
                long kFromX = (long) Math.ceil((double)-x0/(double) C - 1);
                long k = Math.max(kFromX, kFromY);
                long kLimitFromY = (long) Math.ceil((double)(y0 - 1e9)/(double) K);
                if(kLimitFromY <= k) {
                    System.out.println(y0 - K*k);
                }
                else {
                    System.out.println("IMPOSSIBLE");
                }
            }
        }

    }
    static EGResult egcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;
        while(r1 != 0) {
            long q = r0/r1;

            temp = r0 - q * r1; // r0 % r1
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1;
            t0 = t1;
            t1 = temp;
        }
        return new EGResult(s0, t0, r0);
    }
}

class EGResult {
    long s;
    long t;
    long r;

    public EGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }

    @Override
    public String toString() {
        return "EGResult{" +
                "s=" + s +
                ", t=" + t +
                ", r=" + r +
                '}';
    }
}