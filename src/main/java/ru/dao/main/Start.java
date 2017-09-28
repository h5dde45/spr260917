package ru.dao.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dao.interfaces.MP3Dao;
import ru.dao.objects.Author;
import ru.dao.objects.MP3;

import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) {

        MP3 mp3 = new MP3();
        mp3.setName("n4");
        Author author=new Author();
        author.setName("a4");
        mp3.setAuthor(author);

        MP3 mp3_2 = new MP3();
        mp3_2.setName("n6");
        Author author2=new Author();
        author2.setName("a6");
        mp3_2.setAuthor(author2);

        List<MP3> list = new ArrayList<>();
        list.add(mp3);
        list.add(mp3_2);

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MP3Dao sqLiteDao = (MP3Dao) context.getBean("sqLiteDao");
        System.out.println(sqLiteDao.insertMP3(mp3));
//        System.out.println(sqLiteDao.insertSimple(mp3));
//        System.out.println(sqLiteDao.insertBatchList(list));
//        System.out.println(sqLiteDao.getMP3ById(3));
//        System.out.println(sqLiteDao.getMP3ListByName("2"));
//        System.out.println(sqLiteDao.getMP3ListByAuthor("2"));
//        System.out.println(sqLiteDao.getMP3Count());
//        System.out.println(sqLiteDao.getStat());

    }
}
