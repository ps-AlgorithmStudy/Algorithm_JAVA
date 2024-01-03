package week18;

import java.sql.Array;
import java.util.*;

class Solution {
    static class Subject implements Comparable<Subject>{
        String name;
        int start;
        int time;

        // 초기에 주어진 과제
        Subject(String[] plan){
            this.name = plan[0];
            this.start = convertToSecond(plan[1]);
            this.time = Integer.parseInt(plan[2]);
        }

        // 미룰 과제
        Subject(String name, int time){
            this.name = name;
            this.time = time;
        }

        @Override
        public int compareTo(Subject o) {
            return this.start - o.start;
        }
    }
    // 시간 문자열을 초로 변경하는 함수
    public static int convertToSecond(String time){
        String[] t = time.split(":");
        return (Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]));
    }
    public List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();

        // 시간 순으로 정렬하기 위해 우선순위 큐 사용
        PriorityQueue<Subject> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));

        // 멈춘 과제를 저장하기 위한 stack
        Stack<Subject> todo = new Stack<>();

        //  plan in plans -> pq
        for(String[] plan :plans){
            pq.offer(new Subject(plan));
        }

        // 과제 저장에 사용할 index
        int idx = 0;

        // 과제 시작
        while(!pq.isEmpty()){
            Subject cur = pq.poll();

            // 현재 수행할 과제
            String curName = cur.name;
            int curStart = cur.start;
            int curTime = cur.time;

            // 현재 시간
            int TIME = curStart;

            // 1. 해야할 과제가 뒤에 더 있다
            if(!pq.isEmpty()){
                Subject nextSubject = pq.peek();

                // 1-1. 현재 과제를 다음과제 시작 전에 못할 경우
                if(TIME + curTime > nextSubject.start){
                    todo.push(new Subject(curName, TIME - nextSubject.start + curTime));
                }
                // 1-2. 현재 과제를 끝내고 시간이 남을 때
                else{
                    // 1-2-1. 다음 과제를 끝나고 바로 해야할 때
                    if(TIME + curStart == nextSubject.start){
                        answer.add(curName);
                    }
                    // 1-2-2. 끝나고 시간이 남아 미뤘던 과제를 해야할 때
                    else{// if TIME + curTime < nextSubject.start
                        answer.add(curName);
                        TIME += curTime;

                        while(!todo.isEmpty()){
                            Subject sub = todo.pop();

                            // 1-2-2-1. 미룬 과제를 완료할 수 있을 때
                            if(nextSubject.start >= TIME + sub.time){
                                TIME += sub.time;
                                answer.add(sub.name);
                            }
                            // 1-2-2-2. 미룬 과제를 완료할 수 없을 때
                            else{
                                // 과제 한만큼만 시간을 빼서 다시 스택에 추가
                                todo.push(new Subject(sub.name, TIME - nextSubject.start + sub.time));
                                break;
                            }
                        }
                    }
                }
            }
            // 2. 해야될 과제가 없는 경우
            else{
                // 2-1. 하던 과제가 남아있을 경우
                if(!todo.isEmpty()){
                    answer.add(curName);

                    // 남아있는 과제를 순차적으로 한다.
                    while(!todo.isEmpty()){
                        answer.add(todo.pop().name);
                    }
                }
                // 2-2. 하다 만 과제도 없는 경우
                else{
                    TIME += curTime;
                    answer.add(curName); // 완료한 과제는 정답 배열에
                }
            }
        }
        return answer;
    }
}
public class Main_176962 {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] plan1 = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        String[][] plan2 = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        String[][] plan3 = {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}};

        System.out.println(s.solution(plan1).toString());
        System.out.println(s.solution(plan2).toString());
        System.out.println(s.solution(plan3).toString());
    }
}
