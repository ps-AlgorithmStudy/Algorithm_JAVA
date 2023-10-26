package week11;

public class pg_43238_입국심사 {
    public static long solution(int n, int[] times) {
        long answer = 0;
        long left = 1;
        long right = (long)n*(long)times[0];  // 상한선
        long mid = 0;
        while(left<=right){
            mid = (left+right) / 2;
            long reviewablePeople = 0;
            for(int time: times){
                reviewablePeople += mid / time;  // mid 시간 동안 심사가능한 인원수 계산

                if(reviewablePeople>=n) break;  // 심사가능한 인원수가 n을 넘으면 더 계산하지 않음
            }

            /* 심사가능한 인원수가 n을 넘었다는 것은 시간이 남는다는 뜻
            *  mid ~ right 까지의 시간은 확인할 필요 없음 */
            if(reviewablePeople>=n) {
                answer = mid;
                right = mid - 1;
            }
            /* 심사가능한 인원수가 n보다 작다는 것은 시간이 부족하다는 뜻
             * left ~ mid 까지의 시간은 확인할 필요 없음 */
            else {
                left = mid + 1;
            }
        }
        return answer;
    }




    public static void main(String[] args) throws Exception {
        TestCase tc1 = new TestCase(6, new int[]{7,10}, 28);
        TestCase tc2 = new TestCase(7, new int[]{2,3}, 9);
        for(TestCase tc: new TestCase[]{tc1,tc2}) {
            System.out.println("sol: "+solution(tc.n, tc.times)+", res: "+tc.result);
        }
    }

    static class TestCase {
        int n;
        int[] times;
        long result;

        public TestCase() {
        }

        public TestCase(int n, int[] times, long result) {
            this.n = n;
            this.times = times;
            this.result = result;
        }
    }
}
