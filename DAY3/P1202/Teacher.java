package DAY3.P1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Teacher {

    static Jewel[] jewels;
    static int[] bags;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //보석
        jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m,v);
        }

        bags = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(bags);
        Arrays.sort(jewels, Comparator.comparingInt(Jewel::getWeight));
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getValue).reversed());
        int index = 0;
        long result = 0;
        for (int i = 0; i < K; i++) {
            int currentBag = bags[i];
            while(index < N && jewels[index].weight <= currentBag) {
                pq.add(jewels[index++]);
            }
            if(!pq.isEmpty()) {
                result += pq.poll().value;
            }
        }
        System.out.println(result);

    }
}
class Jewel {
    int weight;
    int value;
    Jewel(int weight, int value) {
        this.value = value;
        this.weight = weight;
    }
    int getValue() {
        return value;
    }
    int getWeight() {
        return weight;
    }
}