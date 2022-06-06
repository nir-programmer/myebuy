package org.nir.myebuy.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException
	{
		//Get the Path 
		Path uploadPath = Paths.get(uploadDir); 
		
		//If path does not exists( the directory does not exists) - create a new directory 
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
			
		}
		
		//use try with resources to get the  InsputStream from the multipartFile 
		try(InputStream inputStream = multipartFile.getInputStream())
		{
			//Createt he Path of the file - relative to the uploadDirecoty 
			Path filePath = uploadPath.resolve(fileName); 
			
			//Copy the file - will replace the existing files with the same names
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException exc)
		{
			throw new IOException("Could not save file: " + fileName, exc);
			
		}
	}
	
	/*
	 * This method is for keeping only the last updated image - and remove the others -
	 *  without clearing the updated user folder - all images are kept in this folder on the OS!
	 */
	public static void cleanDir(String dir)
	{
		Path dirPath = Paths.get(dir); 
		
		
		try {
			Files.list(dirPath).forEach(file -> {
				if(!Files.isDirectory(file))
				{
					try {
						Files.delete(file);
					}
					catch(IOException ex)
					{
						System.err.println("Could not delete file: " + file);
					}
				}
			});
			
		}
		catch(IOException ex)
		{
			System.err.println("Could not list directory: " + dirPath);
		}
	}
	
	
	
	

}
