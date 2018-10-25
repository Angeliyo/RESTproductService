package com.example.springboot.rest.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadController.class);
	
   @RequestMapping(value = "/download/{name}/{ext}", method = RequestMethod.GET) 
   public ResponseEntity<Object> downloadFile(@PathVariable("name") String name, @PathVariable("ext") String ext) throws IOException  {
	   LOGGER.info("name: "+name);
	   LOGGER.info("ext: "+ext);
	   String filename = "C:/tmp/"+name+"."+ext;
	   LOGGER.info("RUTA: "+filename);
	   File file = new File(filename);
	   InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	   HttpHeaders headers = new HttpHeaders();
      
      headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
      headers.add("Pragma", "no-cache");
      headers.add("Expires", "0");
      
      ResponseEntity<Object> 
      responseEntity = ResponseEntity.ok().headers(headers).contentLength(
         file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
      
      return responseEntity;
   }
}
