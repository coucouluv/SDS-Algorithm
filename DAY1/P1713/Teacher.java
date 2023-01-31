package DAY2.P1713;

import java.util.*;

public class Teacher {
    static int N, K;
    static int[] inputs;
    static Person[] people;
    static List<Person> list;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        inputs = new int[K];
        people = new Person[101];
        list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int num = sc.nextInt();
            print();
            if(people[num] == null) {
                people[num] = new Person(num,0,0,false);
            }
            if(people[num].isIn == true) {
                people[num].count++;
            } else {
                if(list.size() == N) {
                    Collections.sort(list);
                    Person removeTarget = list.remove(N - 1);
                    removeTarget.isIn = false;
                    removeTarget.count = 0;
                }
                people[num].count = 1;
                people[num].isIn = true;
                people[num].timeStamp = i;
                list.add(people[num]);
            }
        }
        Collections.sort(list, (o1, o2) -> o1.num - o2.num);
        for(Person cur: list) {
            System.out.print(cur.num + " ");
        }
    }
    public static void print() {
        for(Person cur: list) {
            System.out.println(cur.num + ": " + cur.count);
        }
    }
}
class Person implements Comparable<Person>{
    int num;
    int count;
    int timeStamp;
    boolean isIn;
    Person(int num, int count, int timeStamp, boolean isIn) {
        this.num = num;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }
    @Override
    public int compareTo(Person o) {
        int comp1 = o.count - count;
        if(comp1 == 0) {
            return o.timeStamp - timeStamp;
        }
        return  comp1;
    }
}