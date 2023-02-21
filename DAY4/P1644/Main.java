import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N, cnt = 0;
    static List<Integer> numbers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        boolean[] tmp = new boolean[N+1];
        for (int i = 2; i <= N; i++) {
            if(!tmp[i]) {
                numbers.add(i);
            }
            for (int j = i; j <= N ; j += i) {
                tmp[j] = true;
            }
        }
        int start = 0, end = 0;
        int sum = 0;
//        numbers.add(0);
        while(true) {
//            System.out.println("s: "+ start + ", e: " + end + " = " + sum);
            if(sum < N) {
                if(end >= numbers.size()) {
                    break;
                }
                sum += numbers.get(end);
                end += 1;
            }
            else {
                if(sum == N) {
                    cnt += 1;
                }
                if(start >= numbers.size()) {
                    break;
                }
                sum -= numbers.get(start);
                start += 1;
            }
        }
        System.out.print(cnt);
    }

}


