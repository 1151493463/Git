package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import entity.Role;
import entity.User;

public class ExcelUtill {
	
	public static List<User> parseUserExcel(InputStream hw) {
		ArrayList<User> userList = new ArrayList<User>();
		
		try {
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(hw);
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
					//user.setId(UUID.randomUUID().toString());
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
					//System.out.println();
					if(user.getLoginName()!=null && !"".equals(user.getLoginName())) {
						userList.add(user);
					}
					
				}
			}
			//System.out.println(userList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}
	
	private static int getInt(HSSFCell cell) {
		
		return (int)cell.getNumericCellValue(); 
	}
	
	private static String getString(HSSFCell cell) {
		// TODO Auto-generated method stub
		if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}else {
		return String.valueOf(cell.getStringCellValue());
		}
		}
	
	public static HSSFWorkbook generatetUserToExcel(List<User> users) {
		HSSFWorkbook hw = new HSSFWorkbook();
		Sheet sheet = hw.createSheet("用户表");
		sheet.setColumnWidth(0, 6000);
		HSSFCellStyle cellStyle = hw.createCellStyle();
		HSSFCellStyle cellStyle2 = hw.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor((short) 13);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = hw.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		cellStyle.setFont(font);
		String[] title = new String[] {"ID","登录名","登陆类型","昵称","密码","年龄","用户类型","性别","头像URL","积分","账户状态","注册日期","用户角色","用户描述"};
		Row rowTitle = sheet.createRow(0);
		Cell tId = rowTitle.createCell(0);
		tId.setCellStyle(cellStyle);
		tId.setCellValue(title[0]);
		sheet.setColumnWidth(0, 10000);
		for(int i=1;i<title.length;i++) {
			Cell cell = rowTitle.createCell(i);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(title[i]);
			sheet.setColumnWidth(i, 8000);
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<users.size();i++){
			int j=i+1;
			Row dataRow = sheet.createRow(j);
			
			Cell id = dataRow.createCell(0);
			id.setCellStyle(cellStyle2);
			id.setCellValue(users.get(i).getId());
			
			Cell loginName = dataRow.createCell(1);
			loginName.setCellStyle(cellStyle2);
			loginName.setCellValue(users.get(i).getLoginName());
			
			Cell loginType = dataRow.createCell(2);
			loginType.setCellStyle(cellStyle2);
			loginType.setCellValue(users.get(i).getLoginType());
			
			Cell nickName = dataRow.createCell(3);
			nickName.setCellStyle(cellStyle2);
			nickName.setCellValue(users.get(i).getNickName());
			
			Cell password = dataRow.createCell(4);
			password.setCellStyle(cellStyle2);
			password.setCellValue(users.get(i).getPassword());
			
			Cell age = dataRow.createCell(5);
			age.setCellStyle(cellStyle2);
			age.setCellValue(users.get(i).getAge());
			
			Cell type = dataRow.createCell(6);
			type.setCellStyle(cellStyle2);
			type.setCellValue(getUserType(users.get(i).getType()));
			
			Cell sex = dataRow.createCell(7);
			sex.setCellStyle(cellStyle2);
			sex.setCellValue(users.get(i).getSex());
			
			Cell head = dataRow.createCell(8);
			head.setCellStyle(cellStyle2);
			head.setCellValue(users.get(i).getHead());
			
			Cell score = dataRow.createCell(9);
			score.setCellStyle(cellStyle2);
			score.setCellValue(users.get(i).getScore());
			
			Cell isLock = dataRow.createCell(10);
			isLock.setCellStyle(cellStyle2);
			isLock.setCellValue(users.get(i).getIsLock());
			
			Cell date = dataRow.createCell(11);
			date.setCellStyle(cellStyle2);
			date.setCellValue(format.format(users.get(i).getRegDate()));
			
			Cell role = dataRow.createCell(12);
			role.setCellStyle(cellStyle2);
			if(users.get(i).getRoles()!=null && users.get(i).getRoles().size()>0){
				List<Role> dataRoles = users.get(i).getRoles();
				StringBuilder sb = new StringBuilder();
				for(Role r : dataRoles){
					sb.append(r.getName()+",");
				}
				String str = sb.toString();
				str = str.substring(0, str.length()-1);
				role.setCellValue(str);
			}else{
				role.setCellValue("无角色");
			}
			Cell intr = dataRow.createCell(13);
			intr.setCellStyle(cellStyle2);
			intr.setCellValue(users.get(i).getIntroduction());
		}
		return hw;
	}
	
	private static String getUserType(int type) {
		switch (type) {
		case 0:
			return "学生";
		case 1:
			return "教师";
		case 2:
			return "管理员";
		default:
			return "游客";
		}
	}
}
