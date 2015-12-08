/**
 *	**	**	**	**	**	**	**	**
 * 	Create.java
 * 	file
 *	**	**	**	**	**	**	**	**
 *
 * 	Author		:		alvin
 * 	Year		:		2013
 * 	Time		:		9:22:46 PM
 * 
 *	**	**	**	**	**	**	**	**
 */
package file;

import java.util.Formatter;

/**
 * @author alvin
 */
public class Create {
	
	private static Formatter f;
	
	public static void write(String file, String str) {

		try {
			f = new Formatter(file);
			f.format("%s", str);
			System.out.println("Successfully created file : \""+file+"\"" + str);
		} catch (Exception e) {
			System.out.println("Error: could not create file : \""+file+"\"!");
		}
	}
	
	public static void closeFile() {
		f.close();
	}

}
