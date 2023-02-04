package P2517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Me {
    static int N, S;
    static List<Person> people = new ArrayList<>();
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = 1;
        while(S < N) {
            S *= 2;
        }
        tree = new int[S*2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            people.add(new Person(i, tmp));
        }
        Collections.sort(people);

        StringBuilder sb = new StringBuilder();
        int []result = new int[N+1];
        for(Person person: people) {
            //나보다 앞에 있는 사람 수 세기
            int tmp = countPeople(1, S, 1, 1, person.number - 1);
            tmp = person.number - tmp;
            result[person.number] = tmp;
            //나 넣기
            insert(person.number);
        }
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    public static void insert(int node) {
        node = node + S -1;
        while(node > 0) {
            tree[node] += 1;
            node /= 2;
        }
    }

    public static int countPeople(int left, int right, int node, int leftNum, int rightNum) {
        //대상 아님
        if(rightNum < left || leftNum > right) {
            return 0;
        }
        else {
            if(left >= leftNum && right <= rightNum) {
                return tree[node];
            }
            else {
                int mid = (left + right)/2;
                int leftResult = countPeople(left, mid, node*2, leftNum, rightNum);
                int rightResult = countPeople(mid+1, right, node*2+1, leftNum, rightNum);
                return leftResult + rightResult;
            }
        }
    }
    public static class Person implements Comparable<Person> {
        int number;
        int ability;

        public Person(int number, int ability) {
            this.number = number;
            this.ability = ability;
        }
        @Override
        public int compareTo(Person o) {
            return ability-o.ability;
        }
    }
}
