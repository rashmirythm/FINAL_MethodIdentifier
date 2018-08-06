import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jgit.api.errors.GitAPIException;

import main.java.io.reflectoring.diffparser.api.UnifiedDiffParser;


public class FinalClient {

	public static String commitHash;
	//public static String parentcommitHash;

	public static void main(String args[]) throws IOException, GitAPIException
	{
		Scanner sc=new Scanner(System.in);  

		System.out.println("Enter the commit id:");  
		commitHash=sc.next(); 
		sc.close();
		
		System.out.println("*******************UNIFIED DIFF DATA**************************");
		AllFileDetails fileDetails[] = GitDiff.getAllFileDetailsOfDiff();
		
	}
}