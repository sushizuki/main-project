package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class Util extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void UploadImage(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException{
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload();

		// Parse the request
		FileItemIterator iter = upload.getItemIterator(request);
		while (iter.hasNext()) {
		    FileItemStream item = iter.next();
		    String name = item.getFieldName();
		    InputStream stream = item.openStream();
		    if (item.isFormField()) {
		        System.out.println("Form field " + name + " with value "
		            + Streams.asString(stream) + " detected.");
		    } else {
		        System.out.println("File field " + name + " with file name "
		            + item.getName() + " detected.");
		        
		        // Process the input stream
		        
		    }
		}
		
	}
}
