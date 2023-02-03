package DAY4.P1837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    static int[] numbers;
    static final String OK = "GOOD";
    static final String NOTOK = "BAD";
    static String P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken();
        int K = Integer.parseInt(st.nextToken());
        numbers= new int[K];
        for(int i = 2; i < K; i++) {
            if(numbers[i] == 0) {
//                System.out.println("number : "+i);
                boolean result = calculate(i);
                if(!result) {
                    System.out.println(NOTOK+ " " + i);
                    return;
                }
                else {
                    for (int j = i; j < K; j += i) {
                        numbers[j] = 1;
                    }
                }
            }
        }
        System.out.println(OK);
    }
    private static boolean calculate(int num) {

        int subP = 0, r = 0;
        for(int i = 0; i < P.length(); i++) {
            subP += P.charAt(i) - '0';
            r = subP % num;
//            System.out.println(i + " : " + r + " subP : " + subP);
            subP = r*10;
        }
        if(r == 0) {
            return false;
        }
        else {
            return true;
        }
    }
}
