package DAY01.P3055;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Teacher {
    private static int R,C;
    static char [][]map;
    static int [][]dp;
    static Queue<Point> queue;
    static final  int[] NX = {-1,1,0,0};
    static final  int[] NY = {0,0,-1,1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();

        Point st = null;
        for(int r = 0; r < R; r++) {
            String line = sc.next();
            for(int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if(map[r][c] == '*') {
                    queue.add(new Point(r,c,'*'));
                }
                else if(map[r][c] == 'S') {
                    st = new Point(r,c,'S');
                }
            }
        }
        queue.add(st);
        boolean foundAnswer = false;
        while(!queue.isEmpty()) {
            //1. 큐에서 꺼내옴
            Point p = queue.poll();
            //2. 목적지인가? -> 고슴도치만 D에 도착
            if(p.type == 'D') {
                System.out.println(dp[p.x][p.y]);
                foundAnswer = true;
                break;
            }
            //3. 연결된 곳을 순회 ->좌우위아래
            for (int i = 0; i < 4; i++) {
                int ty = p.y + NY[i];
                int tx = p.x + NX[i];
                //4. 갈 수 있는가? (공통) -> 앱 안벗어나고
                if(0 <= ty && ty < R && 0 <= tx && tx < C) {
                    //4. 갈 수 있는가(고슴도치) -> 0, D
                    if(p.type == '.' || p.type == 'S') {
                        if((map[ty][tx] == '.' || map[ty][tx] == 'D') && dp[ty][tx] == 0) {
                            //5. 체크인(고슴도치) -> dp
                            dp[ty][tx] = dp[p.y][p.x] + 1;
                            //6. 큐에 넣음
                            queue.add(new Point(ty,tx,map[ty][tx]));
                        }
                    }
                    else if(p.type == '*') {
                        //4. 갈 수 있는가(물) -> 0, S
                        if(map[ty][tx] == '.' || map[ty][tx] == 'S') {
                            //5. 체크인(물) -> map
                            map[ty][tx] = '*';
                            //6. 큐에 넣음
                            queue.add(new Point(ty,tx,'*'));
                        }
                    }
                }
            }

        }
        if(!foundAnswer)
            System.out.println("KAKTUS");
    }

}
class Point {
    int y;
    int x;
    char type;{}
    Point(int y, int x, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
