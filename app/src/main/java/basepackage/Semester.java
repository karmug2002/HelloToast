package basepackage;

import android.util.ArrayMap;
import java.util.ArrayList;

/**
 * This class handles all stuff related to cgpa.
 * @author implemented by Karmugilan for the miniproject.
 */

final  class Semester 
{
	//field variables
	private ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo; //hashmaps are like dictionaries storing a key and a value.
	private basepackage.InputProcessor ip;
	private float totalAcquiredCP;
	private float totalGivenCP;
	private ArrayList<Float> givenCPS;
	//CPS = Credit Points
	
	public Semester(ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo)
	{
		this.semInfo = semInfo; //receive the semInfo from the constructor!!
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
		ArrayList<Float> inputs = ip.getInput();	   //receive the inputs from the user

		for(int k = 0; k<inputs.size(); k++)
		{
			totalAcquiredCP += inputs.get(k) * givenCPS.get(k);
		}
		
	}
	
	public float getCGPA() //initialize the values of the private variables
	{
		ip = new InputProcessor(semInfo);
		sumGivenCPS();
		sumAcquiredCPS();
		return totalAcquiredCP/totalGivenCP;
	}

	@Override
	public String toString()  //automatically called by the println fn.
	{
		return semInfo.toString();
	}
	
}
