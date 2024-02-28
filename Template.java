import java.io.*;

import static java.lang.Integer.parseInt;

public class Template {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int K = parseInt(inputs[0]), N = parseInt(inputs[1]);

        bw.write(String.valueOf(K));
        bw.flush();
        bw.close();
    }
}
