package ru.dao.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dao.interfaces.MP3Dao;
import ru.dao.objects.MP3;

import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        MP3 mp3 = new MP3();
        mp3.setName("n5");
        mp3.setAuthor("a7");
        MP3 mp3_2 = new MP3();
        mp3_2.setName("n5");
        mp3_2.setAuthor("a6");
        List<MP3> list = new ArrayList<>();
        list.add(mp3);
        list.add(mp3_2);

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MP3Dao sqLiteDao = (MP3Dao) context.getBean("sqLiteDao");
//        sqLiteDao.insert(mp3);
//        sqLiteDao.insert(list);
//        System.out.println(sqLiteDao.getMP3ById(7));
        System.out.println(sqLiteDao.getMP3ListByName("n5"));
        System.out.println(sqLiteDao.getMP3ListByAuthor("6"));
        System.out.println(sqLiteDao.getMP3Count());


    }
}
