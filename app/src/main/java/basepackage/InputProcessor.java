package basepackage;

/** 
 * This class processes the inputs using scanner
 * for now , but  as the app develops this class
 * will be modified.
 * @author implemented by Karmugilan for the miniproject.
 */

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

final class InputProcessor
{
	//field variables
	private ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo;
	private Scanner scanner;
	private float in;
	
	public InputProcessor(ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo)
	{
		this.semInfo = semInfo;
		scanner = new Scanner(System.in);

	}

	public ArrayList<Float> getInput()
	{
		
		ArrayList<Float> input = new ArrayList<>();
		ArrayList<String> semNames = semInfo.keyAt(0);
		for(String s : semNames)
		{
			print(s);
			in = scanner.nextFloat();
			input.add(in);
		}
		scanner.close();
		return input;
	}
	
	private void print(String s)
	{
		System.out.print("Enter Your Grade for this Subject " + s +": ");
	}

}
