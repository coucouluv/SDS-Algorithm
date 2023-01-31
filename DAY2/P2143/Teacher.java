package DAY2.P2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Teacher {
    private static int T;
    private static int n, m;
    private static long[] A,B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        A = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        B = new long[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        List<Long> subA = new ArrayList<>();
        List<Long> subB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long sum = A[i];
            subA.add(sum);
            for (int j = i+1; j < n; j++) {
                sum += A[i];
                subA.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            long sum = B[i];
            subB.add(sum);
            for (int j = i+1; j < m; j++) {
                sum += B[i];
                subB.add(sum);
            }
        }
        Collections.sort(subA);
        Collections.sort(subB, Comparator.naturalOrder());

        long result = 0;
        int ptA = 0, ptB = 0;
        while(true) {
            long currentA = subA.get(ptA);
            long target = T - currentA;
            if(subB.get(ptB) > target) {
                ptB++;
            }
            else if(subB.get(ptB) < target) {
                ptA++;
            }
            else {
                //카운트 세기
                long countA = 0;
                long countB = 0;
                while(ptA < subA.size() && subA.get(ptA) == currentA) {
                    ptA++;
                    countA++;
                }
                while(ptB < subB.size() && subB.get(ptB) == countB) {
                    ptB++;
                    countB++;
                }
                result += countA * countB;
            }
            if(ptA == subA.size() || ptB == subB.size()) {
                break;
            }
        }
        System.out.println(result);
    }
}
