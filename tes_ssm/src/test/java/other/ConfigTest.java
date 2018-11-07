package other;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dto.Page;
import exception.MissingTypMatch;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import util.ActiveCodeUtil;
import util.ParamVerify;

public class ConfigTest {
	private String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:conf/spring-mybatis.xml","classpath:conf/spring.xml","classpath:conf/spring-mvc.xml"}) ;
		Page bean = context.getBean("page",Page.class);
		System.out.println(bean.getPageSize());
	}
	
	@Test
	public void test1() {
		try {
			//System.err.println(ParamVerify.getLoginType("14752@qq.com"));
			System.err.println(ParamVerify.getLoginType("13409317484"));
		} catch (MissingTypMatch e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test2() {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		File file = new File("F:\\1.jpg");
		BufferedImage read = null;
		try {
			read = ImageIO.read(file);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		int height = read.getHeight();
		int width = read.getWidth();
		if(height>width) {
			height=width;
		}
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Thumbnails.of(inputStream).sourceRegion(Positions.CENTER,height,height).size(100, 100).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.png")),0.5f).toFile("F:/2.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		/*String url = "F://1";
        boolean bol = FileUtils.deleteQuietly(new File(url));
        System.out.println(bol);*/
		
		File file = new File("F://1");
		if (file.exists()) {
			if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			file.delete();
		}
	}
	@Test
	public void test4() {
		String str = "fdsfdsfs.png.bak";
		String substring = str.substring(0, str.lastIndexOf(".bak"));
		System.out.println(substring);
	}
	
	@Test
	public void test5() {
		int i = testTry();
		System.out.println(i);
	}

	private int testTry() {
		try {
			/*int i = 1/0;*/
			return 1;
		}catch (Exception e) {
			// TODO: handle exception
			return -1;
		}finally {
			return 0;
		}
	}
	
	@Test
	public void test6() {
		try {
			/*String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		File file = new File(basePath+"/sensitiveWord.txt");
		if(!file.exists()) {
			file.mkdirs();
		}*/
		@SuppressWarnings("resource")
		FileWriter fileWriter = new FileWriter(basePath+"/sensitiveWord.txt");
		fileWriter.write("活动方式");
		fileWriter.flush();
		fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Test
	public void test7() {
		String generateActiveCode = ActiveCodeUtil.generateActiveCode();
		System.out.println(generateActiveCode);
	}
}
