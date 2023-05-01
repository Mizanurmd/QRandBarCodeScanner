package com.Spring.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Spring.qrcode.QRCodeGenerator;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class QRController {
	
	private static final String QR_CODE_IMAGE_PATH="./src/main/resources/QRCode.png";
	
	@GetMapping(value="/generateAndDownlodQRCode/{codeText}/{width}/{height}")
	public void download(
			@PathVariable("codeText")String codeText,
			@PathVariable("width") Integer width,
			@PathVariable("height") Integer height) throws WriterException, IOException {
		
		QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
	}

	@GetMapping(value="/generateQRCode/{codeText}/{width}/{height}")
	public ResponseEntity<byte[]> generateQRCode(
			@PathVariable("codeText") String codeText,
			@PathVariable("width") Integer width,
			@PathVariable("height") Integer height
			) throws WriterException, IOException{
				return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
						
		
	}
}
