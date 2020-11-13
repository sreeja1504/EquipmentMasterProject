package com.inkathon.equipmentmaster.controller;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inkathon.equipmentmaster.config.ResponseDto;
import com.inkathon.equipmentmaster.dtos.EquipmentStageDto;
import com.inkathon.equipmentmaster.service.EquipmentStageService;
import com.inkathon.equipmentmaster.util.HttpStatusCodes;
@RestController
@RequestMapping(value = "/equipmentStage")
public class EquipmentStageController {

	@Autowired
	EquipmentStageService equipmentStageService;

	@GetMapping("/get")
	public String get1() {
		return "Welcome Msg from EquipmentStageController";
	}
	
	@GetMapping(value = "/mail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto sendMail(){
		return equipmentStageService.sendMail();
	}

	@GetMapping("/exportStageTableData")
	public void getDownloadStagged(HttpServletResponse response) throws IOException {
	    FileInputStream fis = new FileInputStream(new File("StageTableData.xls"));
	    response.addHeader("Content-disposition", "attachment;filename=StageTableData.xls");
	    response.setContentType("application/octet-stream");
	    IOUtils.copy(fis, response.getOutputStream());
	    response.flushBuffer();
	}

	
   //create or update in stage
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto saveEquipmentStage(@RequestBody EquipmentStageDto dto) {
		return equipmentStageService.saveEquipmentStage(dto);
	}
	
	//get all records in stage
	@GetMapping(value = "/getAllEquipmentStages/{pageNumber}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto getEquipmentStage(@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
		return equipmentStageService.getEquipmentStageList(pageNumber,pageSize);

	}
	
	//get records in stage which are not in master
	@GetMapping(value = "/viewEquipmentStageNotInMaster/{pageNumber}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto viewEquipmentStageListNotInMaster(@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
		return equipmentStageService.viewEquipmentStageListNotInMaster(pageNumber,pageSize);

	}
	
	//read all records in master with respective of flag as true 

	@GetMapping(value = "/viewEquipmentMaster/{pageNumber}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto viewEquipmentMasterList(@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
		return equipmentStageService.viewEquipmentMasterList(pageNumber,pageSize);

	}


/*	@PostMapping(value="/filterEquipmentStage/{pageNumber}/{pageSize}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto filterEquipmentStage(@RequestBody EquipmentStageDto equipmentStageDto,@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
		return equipmentStageService.filterEquipmentStage(equipmentStageDto,pageNumber,pageSize);

	}
	*/
	
	@PostMapping(value="/filterEquipmentStage/{pageNumber}/{pageSize}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto filterEquipmentStage(@RequestBody EquipmentStageDto equipmentStageDto,@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
		Integer x=1;
		return equipmentStageService.filterEquipmentStage(equipmentStageDto,pageNumber,pageSize,x);

	}
	@PostMapping(value="/filterEquipmentStageNotInMaster/{pageNumber}/{pageSize}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto filterEquipmentStageNotInMaster(@RequestBody EquipmentStageDto equipmentStageDto,@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
		Integer x=2;
		return equipmentStageService.filterEquipmentStage(equipmentStageDto,pageNumber,pageSize,x);

	}


}
