package com.xiaoyuan.common.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.springframework.web.multipart.MultipartFile;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.oss.util.OSSUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.xiaoyuan.common.content.FinVal;
import com.xiaoyuan.common.exception.UploadException;
import com.xiaoyuan.common.log.LogTool;


public class ImageUtil {  
	private static LogTool log = LogTool.getInstance(ImageUtil.class);
	 public static String uploadFile(MultipartFile file,String filename) throws Exception{		
		 	InputStream is= file.getInputStream();               
		 	FileUtil fileUtil = new FileUtil();
	   		fileUtil.saveFileToTemp(is,filename,Global.COURSE_FILE_PATH); 
	   		return fileUtil.getLastFileName();
		}
		
		
		public  static void doc2Pdf(String docPath, String pdfPath) throws ConnectException {
	       File inputFile = new File(docPath);
	       File outputFile = new File(pdfPath);
	       OpenOfficeConnection connection = new SocketOpenOfficeConnection("139.196.241.246",8100);
	       connection.connect();
	       DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
	       DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();   
	       DocumentFormat txt = formatReg.getFormatByFileExtension("odt") ;
	       DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf") ;
	       converter.convert(inputFile, txt, outputFile, pdf);
	       connection.disconnect();
	   }

		    
		public static JSONArray doc2Imags(String docPath, String imgDirPath,Long courseId) throws Exception {
	   	String pdfPath =String.format("%s%s.pdf",  FilenameUtils.getFullPath(docPath), FilenameUtils.getBaseName(docPath));    	
			log.debug("将doc转成pdf");
	   		doc2Pdf(docPath, pdfPath);
	   		log.debug("将pdf转成img");
			JSONArray imgList = pdf2Imgs(pdfPath, imgDirPath,courseId);
			File pdf =  new File(pdfPath);
			if(pdf.isFile()){
				pdf.delete();
			}
			return imgList;
	   }
	   public static JSONArray pdf2Imgs(String pdfPath, String imgDirPath,Long courseId) throws Exception {
	       Document document = new Document();
	       document.setFile(pdfPath);
	       String FileName = pdfPath.substring(pdfPath.lastIndexOf("/")+1, pdfPath.lastIndexOf("."));
	       
	       float scale = 2f;//放大倍数
	       float rotation = 0f;//旋转角度
	    
	       int pageNum = document.getNumberOfPages();
	       if(pageNum > FinVal.MaxNumberOfPage){
	    	   throw new UploadException("文档页码数过长，本系统现在仅支持"+FinVal.MaxNumberOfPage+"页的文档上传！");
	       }
	     
	       File imgDir = new File(imgDirPath);
	       if (!imgDir.exists()) {
	           imgDir.mkdirs();
	       }
	       JSONArray jsonArray = new JSONArray();
	       for (int i = 0; i < pageNum; i++) {
	           BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
	                   Page.BOUNDARY_CROPBOX, rotation, scale);
	           RenderedImage rendImage = image;                 
	           try {
	        	   String filePath = FileName+"_"+(courseId)+"_"+(i+1) + ".png";
	           	               
	               File file = new File(Global.COURSE_IMG_PATH+filePath);
	               ImageIO.write(rendImage, "png", file);
	               log.debug("第"+(i+1)+"张ppt转图片成功");
	               log.debug("开始压缩第"+(i+1)+"张图片");
	               ImageUtil.transferImg(file, imgDirPath,(FileName+"_"+(courseId)+"_"+(i+1)),"png",800, 100,true); 
	               log.debug("第"+(i+1)+"压缩成功");
	               log.debug("将第"+(i+1)+"张图片上传到阿里云服务器");
	               
	               String tempPath = imgDirPath+(FileName+"_"+(courseId)+"_"+(i+1))+".png";
	               String ossPath = OSSUtil.uploadFile(tempPath, "pptImgs");//山川之后会删除tempPath
	               log.debug("第"+(i+1)+"张图片上传到阿里云服务器成功");
	               JSONObject jsonObject = new JSONObject();
	               jsonObject.put("imgIndex", (i+1));
	               jsonObject.put("imgPath", ossPath);
	               jsonArray.add(jsonObject);
	               
	           } catch (IOException e) {
	               e.printStackTrace();
	               return null;
	           }
	           image.flush();
	       }
	       log.debug("删除pdf文件");
	       File pdf = new File(pdfPath);
	       if(pdf.isFile()){
	    	   pdf.delete();
	       }
	       document.dispose();            
	       return jsonArray;
	   }
	
    /**
     * 压缩图片
     * proportion 是否等比压缩
     * */
	public static boolean transferImg(File fromFile, String toFilePath,
			String toFileName, String suffix, int width, int height,boolean proportion) throws Exception {
		
		// fileExtNmae是图片的格式 gif JPG 或png
		// String fileExtNmae="";
		
		if (!fromFile.isFile())
			throw new Exception(fromFile
					+ " is not image file error in CreateThumbnail!");
		
		File directory=new File(toFilePath);
		if(!directory.exists()){
			
			if(!directory.mkdirs()){
				throw new Exception("create path "+toFilePath+ " error ,please check!!!");
				
			}
		}
		Image  src = javax.imageio.ImageIO.read(fromFile);
		   int newWidth;
           int newHeight;
        // 判断是否是等比缩放
        if (proportion == true) {
         // 为等比缩放计算输出的图片宽度及高度
         double rate1 = ((double) src.getWidth(null)) / (double) width + 0.1;
         double rate2 = ((double) src.getHeight(null)) / (double) height + 0.1;
         // 根据缩放比率大的进行缩放控制
         double rate = rate1 < rate2 ? rate1 : rate2;
         newWidth = (int) (((double) src.getWidth(null)) / rate);
         newHeight = (int) (((double) src.getHeight(null)) / rate);
        } else {
         newWidth = width; // 输出的图片宽度
         newHeight = height; // 输出的图片高度
        }
        if("png".equalsIgnoreCase(suffix)){
			resizePNG(fromFile, toFilePath, toFileName, suffix, newWidth, newHeight, proportion);
		}else{
		BufferedImage tag = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);      
        //tag.getGraphics().drawImage(src,0,0,new_w,new_h,null); //绘制缩小后的图
         tag.getGraphics().drawImage(src.getScaledInstance(newWidth, newHeight,  Image.SCALE_SMOOTH), 0,0,null);
         FileOutputStream  newimage=new FileOutputStream(new File(toFilePath + toFileName + "." + suffix)); //输出到文件流 
         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
         JPEGEncodeParam jep=JPEGCodec.getDefaultJPEGEncodeParam(tag); 
          /* 压缩质量 */ 
         jep.setQuality((float) 1.0, true); 
         encoder.encode(tag, jep); 
         //encoder.encode(tag); //近JPEG编码 
         newimage.close(); 
		}
		return true;
	}
	  
        // TODO Auto-generated constructor stub  
     public static void resizePNG(File fromFile, String toFilePath,String toFileName,String toFileSuffix,  int width, int height,boolean proportion) {
              try {  
                

                  BufferedImage bi2 = ImageIO.read(fromFile);  
            
                  BufferedImage to = new BufferedImage(width, height,  

                          BufferedImage.TYPE_INT_RGB);  

                  Graphics2D g2d = to.createGraphics();  

                  to = g2d.getDeviceConfiguration().createCompatibleImage(width,height,  

                          Transparency.TRANSLUCENT);  

                  g2d.dispose();  

                  g2d = to.createGraphics();  

                  Image from = bi2.getScaledInstance(width, height, bi2.SCALE_AREA_AVERAGING);  
                  g2d.drawImage(from, 0, 0, null);
                  g2d.dispose();  

                  ImageIO.write(to, toFileSuffix, new File(toFilePath+File.separator+toFileName+"."+toFileSuffix));  

              } catch (IOException e) {  

                  e.printStackTrace();  
                   
              }  

          }  

          public static void main(String[] args) throws Exception {  

              System.out.println("Start");  

             // transferImg(new File("E:\\xxxx\\test.png"), "E:\\xxxx\\abc","a","png",800, 100,true);  
              doc2Imags(Global.COURSE_FILE_PATH+"aa.pptx", Global.COURSE_IMG_PATH,null);
              System.out.println("OK");  

          } 
}

