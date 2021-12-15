package basepackage;

import android.util.ArrayMap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will manage the semester objects.
 * @author karmugilan
 */

public final class SemManager
{
	//field variables
	private HashMap<String,Semester> semesters;//Stores semester objects mapped to the semester name.
	private CSVParser csvParser;
	private ArrayMap<String, ArrayMap<ArrayList<String>, ArrayList<Float>>> semestersInfo;
	
	public SemManager(InputStream inputStream)
	{
		semesters = new HashMap<String,Semester>();
		csvParser = new CSVParser(inputStream);
		createSemObjects();
	}
	
	private void createSemObjects()
	{
		semestersInfo = csvParser.getSemesterInfos(); //get the semester Infos!!

		for(int k = 1; k<semestersInfo.size()+1; k++)
		{
			ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo = semestersInfo.get("Semester "+k);
			Semester sem = new Semester(semInfo);
			semesters.put("Semester "+k , sem);//store the semester objects.
		}
		
	}

	public ArrayMap<ArrayList<String>, ArrayList<Float>> getSemInfoForOneSem(int whichSem)
	{
		String sem = "Semester "+whichSem;
		Semester selectedSem = semesters.get(sem);
		return selectedSem.getSemInfo();
	}


	public float getCGPAForOneSem(int whichSem) //this method returns cgpa for the selected semester
	{
		String sem = "Semester "+whichSem;
		Semester selectedSem = semesters.get(sem);
		return selectedSem.getCGPA();	//return the cgpa for selected semester.
	}
	

}
