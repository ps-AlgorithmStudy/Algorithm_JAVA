import java.io.*;

public class Main_bj_9466_텀프로젝트 {


    static int solution(int N, int[] arr) {

        int[] v = new int[N + 1];

        // dfs 탐색 수행
        for(int key : arr) {
            dfs(arr, v, key);
        }

        // N - 팀으로 구성된 인원 수 
        return N;
    }


    static int dfs(int[] arr, int[] v, int key) {
        v[key] = true;

        int next = arr[key];
        dfs(arr, v, next);

        // 맨 끝에 갔더니 시작 위치와 같아지는 경우
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.next());
            }

            System.out.println(solution(N, arr));
        }
    }

}