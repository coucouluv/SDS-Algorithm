package DAY2.P1713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Me {
    private static int N, total;
    private static List<Picture> list = new ArrayList<>();
    private static Picture[] students = new Picture[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st= new StringTokenizer(br.readLine());
        total = Integer.parseInt(st.nextToken());
        st= new StringTokenizer(br.readLine());

        for(int i = 0; i < total; i++) {
            int student = Integer.parseInt(st.nextToken());
            if(students[student] == null) {
                students[student] = new Picture(student, 0, i);
            }
            boolean isHere = false;
            for (int j = 0; j < list.size() ; j++) {
                if(list.get(j).num == student) {
                    students[student].good += 1;
                    isHere = true;
                    break;
                }
            }
            if(isHere == false) {
                if(list.size() == N) {
                    Collections.sort(list);
                    Picture removeTarget = list.remove(N - 1);
                    removeTarget.good = 0;
                }
                students[student].good = 1;
                students[student].time = i;
                list.add(students[student]);
            }

        }
        Collections.sort(list, (o1, o2) -> o1.num - o2.num);
        for(Picture cur: list) {
            System.out.print(cur.num + " ");
        }
    }
}

class Picture implements Comparable<Picture>{
    int num;
    int good;
    int time;
    Picture(int num, int good, int time) {
        this.num = num;
        this.good = good;
        this.time = time;
    }

    @Override
    public int compareTo(Picture p) {
        if(p.good == this.good) {
            return p.time - this.time;
        }
        return p.good - this.good;
    }
}
