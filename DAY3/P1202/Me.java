package DAY3.P1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Me {

    private static int[] bags;
    private static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //보석
        List<Diamond> diamonds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            diamonds.add(new Diamond(m, v));
        }

        bags = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(bags);

        Collections.sort(diamonds, (o1, o2) ->  o1.m - o2.m);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int index = 0;
        for (int i = 0; i < K; i++) {
//            System.out.println(i + " : " +bags[i]);
            for(int j = index; j < N; j++) {
                if(bags[i] >= diamonds.get(j).m) {
                    index++;
                    pq.offer(diamonds.get(j).value);
                }
                else {
                    break;
                }
            }
            if(!pq.isEmpty()) {
                result += pq.poll();
            }
        }
        System.out.println(result);
    }
}
class Diamond{
    int m;
    int value;
    Diamond(int m, int value) {
        this.m = m;
        this.value = value;
    }


}