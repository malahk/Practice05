package ua.org.oracle.academy.malahk.dao;

import ua.org.oracle.academy.malahk.models.MusicType;

import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public interface MusicTypeDAO {

    boolean create(MusicType musicType);
    List<MusicType> getAll();
    MusicType getMusicType(Integer id);
    boolean update(MusicType musicType);
    boolean delete(MusicType musicType);
}
