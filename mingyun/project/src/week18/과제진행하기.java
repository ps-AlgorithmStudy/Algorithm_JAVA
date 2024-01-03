package week18;

import java.util.*;
public class 과제진행하기 {


    class Plan {
        String name;
        int startTime;
        int leftMinute;
        int originalStart;

        Plan(String name, int startTime, int leftMinute) {
            this.name = name;
            this.startTime = startTime;
            this.leftMinute = leftMinute;
            this.originalStart = startTime;
        }
    }

    class Solution {
        public String[] solution(String[][] plans) {
            List<String> completedTasks = new ArrayList<>();
            PriorityQueue<Plan> taskQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.startTime));
            Stack<Plan> pausedTasks = new Stack<>();

            for (String[] plan : plans) {
                String[] timeParts = plan[1].split(":");
                int startTime = Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
                int playTime = Integer.parseInt(plan[2]);
                taskQueue.add(new Plan(plan[0], startTime, playTime));
            }

            Plan currentTask = null;
            int currentTime = 0;

            while (!taskQueue.isEmpty() || currentTask != null || !pausedTasks.isEmpty()) {
                while (!taskQueue.isEmpty() && (currentTask == null || taskQueue.peek().startTime <= currentTime)) {
                    if (currentTask != null) {
                        currentTask.leftMinute -= (currentTime - currentTask.startTime);
                        pausedTasks.push(currentTask);
                    }
                    currentTask = taskQueue.poll();
                    currentTask.startTime = Math.max(currentTime, currentTask.startTime);
                }

                currentTime++;

                if (currentTime >= currentTask.startTime + currentTask.leftMinute) {
                    completedTasks.add(currentTask.name);
                    currentTask = null;

                    if (!pausedTasks.isEmpty()) {
                        currentTask = pausedTasks.pop();
                        currentTask.startTime = currentTime;
                    }
                }
            }

            String[] answer = new String[completedTasks.size()];
            for (int i = 0; i < completedTasks.size(); i++) {
                answer[i] = completedTasks.get(i);
            }
            return answer;
        }
    }

}
