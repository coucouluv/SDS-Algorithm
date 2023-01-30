package DAY01.P1759;

import java.util.Arrays;
import java.util.Scanner;

public class Teacher {
    private static int L, C;
    private static char[] input;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        L = in.nextInt();
        C = in.nextInt();
        input = new char[C];
        for(int i = 0; i < C; i++) {
            input[i] = in.next().charAt(0);
        }
        Arrays.sort(input);
        dfs(-1,0,0,0,"");
    }
    static void dfs(int current, int length, int ja, int mo, String pwd) {
        //1. 체크인 - 생략 가능 (중복이 없어서)
        //2. 목적지인가 length == L -> 자, 모음 개수 확인
        if(length == L) {
            if(ja >= 2 && mo >= 1) {
                System.out.println(pwd);
            }
        } else {
            //3. 연결된 곳을 순회 current ~ C까지
            for (int i = current+1; i < C; i++) {
                //4. 갈 수 있는가? - 생략 가능
                //5. 간다 - 자, 모 세팅
                if(input[i] == 'a' || input[i] == 'e' || input[i] == 'i' ||
                        input[i] == 'o' || input[i] == 'u') {
                    dfs(i, length+1, ja, mo+1, pwd+input[i]);
                }
                else {
                    dfs(i, length+1, ja+1, mo, pwd+input[i]);
                }
            }
        }
        //6. 체크아웃 - 생략가능
    }
}
