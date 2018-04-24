package com.timetable.core.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.timetable.core.constraint.Constraint;
import com.timetable.core.constraint.TimeConstraint;
import com.timetable.core.main.Data;

public class Model {

	public void saveToFile(File file) {
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Constraint.getInstance());
			oos.writeObject(Data.getInstance());
			oos.writeObject(TimeConstraint.getInstance());
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readFromFile(File file) {
		
		try {
			
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Constraint.setInstance((Constraint)ois.readObject());
			Data.setInstance((Data)ois.readObject());
			TimeConstraint.setInstance((TimeConstraint)ois.readObject());
			
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
