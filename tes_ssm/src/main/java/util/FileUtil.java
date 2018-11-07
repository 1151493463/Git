package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import enums.ImageCategory;

public class FileUtil {
	private static String SEPARATOR = System.getProperty("file.separator");
	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String getImageBasePath() {
		String os = System.getProperty("os.name");
		String basePath = null;
		if(os.toLowerCase().startsWith("win")) {
			basePath = "D:/project/tes_ssm/resources";
		}else {
			basePath = "/home/project/tes_ssm/resources";
		}
		basePath = basePath.replaceAll("//", SEPARATOR);
		return basePath;
	}
	
	public static String getImagePath(String id,ImageCategory category) {
		String userImagePath = "/upload/image/"+category.getName()+"/"+id+"/";
		return userImagePath.replaceAll("//", SEPARATOR);
	}
	
	public static void deleteFile(String path,String pattern) {
		File file = new File(path);
		if (file.exists()) {
			File[] listFiles = file.listFiles();
			for(File f:listFiles) {
				if(f.exists()) {
					if(pattern==null) {
						f.delete();
					}else if(f.getName().endsWith(pattern)) {
						f.delete();
					}
				}
			}	
		}
	}
	
	public static String getRandomFileName(String id) {
		return id + format.format(new Date());
	}
	
	public static void markerFile(String path) {
		File file = new File(path);
		if(file.exists()) {
			File[] listFiles = file.listFiles();
			for(File oldFile:listFiles) {
				if(oldFile.exists()) {
					File newFile = new File(path+oldFile.getName()+".bak");
					oldFile.renameTo(newFile);
				}
			}
		}
	}
	
	public static void recoverFile(String path) {
		File file = new File(path);
		if(file.exists()) {
			File[] listFiles = file.listFiles();
			for(File oldFile:listFiles) {
				if(oldFile.exists()) {
					File newFile = new File(oldFile.getName().substring(0,oldFile.getName().lastIndexOf(".bak")));
					oldFile.renameTo(newFile);
				}
			}
		}
	}

}
