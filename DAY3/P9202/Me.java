package DAY3.P9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Me {
    static int []dx = {0,0,-1,1,-1,-1,1,1};
    static int []dy = {-1,1,0,0,-1,1,-1,1};

    static char[][] board = new char[4][4];
    static boolean[][] visited = new boolean[4][4];
    static int resultScore = 0;
    static String resultWord = "";
    static int resultCnt = 0;
    static int [] scores = {0,0,0,1,1,2,3,5,11};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());

        Tree tree = new Tree();
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            makeTree(tmp, tree);
//            print(tree, 0);
        }
        st = new StringTokenizer(br.readLine());

        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                String line = st.nextToken();
                for (int k = 0; k < 4; k++) {
                    board[j][k] = line.charAt(k);
                    visited[j][k] = false;
                }
            }
            //초기화
            resultScore = 0;
            resultWord = "";
            resultCnt = 0;
            //tree hit 초기화
            setHit(tree);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    char cur = board[j][k];
                    for(Tree child: tree.children) {
                        if(cur == child.data) {
                            visited[j][k] = true;
                            dfs(j,k,1,child,cur+"");
                            visited[j][k] = false;
                        }
                    }
                }
            }
            sb.append(resultScore).append(" ").append(resultWord)
                    .append(" ").append(resultCnt).append("\n");
            if(i != b-1) {
                st = new StringTokenizer(br.readLine());
            }
        }
        System.out.print(sb);

    }

    public static void dfs(int x, int y, int depth, Tree cur, String words) {
//        System.out.println(x + ", " + y + " : depth " + depth + " word " + words);
        if(cur.isEnd == true) {
            if(cur.isHit == false) {
                resultScore += scores[depth];
                resultCnt += 1;
                cur.isHit = true;
                if(resultWord.length() < depth) {
                    resultWord = words;
                }
                else if(resultWord.length() == depth) {
                    if(resultWord.compareTo(words) > 0){
                        resultWord = words;
                    }
                }
            }
        }
        if(depth > 8)
            return;
        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && visited[nx][ny] == false) {
                char next = board[nx][ny];
                for(Tree child: cur.children) {
                    if(child.data == next) {
                        visited[nx][ny] = true;
                        dfs(nx, ny, depth+1 ,child, words+next);
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }
    public static void setHit(Tree parent) {

        if(parent.isEnd == true) {
            parent.isHit = false;
        }
        for(Tree child: parent.children) {
            setHit(child);
        }
    }
    public static void makeTree(String tmp, Tree tree) {

        Tree curTree = tree;
        int index = 0;
        while(index < tmp.length()) {
            char cur = tmp.charAt(index);
            boolean isHere = false;
            for(Tree child: curTree.children) {
                if(child.data == cur) {
                    curTree = child;
                    isHere = true;
                    break;
                }
            }
            if(!isHere) {
                Tree newTree = new Tree(cur,false, false);
                curTree.children.add(newTree);
                curTree = newTree;
            }
            index += 1;
        }
        curTree.isEnd = true;
    }

    public static class Tree{
        List<Tree> children;
        boolean isEnd;
        boolean isHit;
        char data;
        Tree() {
            children = new ArrayList<>();
            data = ' ';
        }
        Tree(char data, boolean isEnd, boolean isHit) {
            children = new ArrayList<>();
            this.data = data;
            this.isEnd = isEnd;
            this.isHit = isHit;
        }
    }
}
