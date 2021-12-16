package basepackage;

import android.util.ArrayMap;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * This class will manage the semester objects.
 * @author karmugilan
 */

public final class SemManager
{
	//field variables
	private ArrayMap<String,Semester> semesters;//Stores semester objects mapped to the semester name.
	private CSVParser csvParser;
	private ArrayMap<String, ArrayMap<ArrayList<String>, ArrayList<Float>>> semestersInfo;
	
	public SemManager(InputStream inputStream)
	{
		semesters = new ArrayMap<>();
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
		//Log.i("SemManager",semesters.get("Semester 8").toString());
		return selectedSem.getSemInfo();
	}


	public void setGivenCPS(ArrayList<Float> userInputs,Semester semester) {
		 semester.setUserInput(userInputs);
	}


	public float getCGPAForOneSem(int whichSem,ArrayList<Float> userInputs) //this method returns cgpa for the selected semester
	{
		String sem = "Semester "+whichSem;
		Semester selectedSem = semesters.get(sem);
		setGivenCPS(userInputs,selectedSem);
		return selectedSem.getCGPA();	//return the cgpa for selected semester.
	}

	public int getNumOfSems()
	{
		return semesters.size();
	}

}
