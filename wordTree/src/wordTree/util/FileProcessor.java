package wordTree.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that provides functionality of reading line by line from some file
 * @author hloya
 *
 */
public class FileProcessor 
{

	/**
	 * Data Members
	 */
	FileReader isr;
	BufferedReader br;

	/**
	 * Parameterized constructor that initializes the reader to read the specified file
	 * @param filePath - contains the path of the file that needs to be read
	 */
	public FileProcessor(String filePath)
	{
		try
		{
			isr = new FileReader(new File(filePath));
			br = new BufferedReader(isr);
		}
		catch (FileNotFoundException e) 
		{
			System.err.println("File Not Found, Please recheck the path specified in arguments");
			e.printStackTrace();
		}
	}
	

	/**
	 * Method that reads 1 line at a time from the input file - made synchronized to make it thread safe
	 * @param filePath - contains the path of the file from which a line needs to be read
	 * @return a line from the input file as String
	 */
	public synchronized String readLine(String filePath)
	{
		String oneLineFromFile = "";
		try 
		{
			oneLineFromFile = br.readLine();
		} 
		catch (IOException e) 
		{
			System.err.println("Cannot Read from File");
			e.printStackTrace();
		}

		return oneLineFromFile;
	}
	
	
	//Closes the file opened for reading
	/**
	 * Method to close the file opened for Reading
	 */
	public void closeFile()
	{
		try
		{
			br.close();
		}
		catch (IOException e) 
		{
			System.err.println("File in Use, File cannot be closed");
			e.printStackTrace();
		}
	}

	/**
	 * Testing purpose
	 */
	/*
	public static void main(String args[]) throws FileNotFoundException
	{
		String filePath = ".\\input.txt";
		FileProcessor fp = new FileProcessor(filePath);

		String abc = "";
		while((abc = fp.readLine(filePath)) != null)
		{			
			System.out.println(abc);
		}
	}
	*/
}
