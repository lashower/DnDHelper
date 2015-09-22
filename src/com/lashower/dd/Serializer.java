/**
 * 
 */
package com.lashower.dd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.lashower.dd.dice.Dice;

/**
 * @author lashower
 *
 */
public class Serializer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Serializer serializer = new Serializer();
		serializer.serializeDice(new Dice("test",20));
//		File f = new File("c:\\dice.ser");
//		f.renameTo(new File("c:\\dic.txt"));
//		System.out.println(f.exists());
	}

	public void serializeDice(Dice d){
	   
	   try{
			FileOutputStream fout = new FileOutputStream("c:\\dice.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(d);
			oos.close();
			System.out.println("Done");
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
