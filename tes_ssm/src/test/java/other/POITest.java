package other;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import entity.User;

public class POITest {
	
	@Test
	public void test() {
		readXls("F://用户信息表.xls");
	}
	
	@SuppressWarnings("resource")
	public void readXls(String file) {
		ArrayList<User> list = new ArrayList<User>();
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			//循环工作表
			for(int numSheet=0;numSheet<workbook.getNumberOfSheets();numSheet++) {
				HSSFSheet sheet = workbook.getSheetAt(numSheet);
				if(sheet==null) {
					continue;
				}
				
				//循环行
				for(int rowNum=1;rowNum<sheet.getLastRowNum();rowNum++) {
					HSSFRow row = sheet.getRow(rowNum);
					if(row==null) {
						continue;
					}
					//循环列
					User user = new User();
					for(int cellNum=0;cellNum<row.getLastCellNum();cellNum++) {
						HSSFCell cell = row.getCell(cellNum);
						if(cell==null) {
							continue;
						}
						//System.out.print(" | "+getString(cell));
						switch (cellNum) {
						case 0:
							user.setLoginName(getString(cell));
							break;
						case 1:
							user.setLoginType(getString(cell));
							break;
						case 2:
							user.setNickName(getString(cell));
							break;
						case 3:
							user.setPassword(getString(cell));
							break;
						case 4:
							user.setAge(getInt(cell));
							break;
						case 5:
							user.setType(getInt(cell));
							break;
						case 6:
							user.setSex(getString(cell));
							break;
						case 7:
							user.setScore(getInt(cell));
							break;
						case 8:
							user.setIsLock(getString(cell));
							break;
						case 9:
							user.setIntroduction(getString(cell));
							break;
						}
					}
					System.out.println();
					list.add(user);
				}
			}
			System.out.println(list);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int getInt(HSSFCell cell) {
		System.out.println((int)cell.getNumericCellValue());
		return (int)cell.getNumericCellValue(); 
	}
	
	private String getString(HSSFCell cell) {
		if(cell.getCellType()==cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}else {
		return String.valueOf(cell.getStringCellValue());
		}
	}
	
	
	@Test
	public void test1() {
		System.out.println((int)55.0);
	}
	

}
