package DAY7.P11266;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Me {
    static int V, E;
    static boolean []visited;
    static int []order;
    static List<List<Integer>> tree = new ArrayList<>();
    static int []isCutPoint;
    static int orderCnt = 1;
    static int cutPointCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        visited = new boolean[V+1];
        order = new int[V+1];
        isCutPoint = new int[V+1];
        for (int i = 1; i <= V; i++) {
            if(!visited[i]) {
                dfs(i, 0);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cutPointCnt).append("\n");
        for (int i = 1; i <= V; i++) {
            if(isCutPoint[i] == 1) {
                sb.append(i).append(" ");
            }
        }
        System.out.print(sb);
    }
    private static int dfs(int index, int root) {
        visited[index] = true;
        order[index] = orderCnt++;
        int childCnt = 0;
        int low = order[index];
        for(int a : tree.get(index)) {
            if(visited[a]) {
                low = Math.min(low, order[a]);
                continue;
            }
            int temp = dfs(a, 1);
            if(root == 1 && temp >= order[index]) {
                if(isCutPoint[index] == 0) cutPointCnt++;
                isCutPoint[index] = 1;
            }
            low = Math.min(low, temp);
            childCnt += 1;
        }
        //루트 노드
        if(root == 0) {
            //차일드 값
            if(childCnt >= 2) {
                if(isCutPoint[index] == 0) cutPointCnt++;
                isCutPoint[index] = 1;
            }
        }
        return low;
    }
}
