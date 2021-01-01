package com.marina.springboot.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.marina.springboot.Posts;
import com.marina.springboot.common.LogUtils;
import com.marina.springboot.repository.ConfirmRepository;

@Service
public class ConfirmService {
	
	@Autowired
	ConfirmRepository confirmRepository;
	
	@Autowired
	HttpSession session;

	public void doInsert(String title, String text, MultipartFile image, int author_id) throws IOException {
		
		LogUtils.info("Start method");
	    if (image != null) {
	    	LogUtils.info("imageのデータがありました");
	    }
	    
        // DBに格納する
	    
	    Posts posts = new Posts();
		try {
			createDirectory();
		    String fileName = savefile(image);
			posts.setTitle(title);
			posts.setText(text);
			posts.setAuthor_id(author_id);
			posts.setImage(fileName);
		} catch (MultipartException e) {
			e.printStackTrace();
		}
        confirmRepository.save(posts);	   
	}

	private String savefile(MultipartFile file) {
		 String filename = getUploadFileName();
	     Path uploadfile = Paths.get("/Users/ryuji/MarinaApp/src/main/resources/static/upload/" + filename);
	        try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
	        	byte[] bytes = file.getBytes();
	        	os.write(bytes);
	        } catch (IOException e) {
				e.printStackTrace();
			} 
			return filename;	
	}

	private String getUploadFileName() {
		 return  DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
                     .format(LocalDateTime.now()) + ".jpg";
	}

//	private String getExtension(String filename) {
//		 int dot = filename.lastIndexOf(".");
//	        if (dot > 0) {
//	          return filename.substring(0, dot).toLowerCase();
//	        }	
//	        return "";
//	}
	
	 private void createDirectory() {
         Path path = Paths.get("/Users/ryuji/MarinaApp/src/main/resources/static/upload");
         if (!Files.exists(path)) {
           try {
             Files.createDirectory(path);
           } catch (Exception e) {
             //エラー処理は省略
           }
         }
     }
}

