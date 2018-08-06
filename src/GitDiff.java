import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitDiff {

public static AllFileDetails[] getAllFileDetailsOfDiff() throws IOException {
		
		File gitWorkDir = new File("C:/Users/I338008/git/JSParser");
		Process proc=Runtime.getRuntime().exec("git diff -U0 78641a9711a771d0dcc6a581981cacc8980ef19c e57f169e0bc0112718993a205cd3307c2188a4cc", null, gitWorkDir);

        System.out.println("read output:");

        InputStream is = (InputStream) proc.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        String FullDiffData =null;// = br.toString();
        //System.out.println(FullDiffData);
        
        
        String line;
        while ((line = br.readLine()) != null) {
        	FullDiffData = FullDiffData + line + "\n";
            //System.out.println(line);
        }
        //System.out.println(FullDiffData);
        String[] fileDiffDivision = FullDiffData.split("---");
        /*for(int i=0;i<fileDiffDivision.length;i++)
        {
        	System.out.println(fileDiffDivision[i]);
        	System.out.println("**********************************************************************");
        }*/
        
        AllFileDetails[] fileDetails = new AllFileDetails[fileDiffDivision.length-1];
        for(int i=0;i<fileDiffDivision.length-1;i++)
        {
        	fileDetails[i] = new AllFileDetails();
        	fileDetails[i] =unifiedDiffHunck(fileDiffDivision[i+1]);
        	System.out.println(fileDetails[i].FileName);
        	System.out.println(fileDetails[i].GitLineinfo);
        	LineNumbers.getLinesofBothRevision(fileDetails[i].GitLineinfo,fileDetails[i]);
        	System.out.println("\n");
        }
        
        
        
        
        
        
       // List<String> filenames = new ArrayList<String>();
        //List<String> GitLineinfo = new ArrayList<String>();
        
        
        return fileDetails;

        }


public static AllFileDetails unifiedDiffHunck(String fileDiffDivision)
{
	AllFileDetails gd = new AllFileDetails();
    //for(int i=1; i<fileDiffDivision.length;i++)
    //{
    	
    	Pattern patternx = Pattern.compile("@@(.*?)@@");
        Matcher matcher = patternx.matcher(fileDiffDivision);
        
        Pattern pattern1 = Pattern.compile("\\+++(.*?)\\.js");
        Matcher matcher1 = pattern1.matcher(fileDiffDivision);
        
        int flag=0;
        List<String> Lineinfo = new ArrayList<String>();
        while(matcher.find())
        {
         while (flag==0 && matcher1.find())
        {
         	String [] arrOfFilenamesplit = matcher1.group(0).split("/", 2);
            //filenames.add(arrOfFilenamesplit[1]);
         	//System.out.println("FileName="+arrOfFilenamesplit[1]);
            gd.setFileName(arrOfFilenamesplit[1]);
            flag=1;
        }
        // Lineinfo.add(matcher.group(1));
         Lineinfo.add(matcher.group(1));
         flag=0;
        }
        //System.out.println(Lineinfo);
        gd.setGitLineinfo(Lineinfo);
       //}
    
    
	
	return gd;
}
	
}
