package DAY01.P3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Me {
    private static int R, C;
    private static char [][]space;
    private static int [][]visited;
    private static final String NOTPOSSIBLE = "KAKTUS";
    private static int []x = {0,0,-1,1};
    private static int []y = {-1,1,0,0};
    private static Queue<Pair> me = new LinkedList<>();
    private static Queue<Pair> water = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        space = new char[R][C];
        visited = new int[R][C];

        Pair end = new Pair(0,0);
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            for(int j = 0; j < C; j++) {
                space[i][j] = tmp.charAt(j);
                if(space[i][j] == 'D') {
                    end.x = i;
                    end.y = j;
                }
                else if(space[i][j] == 'S') {
                    me.add(new Pair(i,j));
                    visited[i][j] = 1;
                    space[i][j] = '.';
                }
                else if(space[i][j] == '*') {
                    water.add(new Pair(i,j));
                }
            }
        }
        int result = bfs(end);
        if(result == -1) {
            System.out.println(NOTPOSSIBLE);
        }
        else {
            System.out.println(result-1);
        }
    }
    public static int bfs(Pair end) {
        while(!me.isEmpty()) {
            //print();
            int waterCnt = water.size();
            //물 이동
            for (int i = 0; i < waterCnt; i++) {
                Pair curWater = water.poll();
                for(int n = 0; n < 4; n++) {
                    int nx = x[n] + curWater.x;
                    int ny = y[n] + curWater.y;
                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
                        continue;
                    }
                    if(space[nx][ny] == '.') {
                        space[nx][ny] = '*';
                        water.add(new Pair(nx, ny));
                    }
                }
            }
            //나 이동
            int meCnt = me.size();
            for (int i = 0; i < meCnt; i++) {
                Pair curMe = me.poll();
                if(curMe.x == end.x && curMe.y == end.y) {
                    return visited[end.x][end.y];
                }
                for(int n = 0; n < 4; n++) {
                    int nx = x[n] + curMe.x;
                    int ny = y[n] + curMe.y;
                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
                        continue;
                    }
                    if((space[nx][ny] == '.'||space[nx][ny] == 'D')&& visited[nx][ny] == 0) {
                        visited[nx][ny] = visited[curMe.x][curMe.y] + 1;
                        me.add(new Pair(nx, ny));
                    }
                }
            }
        }
        return -1;
    }
    public static class Pair {
        int x; int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
