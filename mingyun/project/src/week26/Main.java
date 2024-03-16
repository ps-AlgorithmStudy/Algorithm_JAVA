package week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String x = br.readLine();
        int t = x.length() - 1 - (x.length() % 2 == 1 ? 1 : 0);

        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add(x);

        while (true) {
            String prev = new ArrayList<>(set).get(set.size() - 1);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < prev.length(); i += 2) {
                sb.append(prev.charAt(i));
            }
            for (int i = t; i >= 0; i -= 2) {
                sb.append(prev.charAt(i));
            }

            if (!set.add(sb.toString())) break;
        }

        ArrayList<String> list = new ArrayList<>(set);
        System.out.println(list.get(n % list.size()));
    }
}
