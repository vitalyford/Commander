import java.io.*;
import java.util.*;
import java.lang.Runtime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandRunner {
	
    private static Pattern pattern;
    private static Matcher matcher;
	
    private static final String IP_ADDR_MATCH = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		 "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		 "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		 "([01]?\\d\\d?|2[0-4]\\d|25[0-5]).*";

	public static void main(String[] args) {

		System.out.println("Enter \"exit\" to exit the app\n");
		
		// initialize the regular expression pattern to match IP address
		pattern = Pattern.compile(IP_ADDR_MATCH);
		
		Scanner s = new Scanner(System.in);
		
		while (true) {
			// read user's input
			System.out.print("Please enter the IP address to ping: ");
			String ip = s.nextLine();
			
			if (ip.toLowerCase().equals("exit")) {
				System.out.println("Bye!");
				break;
			}
			
			// verify the input to match the IP address pattern
			matcher = pattern.matcher(ip);
			
			// if the input matches the IP pattern, then proceed, otherwise, repeat
			if (matcher.matches()) {
			    String command = "cmd /c \"ping " + ip + "\"";
		        String output = "";			    
			    try {
			    	// execute the command
			        Process process = Runtime.getRuntime().exec(command);

			        // receive the output from the execution
			        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			        while ((output = reader.readLine()) != null) {
			            System.out.println(output);
			        }
			        System.out.println();
			    }
			    catch (IOException e) {
			        e.printStackTrace();
			    }
			}
		}
		
		s.close();
	}

}
