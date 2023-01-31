package DAY2.P2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    private static int N;
    private static int []inputs;
    private static long M;
    private static int result = 0;
    private static final long MAX = 1000000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        inputs = new int[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        long search = binarySearch(0, MAX);
        System.out.println(search);
    }
    public static long binarySearch(long low, long high) {
        while(low+1 < high) {
            long mid = (low+high)/2;
            if(calculate(mid) >= M) {
                low = mid;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
    public static long calculate(long mid) {
        long ret = 0;
        for (int i = 0; i < N; i++) {
            if(inputs[i] > mid) {
                ret += inputs[i] - mid;
            }
        }
        return ret;
    }

}
