import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N, M;
    static boolean []computers;
    static List<List<Pair>> connect = new ArrayList<>();
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        computers = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            connect.add(new ArrayList<>());
        }
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            connect.get(a).add(new Pair(b,c));
            connect.get(b).add(new Pair(a,c));
        }

        for(Pair next: connect.get(1)) {
            queue.add(next);
        }

        computers[1] = true;
        while(!queue.isEmpty()) {
            Pair cur = queue.poll();
            if(!computers[cur.b]) {
//                System.out.println("b : "+ cur.b + " c : " + cur.c);
                computers[cur.b] = true;
                result += cur.c;
                for(Pair next :connect.get(cur.b)) {
                    if(!computers[next.b]) {
                        queue.add(next);
                    }
                }
            }
        }
        System.out.print(result);
    }
}

class Pair implements Comparable<Pair> {
    int b;
    int c;
    public Pair(int b, int c) {
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(Pair o) {
        return c - o.c;
    }
}


