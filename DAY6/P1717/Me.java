package DAY6.P1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    static int N, M;
    static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        number = new int[N+1];
        for (int i = 0; i <= N; i++) {
            number[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1) {
                int result1 = find(b);
                int result2 = find(c);
                if(result1 == result2) {
                    sb.append("YES\n");
                }
                else {
                    sb.append("NO\n");
                }
            }
            else {
                union(b,c);
            }
//            print();
        }
        System.out.print(sb);
    }
    public static void print() {
        for (int i = 0; i <= N; i++) {
            System.out.println(i + ": " + number[i]);
        }
    }
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) {
            return;
        }
        number[a] = b;

    }
    public static int find(int index){
        if(number[index] == index) {
            return index;
        }
        return number[index] = find(number[index]);
    }
}
