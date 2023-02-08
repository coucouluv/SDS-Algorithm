import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1516 {

    static int N, M;
    static List<List<Integer>> people = new ArrayList<>();
    static int[] inorder;
    static int[] result;
    static int[] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            people.add(new ArrayList<>());
        }
        inorder = new int[N+1];
        result = new int[N+1];
        time = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            time[i] = a;
            while(true) {
                int b = Integer.parseInt(st.nextToken());
                if(b == -1)
                    break;
                people.get(b).add(i);
                inorder[i] += 1;
            }
        }
        Queue<Integer> pq = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if(inorder[i] == 0) {
                pq.add(i);
                result[i] = time[i];
            }
        }
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            for(int a :people.get(cur)) {
                inorder[a] -= 1;
                if(inorder[a] == 0) {
                    pq.add(a);
//                    System.out.println("a : " + a + " size : " + size);
                }
                result[a] = Math.max(result[cur]+time[a], result[a]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

}
