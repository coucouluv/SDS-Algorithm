import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    static Map<String, Integer> trees = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        String word = br.readLine();
        int cnt = 0;
        while(true) {
            if(trees.get(word) == null) {
                trees.put(word, 1);
            }
            else {
                trees.put(word, trees.get(word) + 1);
            }
            cnt += 1;
            word = br.readLine();
            if(word == null) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        Set<String> keySet = trees.keySet();
        for(String cur : keySet) {
            double result = (double)(trees.get(cur) * 100.0) / cnt;
            sb.append(cur).append(" ").append(String.format("%.4f", result)).append("\n");
        }
        System.out.print(sb);
    }
}


