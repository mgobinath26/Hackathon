import java.util.*;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.FileNotFoundException;

public class CPU {

	public static void main(String[] args) throws IOException {
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		double[] arr = new double[1000];
		File file = new File("D:\\cpu.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String readLine;
			String str1, str, value, result;
			int line = 0;
			int i = 0, j, val = 0;
			int count = 0, start = 0;
			double temp, max = 0.0, sum = 0.0;
			while ((readLine = br.readLine()) != null) {
				count += 1;
				StringTokenizer st = new StringTokenizer(readLine);
				while (st.hasMoreTokens()) {
					str = st.nextToken();
					start += 1;
					if (start == 9) {
						value = str;
						temp = Double.parseDouble(value);
						arr[i++] = temp;
						start = 0;
						break;
					}
				}
			}
			for (j = 0; j < count; j++) {
				val = j + 1;
				str1 = String.format("%d", val);
				result = String.format("%.1f", arr[j]);
				obj1.put(str1 + "s", result);
				if (arr[j] > max) {
					max = arr[j];
				}
				sum = sum + arr[j];
				val = 0;
			}
			double average = sum / count;
			String avg = String.format("%.2f", average);
			String maximum = String.format("%.2f", max);
			
			obj.put("AverageCpu", avg);
			obj.put("values: ", obj1);
			obj.put("MaximumCpu", maximum);
			obj2.put("sampletransaction",obj);
			JSONArray jsonList = new JSONArray();
			jsonList.add(obj2);

			try (FileWriter file1 = new FileWriter("D://cpuOutput.json")) {
				file1.write(jsonList.toString());
				file1.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
