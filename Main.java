import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args) throws IOException {
		double[] a = new double[10000];
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		File file = new File("D://Memory.txt");
		try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
			String readLine;
			double temp;
			int line = 0;
			int i = 0;
			double sum = 0.0;
			double max = 0.0;
			while ((readLine = bf.readLine()) != null) {
				if (line % 2 != 0) {

					String str = readLine;
					str = str.replaceAll("[^0-9]", "");
					str = str.trim();

					temp = Integer.parseInt(str);

					a[i++] = temp / 10000;

				}
				line++;
			}
			String str1;
			for (int j = 0; j < 938; j++) {
				str1 = String.format("%d", j);
				obj1.put(str1 + "s", a[j]);
				if (max < a[j])
					max = a[j];
				sum = sum + a[j];
			}

			double average = sum / 938;

			DecimalFormat df = new DecimalFormat("#.###");
			df.setRoundingMode(RoundingMode.CEILING);
			obj.put("AverageMemory(MB)", df.format(average));
			obj.put("values: ", obj1);
			obj.put("MaximumMemory(MB)", df.format(max));
//			File myFile = new File("C:/MyTestFile.txt");
// 			// check if file exist, otherwise create the file before writing
//			if (!myFile.exists()) {
//				myFile.createNewFile();
//			}
// 			Writer writer = new FileWriter(myFile);
//			BufferedWriter BufferedWriter = new BufferedWriter(writer);
//			BufferedWriter.write(obj);
			System.out.println(obj);
		}
	}
}
