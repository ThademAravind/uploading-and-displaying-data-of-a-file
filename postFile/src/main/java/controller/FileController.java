package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

@MultipartConfig
@Controller
public class FileController {

	@RequestMapping(value = "/load")
	public String display() {
		return "upload";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException, ServletException {

		String destination = "home/mphs/upload/" + file.getOriginalFilename();
		File myfile = new File(destination);
		file.transferTo(myfile);
		System.out.println("{" + file.getOriginalFilename() + "} File uploaded Successfully");

		FileInputStream fis = new FileInputStream(new File(destination));

		HSSFWorkbook wb = new HSSFWorkbook(fis);

		HSSFSheet sheet = wb.getSheetAt(0);

		for (Row row : sheet) {
			for (Cell cell : row) {
				switch (cell.getCellType()) {
				case STRING:
					System.out.printf("%-30s",cell.getStringCellValue());
					break;
				case NUMERIC:
					System.out.printf("%5.1f",cell.getNumericCellValue());
					break;
				default:
					break;
				}
			}
			System.out.println();
		}

	}

}
