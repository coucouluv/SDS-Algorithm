package DAY4.P1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    static long []paskal;
    static int[] number;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        paskal = new long[N+1];
        number = new int[N+1];
        visited = new boolean[N+1];
        paskal[0] = 1;
        for (int i = 1; i <= N; i++) {
            paskal[i] = i * paskal[i-1];
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        if(a == 1) {
            long cnt = Long.parseLong(st.nextToken());
            //함수
            findNumber(cnt);
            for(int i = 0; i < N; i++) {
                System.out.print(number[i] + " ");
            }
        }
        else {
            for (int i = 0; i < N; i++) {
                number[i] = Integer.parseInt(st.nextToken());
            }
            //함수
            System.out.println(findIndex());
        }
    }
    private static long findIndex() {
        int index = 0;
        long result = 0;
        while(index < N) {
            long num = 0;
            for(int j = 1; j <= N; j++) {
                num = paskal[N-index-1];
                if(number[index] == j) {
                    visited[j] = true;
                    break;
                }
                else if(visited[j] == false) {
                    result += num;
                }
            }
            index++;
        }
        return result+1;
    }
    private static void findNumber(long cnt) {
        int index = 0;
        while(index < N) {
            long num = 0;
            for(int j = 1; j <= N; j++) {
                if(!visited[j]) {
                    num = paskal[N-index-1];
                    if(num >= cnt) {
                        number[index] = j;
                        visited[j] = true;
                        break;
                    }
                    else {
                        cnt -= num;
                    }
                }
            }
            index++;
        }
    }
}
