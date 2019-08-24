package utils;

import java.io.File;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.nio.file.Path;
 import java.util.HashMap;
 
 import javax.sound.midi.Patch;
 
 import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
 import com.google.zxing.MultiFormatWriter;
 import com.google.zxing.client.j2se.MatrixToImageWriter;
 import com.google.zxing.common.BitMatrix;
 import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
 
//���ɶ�ά��
public class CreateQRCode {
	 public static int count = 0;
	 /**
	  * ���ɶ�ά��ķ���
	  * @param str ��ά�������
	  * @param path  ��ά�뱣��ĵ�ַ
	  * @return
	  */
     public static String getPic(String str,String path) {
    	 final int width = 300;
         final int height = 300;
         final String format = "png";
         
         //�����ά��Ĳ���
         HashMap hints = new HashMap();
         hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
         hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
         //���ɶ�ά��
         try{
             //OutputStream stream = new OutputStreamWriter();
             BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, width, height, hints);
            count++;
             Path file = new File(path+count+"."+format).toPath();
             MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            //MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
         }catch(Exception e){
             e.printStackTrace();
         }
         return count+"."+format;
     }
 
 }