package DAY4.P3955;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    static long K, C;
    static final long MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            K = Long.parseLong(st.nextToken());
            C = Long.parseLong(st.nextToken());
            long num = gcd(K,C);
            if(K == 1 && C == 1) {
                System.out.println(2);
            }
            else if(K == 1) {
                System.out.println(1);
            }
            else if(C == 1) {
                if(K+1 <= MAX) {
                    System.out.println(K + 1);
                }
                else {
                    System.out.println("IMPOSSIBLE");
                }
            }
            else if(1 % num != 0) {
                System.out.println("IMPOSSIBLE");
            }
            else {
                long result  = calculate(K,C, num);
                if(result <= MAX) {
                    System.out.println(result);
                }
                else {
                    System.out.println("IMPOSSIBLE");
                }
            }
        }
    }
    static long calculate(long K, long C, long num) {
        long cs = 1, ct = 0;
        long ns = 0, nt = 1;
        long a = K, b = C;
        long r = 0, q = 0;
        while(true) {
            r = a % b;
            q = a / b;
//            System.out.println("cs :"+ cs + " ct : "+ ct + " a : " + a);
//            System.out.println("ns :"+ ns + " nt : "+ nt + " n : " + b);
//            System.out.println("------ r : "+ r + " q : " + q );
            long ts = cs - ns*q;
            long tt = ct - nt*q;
            a = b;
            b = r;
            cs = ns;
            ct = nt;
            ns = ts;
            nt = tt;
            if(r <= 1)
                break;
        }
//        System.out.println(ns + ", " + nt);
        long diff = 0;

        diff = K/num;
//        System.out.println("diff : " +diff );
//        long index = 1;
        while(nt <= 0) {
            nt += diff;
        }
        return nt;
    }
    static long gcd(long a, long b) {
        //gcd(a,b) == gcd(b, a%b) a%b가 0일때 b가 답
        while(b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
