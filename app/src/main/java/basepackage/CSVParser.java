package basepackage;

/**
 * This is a basic csv parser class. it parses using the delimeter ",".
 * It reads the files line by line and uses split to parse the files.
 * @author karmugilan
 *
 */

import android.util.Log;
import android.util.ArrayMap;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

final class CSVParser
{
	//field variables
	private ArrayMap<ArrayList<String>,ArrayList<Float>> semInfo;
	private ArrayMap<String,ArrayMap<ArrayList<String>,ArrayList<Float>>> semesterInfos;
	private Scanner scanner;
	
	public CSVParser(InputStream inputStream)
	{
		scanner = new Scanner(inputStream);
		semInfo = new ArrayMap<>();
		semesterInfos = new ArrayMap<>();
	}
	
	private static float parse(String s)
	{
		return Float.parseFloat(s);
	}
	
	private void readFile()
	{
		int count = 1;
		ArrayList<String> semNames = new ArrayList<String>();
		ArrayList<Float> givenCPS = new ArrayList<Float>();
		while(scanner.hasNext())
		{
			String line = scanner.nextLine();
			String[] strs = line.split(",");  //splitting the strings using ","
			String s = "Semester "+count;
			
			if(!line.startsWith(s))
			{
				semNames.add(strs[2]);
				givenCPS.add(parse(strs[strs.length-1]));
				
			}
			else
			{
				semInfo.put(semNames,givenCPS);
				semesterInfos.put(s, semInfo);//old object 
				semInfo = new ArrayMap<>();//adding a new reference,new object to store the data
				semNames = new ArrayList<String>();
				givenCPS = new ArrayList<Float>();
				count++;    //to know what semester is.
			}
			
		}
		scanner.close();
	}
	
	public ArrayMap<String, ArrayMap<ArrayList<String>, ArrayList<Float>>> getSemesterInfos()
	{
		this.readFile();
		Log.i("CSVParser " , semesterInfos.get("Semester 1").keyAt(0).toString());
		return semesterInfos;
	}

}
