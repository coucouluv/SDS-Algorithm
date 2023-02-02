package DAY3.P9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teacher {
    static int []dx = {0,0,-1,1,-1,-1,1,1};
    static int []dy = {-1,1,0,0,-1,1,-1,1};
    static int [] scores = {0,0,0,1,1,2,3,5,11};
    static char[][] board = new char[4][4];
    static boolean[][] visited = new boolean[4][4];
    static String answer;
    static int sum;
    static int count;
    static StringBuilder sb = new StringBuilder();
    static TrieNode root = new TrieNode();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            insert(tmp);
        }
    }
    static void search(int y, int x, TrieNode node) {
        //1. 체크인
        visited[y][x] = true;
        sb.append(board[y][x]);

        //2. 목적지 도달하는가?
        if(node.isEnd == true && node.isHit == false) {
            node.isHit = true;
            sum += scores[sb.length()];
            count++;
            String foundAnswer = sb.toString();
            if(compare(answer, foundAnswer) > 0) {
                answer = foundAnswer;
            }
        }
        //3. 연결한 곳을 순회
        for(int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            //4. 가능한가? : 맵 벗어나지 않고 Trie에 단어가 있고 방문한 적 없고
            if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                if(node.hasChild(board[ny][nx]) && visited[ny][nx] == false) {
                    //5. 간다
                    search(ny,nx,node.getChild(board[ny][nx]));
                }
            }
        }
        //6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(sb.length()-1);
    }

    static int compare(String o1, String o2) {
        int result = o2.length() - o1.length();
        if(result == 0) {
            return o1.compareTo(o2);
        }
        return result;
    }
    static void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(current.hasChild(c) == false) {
                current.children[c-'A'] = new TrieNode();
            }
            current = current.getChild(c);
        }
        current.isEnd = true;

    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    boolean isHit;

    boolean hasChild(char c) {
        return children[c - 'A'] != null;
    }
    TrieNode getChild(char c){
        return children[c - 'A'];
    }
    void clearHit() {
        this.isHit = false;
        for (int i = 0; i < children.length; i++) {
            if(children[i] != null) {
                children[i].clearHit();
            }
        }
    }
}
