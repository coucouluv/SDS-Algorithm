import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Me {

    static int N, M;
    static int[][] parent;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] visited;
    static int[] depth;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        parent = new int[18][N+1];
        visited = new int[N+1];
        depth = new int[N+1];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        makeParent(1);
        for (int i = 1; i <= 17; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //깊이 맞추기
            int depthA = depth[a];
            int depthB = depth[b];
            if(depth[a] < depth[b]) {
                depthA = depth[b];
                depthB = depth[a];
                int temp = a;
                a = b;
                b = temp;
            }
            for (int j = 17; j >= 0; j--) {
                if(depthA - (int)Math.pow(2,j) >= depthB) {
                    depthA -= (int)Math.pow(2,j);
                    a = parent[j][a];
                }
                if(depthA == depthB) {
                    break;
                }
            }
            //공통 조상 찾기
            result = a;
            if(a != b) {
                for (int j = 17; j >= 0; j--) {
                    if(parent[j][a] != parent[j][b]) {
                        a = parent[j][a];
                        b = parent[j][b];
                    }
                    result = parent[j][a];
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
    private static void print() {
        for (int i = 1; i <= N; i++) {
            System.out.println("child " + i + ", depth" + depth[i]);
        }
    }
    private static void makeParent(int index) {
        visited[index] = 1;
        for(int a : tree.get(index)) {
            if(visited[a] == 0) {
                parent[0][a] = index;
                depth[a] = depth[index] + 1;
                makeParent(a);
            }
        }
    }

}


