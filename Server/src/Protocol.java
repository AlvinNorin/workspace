import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.*;
import org.newdawn.slick.util.ResourceLoader;

public class Protocol {
	
	static String server_name = "Lumen Capitalism";
	static String server_icon = "";
	
	public static String process(String command) {
		String response = "";
		if (Integer.parseInt(command.substring(1, 5)) == 1)
			response = command.substring(1, 5) + server_name;
		
		else if (Integer.parseInt(command.substring(1, 5)) == 2) {
			Protocol p = new Protocol();
			URL url = ResourceLoader.getResource("vector.png");
			//URL url = p.getClass().getResource("vector.png");
			try {
				BufferedImage img = ImageIO.read(url);
				
				server_icon = encodeToString(img, "PNG");
				System.out.println(server_icon);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response = command.substring(1, 5) + server_icon;
		}
		return response;
    }
	
	public String imageToString(BufferedImage bImage, String path)   {
        char[] imageString = null;

        String formatName = path.substring(path.lastIndexOf('.')+1, path.length());

        //image to bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, formatName, baos);
            baos.flush();
            byte[] imageAsRawBytes = baos.toByteArray();
            baos.close();

            //bytes to string
            Base64Coder b64enc = new Base64Coder();
            imageString = b64enc.encode(imageAsRawBytes);
            //don't do this!!!
            //imageString = new String(imageAsRawBytes);
        } catch (IOException ex) {
            Logger.getLogger(Protocol.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imageString.toString();
    }
	
	public BufferedImage stringToImage(String imageString)    {
        //string to ByteArrayInputStream
        BufferedImage bImage = null;
        try {
            byte[] output = Base64Coder.decode(imageString);
            ByteArrayInputStream bais = new ByteArrayInputStream(output);
            bImage = ImageIO.read(bais);
        } catch (IOException ex) {
            Logger.getLogger(Protocol.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bImage;
    }
	
	public static String encodeToString(BufferedImage image, String type) {
		char[] imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();

			//BASE64Encoder encoder = new BASE64Encoder();
			imageString = Base64Coder.encode(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString.toString();
	}
}
