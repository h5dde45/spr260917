package ru.dao.interfaces;

import ru.dao.objects.MP3;

import java.util.List;

public interface MP3Dao {
    void insert(MP3 mp3);

    void insert(List<MP3> mp3List);

    void delete(int id);

    void delete(MP3 mp3);

    MP3 getMP3ById(int i);

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);

    int getMP3Count();
}
