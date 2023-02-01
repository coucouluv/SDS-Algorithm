package DAY3.P11279;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Teacher {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        MaxHeap heap = new MaxHeap();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num == 0) {
                sb.append(heap.delete()).append("\n");
            }
            else {
                heap.insert(num);
            }

        }
        System.out.println(sb);
    }
}

class MaxHeap {
    List<Integer> list;

    public MaxHeap() {
        list = new ArrayList<>(100001); //미리 크기 지정해주기
        list.add(0);
    }
    public void insert(int val) {
        //1. 마지막에 추가
        list.add(val);
        //2. 부모랑 조건 비교, 교환
        int current = list.size()-1;
        int parent = current / 2;
        while(true) {
            //1. current가 루트일 때 탈출
            //2. 부모, 자식 조건을 만족하면 탈출
            if(parent == 0 || list.get(parent) >= list.get(current)) {
                break;
            }
            int tmp = list.get(parent);
            list.set(parent, list.get(current));
            list.set(current, tmp);
            current = parent;
            parent = current / 2;
        }
    }

    public int delete() {
        //1. root 제거
        int top = list.get(1);
        //2. 마지막 노드를 root로 이동
        list.set(1, list.get(list.size()-1));
        list.remove(list.size()-1);

        //3. 왼쪽, 오른쪽 중 조건이 안맞는 것을 선택 후 조건에 맞게 swap
        int currentNode = 1;
        while(true) {
            int leftNode = currentNode * 2;
            int rightNode = currentNode * 2 + 1;

            if(leftNode >= list.size()) { //리프 노드임
                break;
            }

            int targetValue = list.get(leftNode);
            int targetNode = leftNode;

            if(rightNode < list.size() && targetNode < list.get(rightNode)) {
                targetValue = list.get(rightNode);
                targetNode = rightNode;
            }
            if(list.get(currentNode) < list.get(targetNode)) {
                int temp = list.get(currentNode);
                list.set(currentNode, list.get(targetNode));
                list.set(targetNode, temp);
            }
            else {

                break;
            }
            currentNode = targetNode;
        }
        return top;
    }
}