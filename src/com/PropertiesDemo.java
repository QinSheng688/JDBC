package com;

import java.io.*;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties properties=new Properties();
        InputStream resourceAsStream = PropertiesDemo.class.getClassLoader().getResourceAsStream("jdbc.properties");
        //properties.load(new FileReader("D:\\study\\JDBC\\src\\resources\\jdbc.properties"));
        properties.load(resourceAsStream);
        System.out.println(properties.get("jdbc.driver"));
    }

}
