package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import enums.ImageCategory;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

	public static String generateImage(CommonsMultipartFile image, String id, ImageCategory category) throws IOException {
		String imagePath = null;

		String fileName = FileUtil.getRandomFileName(id);
		String extension = getFileExtension(image);
		String targetDir = FileUtil.getImageBasePath() + FileUtil.getImagePath(id, category);
		makeDirs(targetDir);
		String realPath = targetDir + fileName + extension;
		BufferedImage read = ImageIO.read(image.getInputStream());
		int width = read.getWidth();
		int height = read.getHeight();
		if (height > width) {
			height = width;
		}
		double radio = 1;
		int thumbnailHeight = 100;
		int thumbnailWidth = 100;
		switch (category) {
		case USER:
			thumbnailHeight = 100;
			thumbnailWidth = 100;
			radio=1;
			break;
		case COURSE:
			thumbnailHeight = 64;
			thumbnailWidth = 100;
			radio=0.64;
			break;
		}
		Thumbnails.of(image.getInputStream()).sourceRegion(Positions.CENTER, height, (int) (height*radio))
				.size(thumbnailWidth, thumbnailHeight)
				.watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File(basePath + "/watermark.png")), 0.5f)
				.toFile(realPath);
		imagePath = FileUtil.getImagePath(id, category) + fileName + extension;

		return imagePath;
	}

	private static void makeDirs(String targetDir) {
		// TODO Auto-generated method stub
		File file = new File(targetDir);
		if(!file.exists()) {
			file.mkdirs();
		}
	}

	private static String getFileExtension(CommonsMultipartFile image) {
		// TODO Auto-generated method stub
		String originalFilename = image.getOriginalFilename();
		return originalFilename.substring(originalFilename.lastIndexOf("."));
	}
}
