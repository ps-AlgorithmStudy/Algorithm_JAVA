package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class bj_9519_졸려 {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb;
        String result = null;

        while (N-- > 0) {
            sb = new StringBuilder();
            for (int i = 0; i < str.length(); i += 2) { // index 가 짝수인 char을 먼저 앞부분에 담는다.
                sb.append(str.charAt(i));
            }
            int n = str.length() - 1; // str 의 length에 따라 항상 n 이 홀수가 아닐 수 있음.
            if (n % 2 == 0) n--;
            for (int i = n; i >= 0; i-= 2) { // index가 홀수인 char 을 뒤에서부터 담는다.
                sb.append(str.charAt(i));
            }

            str = sb.toString();
            if (list.contains(str)){ // 방금 새로 생성한 str이 list 에 이미 추가된 str인지 확인한다.
                int size = list.size();
                result = list.get(N % size);
                break;
            }
            list.add(str);
        }

        if (result != null) // cycle이 발견됐다면
            System.out.println(result);
        else // cycle이 발견되지 않았다면,
            System.out.println(list.get(list.size() - 1));
    }
}

