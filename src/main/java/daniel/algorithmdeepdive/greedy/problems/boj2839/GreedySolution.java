package daniel.algorithmdeepdive.greedy.problems.boj2839;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GreedySolution {
	public static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int minTotalBags = getMinTotalBags(N);

		bw.write(minTotalBags + "");

		br.close();
		bw.flush();
		bw.close();
	}

	private static int getMinTotalBags(int N) {
		if (N % 5 == 0) {
			return N / 5;
		}

		int maxFiveKgBags = N / 5;
		int minTotalBags = -1;
		int remainingWeight;
		int threeKgBags;
		for (int i = maxFiveKgBags; i >= 0; i--) {
			remainingWeight = N - (i * 5);
			if (remainingWeight % 3 == 0) {
				threeKgBags = remainingWeight / 3;
				minTotalBags = i + threeKgBags;
				break;
			}
		}
		return minTotalBags;
	}

	public static void main(String[] args) throws Exception {
		solution();
	}
}
