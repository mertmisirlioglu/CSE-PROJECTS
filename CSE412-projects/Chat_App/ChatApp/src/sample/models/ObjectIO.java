package sample.models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIO<T> {




    public  void WriteObjectToFile(T serObj,String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public  T ReadObjectFromFile(String filepath) {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            T object =  (T)objectIn.readObject();
            objectIn.close();
            System.out.println("The Object  was succesfully written to a file");
            return object;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

