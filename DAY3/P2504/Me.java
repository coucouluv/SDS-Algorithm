import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static String word;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        word = st.nextToken();
        Stack<Character> stack = new Stack<>();
        int num = 1;
        int result = 0;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            if(cur == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }
                stack.pop();
                if(word.charAt(i-1) == '(') {
                    result += num;
                }
                num /= 2;
            }
            else if(cur == ']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }
                stack.pop();
                if(word.charAt(i-1) == '[') {
                    result += num;
                }
                num /= 3;
            }
            else if(cur == '('){
                num *= 2;
                stack.push(cur);
            }
            else {
                num *= 3;
                stack.push(cur);
            }
        }
        if(!stack.isEmpty()) {
            System.out.println(0);
        }
        else {
            System.out.println(result);
        }
    }


}


