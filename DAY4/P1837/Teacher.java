package DAY4.P1837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Teacher {
    static char[] P;
    static boolean[] check;
    static final String OK = "GOOD";
    static final String NOTOK = "BAD";
    static List<Integer> primes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken().toCharArray();
        int K = Integer.parseInt(st.nextToken());
        check= new boolean[K+1];
        for(int i = 2; i < K; i++) {
            if(check[i] == false) {
                primes.add(i);
                for (int j = i*2; j < K; j += i) {
                    check[j] = true;
                }
            }
        }
        for(int num: primes) {
            if(checkIsBad(num)) {
                System.out.println(NOTOK + " " + num);
                return;
            }
        }
        System.out.println(OK);
    }
    static boolean checkIsBad(int x) {
        int r = 0;
        for (int i = 0; i < P.length; i++) {
            r = (r*10 + (P[i] -'0')) % x;
        }
        if(r == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
