package wordTree.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * inputFile Creator for Assignment 4
 * @author hloya1
 *
 */
public class inputFileCreator 
{
	static char oldChar;

	public static void main(String[] args) throws IOException 
	{
		String s;
		FileWriter file = new FileWriter(new File(".\\input.txt"));

		for(int i=0; i < 50000; i++)
		{
			s = getRandomString();
			file.write(s);
		}

		file.flush();
		file.close();
	}

	/**
	 * Generate Random String of Length 125 using the random chars provided below
	 * @return - a string
	 */
	public static String getRandomString() {
		String RANDOMCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
		StringBuilder newString = new StringBuilder();
		Random rnd = new Random();
		while (newString.length() < 125)
		{ 
			// length of the random string.
			int index = (int) (rnd.nextFloat() * RANDOMCHARS.length());
			char newChar = RANDOMCHARS.charAt(index);

			//Using the below condition to avoid multiple spaces - but this doesnt repeat any other character as well
			if(newChar == oldChar && oldChar == ' ')
			{
				continue;
			}
			else	//if(newChar != oldChar && oldChar != ' ')
				{
					oldChar = newChar;
					newString.append(newChar);
				}

		}
		String newStringToReturn = newString.toString() + "\n";
		return newStringToReturn;
	}
}
