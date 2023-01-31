package DAY2.P2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Me {
    private static int T;
    private static int n, m;
    private static List<Integer> Alist = new ArrayList<>();
    private static List<Integer> Blist = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        int []A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        int []B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        //부배열의 합 배열 만들기
        makeA(A);
        makeB(B);

        //정렬
        Collections.sort(Alist);
        Collections.sort(Blist, Collections.reverseOrder());

        int startA = 0;
        int startB = 0;
        long result = 0;

        while(startA < Alist.size() && startB < Blist.size()) {
            long sum = Alist.get(startA) + Blist.get(startB);
            if(sum < T) {
                startA++;
            }
            else if(sum == T) {
                long aNum =  Alist.get(startA);
                long bNum = Blist.get(startB);
                long cntA = 0, cntB = 0;
                while (startA < Alist.size() && Alist.get(startA) == aNum) {
                    cntA++;
                    startA++;
                }
                while (startB < Blist.size() && Blist.get(startB) == bNum) {
                    cntB++;
                    startB++;
                }
                result += cntA * cntB;
            }
            else {
                startB++;
            }
        }
        System.out.println(result);
    }
    static void makeA(int[] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            int num = 0;
            for(int j = i; j < tmp.length; j++) {
                num += tmp[j];
                Alist.add(num);
            }
        }
    }
    static void makeB(int[] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            int num = 0;
            for(int j = i; j < tmp.length; j++) {
                num += tmp[j];
                Blist.add(num);
            }
        }
    }
}
