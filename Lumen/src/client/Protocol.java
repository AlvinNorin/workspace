/**
 *	**	**	**	**	**	**	**	**
 * 	Protocol.java
 * 	client
 *	**	**	**	**	**	**	**	**
 *
 * 	Author		:		alvin
 * 	Year		:		2015
 * 	Time		:		10:11:56 PM
 * 
 *	**	**	**	**	**	**	**	**
 */
package client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * @author alvin
 */
public class Protocol {
	public static String server_name = "Loading..";
	public static BufferedImage server_icon = null;
	
	public static void process(String command) {
		if (Integer.parseInt(command.substring(0, 4)) == 1)
			server_name = command.substring(4);
		
		if (Integer.parseInt(command.substring(0, 4)) == 2) {
			stringToImage(command.substring(4));
		}
	}
	
	public static BufferedImage stringToImage(String imageString)    {
		System.out.println(imageString);
        //string to ByteArrayInputStream
        BufferedImage bImage = null;
        Base64Coder b64dec = new Base64Coder();
        try {
            byte[] output = b64dec.decode(imageString);
            ByteArrayInputStream bais = new ByteArrayInputStream(output);
            bImage = ImageIO.read(bais);
        } catch (IOException e) {
            System.err.println("Error");
        }

        return bImage;
    }
}
