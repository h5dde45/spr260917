package ru.dao.interfaces;

import ru.dao.objects.Author;
import ru.dao.objects.MP3;

import java.util.List;
import java.util.Map;

public interface MP3Dao {

    int insertMP3(MP3 mp3);

//    @Transactional
    int insertAuthor(Author author);

    int insertSimple(MP3 mp3);

    int insertBatchList(List<MP3> mp3List);

    int insertList(List<MP3> mp3List);

    Map<String,Integer> getStat();

    void delete(int id);

    void delete(MP3 mp3);

    MP3 getMP3ById(int i);

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);

    int getMP3Count();
}
