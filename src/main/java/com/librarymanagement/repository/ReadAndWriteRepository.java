package com.librarymanagement.repository;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

@Repository
public class ReadAndWriteRepository {
	 public  JSONObject readJsonFiel(String path) {
			String pathoffile=path;
			JSONObject jobj=null;
			JSONParser parse=new JSONParser();
			try(FileReader read=new FileReader(path)){
				Object obj=parse.parse(read);
				JSONObject jsonobj=(JSONObject) obj;
//				System.out.println(array);
				jobj=jsonobj;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			return jobj;

	}
		public boolean writejsonfile(String path,JSONObject jarray) throws IOException {
			BufferedWriter writer=new BufferedWriter(new FileWriter(path));
			writer.write(jarray.toJSONString());
			
			writer.flush();
			writer.close();
			return true;

	}
}
