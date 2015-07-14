package ua.org.oracle.academy.malahk.dao;

import ua.org.oracle.academy.malahk.connectors.Connector;
import ua.org.oracle.academy.malahk.models.MusicType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public class MusicTypeDAOImpl implements MusicTypeDAO {

    public static final String CREATE_MUSICTYPE = "insert music_type set genre = ?";
    public static final String GET_ALL = "select * from music_type";
    public static final String GET_BY_ID = "select * from music_type where id = ?";
    public static final String UPDATE_MUSICTYPE = "update music_type set set genre = ? where id = ?";
    public static final String DELETE_MUSICTYPE = "delete from music_type where id = ?";

    private static Connection connection;
    List<MusicType> musicList;

    public MusicTypeDAOImpl () {
        connection = Connector.getConn();
    }

    @Override
    public boolean create(MusicType musicType) {

        boolean result = false;

        try {

            PreparedStatement createMusicType = connection.prepareStatement(CREATE_MUSICTYPE, Statement.RETURN_GENERATED_KEYS);
            createMusicType.setString(1, musicType.getMusicGenre());

            result = createMusicType.execute();

            ResultSet createdMusicTypeRS = createMusicType.getGeneratedKeys();

            while(createdMusicTypeRS.next()) {
                musicType.setId(createdMusicTypeRS.getInt(1));

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<MusicType> getAll() {
        try {

            Statement getAll  = connection.createStatement();
            ResultSet allRS = getAll.executeQuery(GET_ALL);
            MusicType musicType;
            musicList = new ArrayList<>();

            while (allRS.next()) {

                musicType = new MusicType();

                Integer id = allRS.getInt(1);
                String genre = allRS.getString(2);

                musicType.setId(id);
                musicType.setMusicGenre(genre);
                musicList.add(musicType);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return musicList;
    }

    @Override
    public MusicType getMusicType(Integer id) {
        try {

            PreparedStatement getById = connection.prepareStatement(GET_BY_ID);
            getById.setInt(1, id);

            ResultSet getMusicTypeRS = getById.executeQuery();
            MusicType musicType = null;
            while (getMusicTypeRS.next()) {

                musicType = new MusicType();

                String genre = getMusicTypeRS.getString(2);

                musicType.setId(id);
                musicType.setMusicGenre(genre);

            }

            return musicType;


        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(MusicType musicType) {
        boolean result = false;

        try {

            PreparedStatement updateMusicType = connection.prepareStatement(UPDATE_MUSICTYPE);
            updateMusicType.setString(1, musicType.getMusicGenre());
            updateMusicType.setInt(2, musicType.getId());

            result = updateMusicType.execute();

            return result;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(MusicType musicType) {

        boolean result = false;

        try {

            PreparedStatement deleteMusicType = connection.prepareStatement(DELETE_MUSICTYPE);
            deleteMusicType.setInt(1, musicType.getId());

            result = deleteMusicType.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
