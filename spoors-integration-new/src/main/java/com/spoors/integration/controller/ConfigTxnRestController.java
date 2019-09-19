package com.spoors.integration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigTxnRestController {

	@RequestMapping(value="/transactions/submitURL")
	public ResponseEntity<String> hitTxnURLForJson(){
		return new ResponseEntity<String>("{\"response\":\"SUCCESS\"}",HttpStatus.OK);
	}
	
	@RequestMapping(value="/transactions/submitURL/XML")
	public ResponseEntity<String> hitTxnURLForXml(){
		return new ResponseEntity<String>("<RootTag><key>SUCCESS</key></RootTag>",HttpStatus.OK);
	}
	
}
