package week18;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/176962
public class pg_176962_과제_진행하기 {
    public String[] solution(String[][] plans) {
        ArrayList<String> answer = new ArrayList<>(plans.length);   // 과제를 수행한 순서
        PriorityQueue<Assignment> pq = new PriorityQueue<>(plans.length);   // 과제를 시작 시간 순으로 정렬
        ArrayDeque<Assignment> stack = new ArrayDeque<>(plans.length);   // 잠시 멈춘 과제 저장 및 현재 진행 중인 과제를 최상단에 저장
        for(String[] plan: plans) {
            pq.offer(new Assignment(plan));
        }

        stack.push(pq.poll());
        while(!pq.isEmpty()) {
            // 스택이 비어있으면 과제를 스택에 넣어 현재 진행 중인 과제로 표시
            if(stack.isEmpty()) {
                stack.push(pq.poll());
                continue;
            }
            Assignment next = pq.poll();
            Assignment cur = stack.pop();


            // 현재 진행 중인 과제의 끝나는 시간이 다음 과제의 시작 시간보다 빠른 경우
            if(cur.end <= next.start) {
                // 현재 진행 중인 과제는 끝남
                answer.add(cur.name);
                // 다음 과제를 시작하기 전에 잠시 멈춘 과제를 수행함
                // 그런데, 잠시 멈춘 과제가 없다면?
                if(stack.isEmpty()){
                    // 다음 과제를 스택의 최상단에 두어 현재 진행 중인 과제로 표시
                    stack.push(next);
                }
                else {
                    // 잠시 멈춘 과제를 불러와서
                    Assignment prev = stack.peek();
                    // 종료 시간 계산
                    prev.end = cur.end + prev.time;
                    // 시작 시간 갱신
                    prev.start = cur.end;
                    // 다음 과제는 pq에 넣어 현재 진행 중인 과제가 아님을 표시
                    pq.offer(next);
                }
            }
            // 현재 진행 중인 과제의 끝나는 시간이 다음 과제의 시작 시간보다 늦은 경우
            else {
                // 현재 진행 중인 과제를 잠시 멈추고, 남은 시간 계산
                cur.time = cur.end - next.start;
                stack.push(cur);
                // 다음 과제를 스택의 최상단에 두어 현재 진행 중인 과제로 표시
                stack.push(next);
            }

        }

        // 스택에 과제가 있는 경우
        while(!stack.isEmpty()) {
            Assignment a = stack.pop();
            answer.add(a.name);
        }

        return answer.toArray(new String[0]);
    }

    class Assignment implements Comparable<Assignment>{
        String name;   // 과제 이름
        int start;   // 과제 시작 시간
        int end;   // 과제 종료 시간
        int time;   // 과제 수행 시간
        public Assignment(String[] plan){
            int start = hourToMinute(plan[1]);
            int time = Integer.parseInt(plan[2]);
            this.name = plan[0];
            this.start = start;
            this.end = start + time;
            this.time = time;
        }
        private int hourToMinute(String time) {
            String[] temp = time.split(":");
            return Integer.parseInt(temp[0])*60 + Integer.parseInt(temp[1]);
        }

        @Override
        public int compareTo(Assignment o){
            if(this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }








    public static void main(String[] args) {
        TestCase[] tc = new TestCase[4];
        tc[1] = new TestCase(new String[][]{
                {"korean", "11:40", "30"},
                {"english", "12:10", "20"},
                {"math", "12:30", "40"}
        }, new String[]{"korean", "english", "math"});
        tc[2] = new TestCase(new String[][]{
                {"science", "12:40", "50"},
                {"music", "12:20", "40"},
                {"history", "14:00", "30"},
                {"computer", "12:30", "100"}
        }, new String[]{"science", "history", "computer", "music"});
        tc[3] = new TestCase(new String[][]{
                {"aaa", "12:00", "20"},
                {"bbb", "12:10", "30"},
                {"ccc", "12:40", "10"}
        }, new String[]{"bbb", "ccc", "aaa"});
        tc[0] = new TestCase(new String[][]{
                {"a", "09:00", "10"},
                {"b", "09:10", "10"},
                {"c", "09:15", "10"},
                {"d", "09:30", "10"},
                {"e", "09:35", "10"}
        }, new String[]{"a", "c", "b", "e", "d"});

        pg_176962_과제_진행하기 obj = new pg_176962_과제_진행하기();
        for (TestCase t : tc) {
            String[] result = obj.solution(t.plans);
            System.out.println(Arrays.equals(result, t.result) + " sol : " + Arrays.toString(result) + " ans : " + Arrays.toString(t.result));
            System.out.println();
        }

    }

    static class TestCase {
        String[][] plans;
        String[] result;

        public TestCase(String[][] plans, String[] result) {
            this.plans = plans;
            this.result = result;
        }
    }
}
