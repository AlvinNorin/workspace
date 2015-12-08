	import java.io.ByteArrayOutputStream;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.logging.Level;
	import java.util.logging.Logger;
public class Main {
	 
	    public static void main(String[] args) throws FileNotFoundException, IOException {
	        File file = new File("src/h");
	 
	        FileInputStream fis = new FileInputStream(file);
	        //System.out.println(fis.read(byte[1024]));
	        //System.out.println(file.exists() + "!!");
	        //InputStream in = resource.openStream();
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = new byte[1024];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); //no doubt here is 0
	                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
	                System.out.println("read " + readNum + " bytes," + buf.length);
	            }
	        } catch (IOException ex) {
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        byte[] bytes = bos.toByteArray();
	        byte[] i = new byte[bytes.length];
	        String b = toBinary(bytes);
	        System.out.println(bytes.toString() + " " + b);
	        int n = 0;
	        int oCo = 0;
	        String seed = "";
	        for (int c = 0; c < 1; c ++) {
	        	//String s = "";
	        	for (int f1 = 0; f1 < 16; f1 ++) {
	        		String bin = Integer.toBinaryString(f1);
	        		while (bin.length() < 4)
	        			bin = 0 + bin;
	        		System.out.println(bin);
	        		String nSeed = "";
	        		int nN = 0;
	        		for (int co = 0; co < b.length(); co +=4) {
		        		String ss = "" + b.charAt(co) + b.charAt(co + 1) + b.charAt(co + 2) + b.charAt(co + 3);
		        		if (ss.matches(bin)) {
		        			nN ++;
		        			System.out.println(nN + " " + ss + " " + bin + " " + co/4 + " " + b.length());
		        			for (int _0 = 0; _0 < co/4 - oCo-1; _0 ++) {
		        				nSeed += 0;
		        				//System.out.println(co/4 + " " + oCo/4);
		        			}
		        			nSeed += 1;
		        			oCo = co/4;
		        		}// else System.out.println(ss + "!=" + s);
		        	}
	        		if (nN > n) {
	        			seed = bin + nSeed;
	        			n = nN;
	        		}
	        		nN = 0;
	        	}
	        	
        		while (seed.length()-4 < b.length()/4)
        			seed += 0;
	        }
	        int ct = 0;
	        for (int la = 4; la < seed.length() - 4; la ++) {
	        	if (seed.regionMatches(la, "1", 0, 1) /*&& b.regionMatches(((la-4)*4)-(ct*4), seed.substring(0, 4), 0, 4)*/) {
	        		System.out.println(la-4 + "-" + 1);
	        		//System.out.println(b.regionMatches((la-4)*4, seed.substring(0, 4), 0, 4) + " " + seed.substring(0, 4));
	        		b = b.substring(0, (la-4)*4) + b.substring(((la-4)*4)+4, b.length());
	        		System.out.println(b + " " + (((la-4)*4)-(ct*4)) + " " + (((la-4)*4)-(ct*4)) + "+" + b.length() + " " + seed.substring(4, seed.length() - 4));
	        		ct ++;
	        		//la -= 4
	        	}
	        }
	        i = fromBinary(seed + b);
	        System.out.println(seed + " " + i);
	        //below is the different part
	        File someFile = new File("src/i");
	        FileOutputStream fos = new FileOutputStream(someFile);
	        fos.write(i);
	        fos.flush();
	        fos.close();
	    }
	    
	    static String toBinary( byte[] bytes )
	    {
	        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
	        for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
	            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
	        return sb.toString();
	    }

	    static byte[] fromBinary( String s )
	    {
	        int sLen = s.length();
	        byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
	        char c;
	        for( int i = 0; i < sLen; i++ )
	            if( (c = s.charAt(i)) == '1' )
	                toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
	            else if ( c != '0' )
	                throw new IllegalArgumentException();
	        return toReturn;
	    }

}
