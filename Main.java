import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {

	public static void main(String[] args) throws IOException {
		double[] a = new double[1000];
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		File file = new File("D://Memory.txt");
		try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
			String readLine;
			double temp;
			int line = 0;
			int i = 0, count = 0;
			String str;
			double sum = 0.0;
			double max = 0.0;
			while ((readLine = bf.readLine()) != null) {
				if (line % 2 != 0) {
					str = readLine;

					StringTokenizer st = new StringTokenizer(str, " ");
					String s1 = st.nextToken();
					str = st.nextToken();
					count++;

					temp = Integer.parseInt(str);

					a[i++] = temp / 1024;

				}
				line++;
			}

			String str1;
			for (int j = 0; j < count; j++) {
				str1 = String.format("%.2f", a[j]);
				obj1.put(j + "s", str1);
				if (max < a[j])
					max = a[j];
				sum = sum + a[j];
			}

			double average = sum / count;
			String pattern = String.format("%.2f", average);
			String pattern1 = String.format("%.2f", max);

			obj.put("AverageMemory(MB)", pattern);
			obj.put("values: ", obj1);
			obj.put("MaximumMemory(MB)", pattern1);
			obj.put("Usecasename", "HomePage");

			JSONArray jsonList = new JSONArray();
			jsonList.add(obj);

			try (FileWriter file1 = new FileWriter("D://output.json")) {
				file1.write(jsonList.toString());
				file1.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
