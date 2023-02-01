package DAY3.P11279;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Me {
    private static int[] tree = new int[300000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num == 0) {
                sb.append(tree[1]).append("\n");
                if(cnt != 1) {
                    tree[1] = tree[cnt - 1];
                    tree[cnt - 1] = 0;
                    deleteTree(tree[1], 1);
                    cnt -= 1;
                }
            }
            else {
                tree[cnt] = num;
                makeTree(num,cnt);
                cnt += 1;
            }

        }
        System.out.println(sb);
    }

    public static void makeTree(int num, int index) {
        if(index != 1 && num > tree[index/2]) {
            int a = num;
            tree[index] = tree[index/2];
            tree[index/2] = a;
            makeTree(tree[index/2], index/2);
        }
    }
    public static void deleteTree(int num, int index) {
        int change = 0;
        if(tree[index*2] < tree[index*2+1]) {
            change = index*2+1;
        }
        else {
            change = index*2;
        }
        if(tree[index] < tree[change]) {
            tree[index] = tree[change];
            tree[change] = num;
            deleteTree(num, change);
        }
    }

}
