package basepackage;

import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;

/**
 * This class handles all stuff related to cgpa.
 * @author implemented by Karmugilan for the miniproject.
 */

final  class Semester 
{
	//field variables
	private ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo; //hashmaps are like dictionaries storing a key and a value.
	private float totalAcquiredCP;
	private float totalGivenCP;
	private ArrayList<Float> givenCPS;
	private ArrayList<Float> userInputs;
	//CPS = Credit Points
	
	public Semester(ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo)
	{
		this.semInfo = semInfo; //receive the semInfo from the constructor!!
		givenCPS = new ArrayList<>();
		userInputs = new ArrayList<>();
	}
	
	private void sumGivenCPS() 
	{
		ArrayList<Float> value = semInfo.valueAt(0);
		for(Float v : value)
		{
			totalGivenCP+=v;
		}
		givenCPS = value; //initialize givenCPS from the semInfo
	}
	
	private void sumAcquiredCPS()
	{
		if(userInputs!=null)
		{
			for(int k = 0; k<userInputs.size() && k < givenCPS.size(); k++)
			{
				totalAcquiredCP += userInputs.get(k) * givenCPS.get(k);
			}
		}

		
	}
	
	public float getCGPA() //initialize the values of the private variables
	{
		totalAcquiredCP=0;
		totalGivenCP=0;
		if(userInputs!=null)
		{
			sumGivenCPS();
			sumAcquiredCPS();
			Log.i("Semester",userInputs.toString());
			Log.i("Semester",String.valueOf(totalAcquiredCP));
			Log.i("Semester",String.valueOf(givenCPS.size()));
			Log.i("Semester",String.valueOf(totalGivenCP));
			Log.i("Semester",String.valueOf(userInputs.size()));

		}
		return totalAcquiredCP/totalGivenCP;
	}

	public ArrayMap<ArrayList<String>, ArrayList<Float>> getSemInfo()
	{
		return semInfo;
	}

	public void setUserInput(ArrayList<Float> userInput) {
		this.userInputs = userInput;
	}

	@Override
	public String toString()  //automatically called by the println fn.
	{
		return semInfo.toString();
	}
	
}
