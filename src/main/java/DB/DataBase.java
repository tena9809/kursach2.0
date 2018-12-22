package DB;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DataBase implements DataBaseInt {

    String tableName;

    public DataBase(String tableName) {
        this.tableName = tableName;
    }

    public String[] select(String name, String value) {
        LinkedList<String> linkedList = new LinkedList<String>();
        try {
            FileInputStream fileInputStream = new FileInputStream(tableName);
            Scanner scanner = new Scanner(fileInputStream);
            String meta = scanner.nextLine();
            String[] arr = meta.split(";");
            int column = 0;
            for(int  i = 0; i < arr.length; i++){
                if(name.equals(arr[i])){
                    column = i;
                }
            }

            while(scanner.hasNextLine()){
                String string = scanner.nextLine();
                String[] array = string.split(";");
                if(array.length > 0 && array[column].equals(value)){
                    linkedList.add(string);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] m = new String[linkedList.size()];
        return linkedList.toArray(m);
    }

    public boolean delete(String name, String value) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String meta = null;
        //FileInputStream fileInputStream;
        try(FileInputStream fileInputStream = new FileInputStream(tableName)){
            Scanner scanner = new Scanner(fileInputStream);
            meta = scanner.nextLine();
            String[] arr = meta.split(";");
            int column = 0;
            for(int  i = 0; i < arr.length; i++){
                if(name.equals(arr[i])){
                    column = i;
                }
            }
            while(scanner.hasNextLine()){
                String string = scanner.nextLine();
                String[] array = string.split(";");
                if(!(array.length > 0 && array[column].equals(value))){
                    arrayList.add(string);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(tableName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileOutputStream.write((meta + '\n').getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < arrayList.size(); i++){
            try {
                fileOutputStream.write((arrayList.get(i) + '\n').getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList.size() > 0;
    }

    public void insert(String[] str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tableName, true);
            for (int i = 0; i < str.length ; i++) {
                fileOutputStream.write((str[i] + ";").getBytes());
            }
            fileOutputStream.write('\n');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void create(String[] meta) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tableName);
            for(int i = 0; i < meta.length; i++) {
                fileOutputStream.write((meta[i] + ";").getBytes());
            }
            fileOutputStream.write('\n');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean check(){
        File file = new File(tableName);
        return file.exists();
    }

    public String[] selectAll(){
        LinkedList<String> linkedList = new LinkedList<String>();
        try {
            FileInputStream fileInputStream = new FileInputStream(tableName);
            Scanner scanner = new Scanner(fileInputStream);
            String meta = scanner.nextLine();

            while(scanner.hasNextLine()){
                String string = scanner.nextLine();
                linkedList.add(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] m = new String[linkedList.size()];
        return linkedList.toArray(m);
    }
}
