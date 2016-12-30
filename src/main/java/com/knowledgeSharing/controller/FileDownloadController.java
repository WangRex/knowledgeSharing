package com.knowledgeSharing.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knowledgeSharing.common.JXLUtil;

@Controller
@RequestMapping("/download")
public class FileDownloadController {
	
	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;
			
	/**
	 * Method for handling file download request from client
	 */
	@RequestMapping(value = "/forExportToExcel", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "fileName", required = true, defaultValue = "") String fileName) throws IOException {

		// get absolute path of the application
		ServletContext context = request.getServletContext();
		String fullFileName = "";
		fullFileName = JXLUtil.class.getResource("/").toString() + "downloads/" + fileName;

		// construct the complete absolute path of the file
		String fullPath = fullFileName.substring(6);
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);
		
		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

	}
}