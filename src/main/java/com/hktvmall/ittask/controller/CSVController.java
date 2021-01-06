package com.hktvmall.ittask.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.hktvmall.ittask.entity.CSV;
import com.hktvmall.ittask.repository.CSVRepository;

import org.springframework.beans.factory.annotation.Autowired;

// import com.hktvmall.ittask.service.CSVService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

@Controller
public class CSVController {

	@Autowired
	private CSVRepository csvRepository;

	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		return "index";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		try {
			File covertFile = new File(file.getOriginalFilename());
			covertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(covertFile);
			fout.write(file.getBytes());
			fout.close();
			System.out.println(covertFile.getName());
			if (covertFile.getName().toLowerCase().contains(".csv")) {
				//
			} else {

				return "fail";
			}
			//
			try (Reader reader = Files.newBufferedReader(covertFile.toPath());
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
				for (CSVRecord csvRecord : csvParser) {
						// Accessing Values by Column Index
						String id = csvRecord.get(0);
						if (!(id.equals("Name"))){
							String name = csvRecord.get(1);
							Integer quantity = Integer.parseInt(csvRecord.get(2));
	
							System.out.println("Record No - " + csvRecord.getRecordNumber());
							System.out.println("id : " + id);
							System.out.println("name : " + name);
							System.out.println("quantity : " + quantity);
							System.out.println("---------------");
	
							CSV result = null;
							int int_TotNoOfRec = 0;
							try {
								result = csvRepository.findById(id);
								// System.out.println(result.getid());
								// System.out.println(result.getname());
								// System.out.println(result.getquantity());
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (!(result == null)) {
								int_TotNoOfRec = result.getquantity();
							}
	
							if (!(int_TotNoOfRec == 0)) {
								result.setId(id);
								result.setname(name);
								result.setquantity(int_TotNoOfRec + quantity);
								this.csvRepository.save(result);
								return "success";
							} else if (int_TotNoOfRec == 0) {
								CSV newresult = new CSV();
								newresult.setId(id);
								newresult.setname(name);
								newresult.setquantity(quantity);
								this.csvRepository.save(newresult);
								return "success";
							}
						}

					
				
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}

	}

}