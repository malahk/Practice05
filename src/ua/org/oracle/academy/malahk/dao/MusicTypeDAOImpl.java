package ua.org.oracle.academy.malahk.dao;

import ua.org.oracle.academy.malahk.connectors.Connector;
import ua.org.oracle.academy.malahk.models.MusicType;
import ua.org.oracle.academy.malahk.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 08.07.2015.
 */
public class MusicTypeDAOImpl implements MusicTypeDAO {

    public static final String CREATE_MUSICTYPE = "insert music_type set genre = ?";
    public static final String GET_ALL = "select * from music_type";
    public static final String GET_BY_ID = "select * from music_type where id = ?";
    public static final String GET_BY_USER = "SELECT mt.* FROM music_type AS mt LEFT JOIN user_music_type AS umt" +
            " ON mt.id = umt.user_music_type_id WHERE umt.user_id = ?";
    public static final String UPDATE_MUSICTYPE = "update music_type set genre = ? where id = ?";
    public static final String DELETE_MUSICTYPE = "delete from music_type where id = ?";

    private static Connection connection;

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
            createMusicType.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<MusicType> getAll() {
        ArrayList<MusicType> musicList = new ArrayList<>();
        try {
            Statement getAll  = connection.createStatement();
            ResultSet allRS = getAll.executeQuery(GET_ALL);
            MusicType musicType;

            while (allRS.next()) {

                musicType = new MusicType();

                Integer id = allRS.getInt(1);
                String genre = allRS.getString(2);

                musicType.setId(id);
                musicType.setMusicGenre(genre);
                musicList.add(musicType);
            }
            getAll.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return musicList;
    }

    @Override
    public Set<MusicType> getByUser(User user)
    {
        Set<MusicType> musicList = new HashSet<>();
        try {
            PreparedStatement getByUser = connection.prepareStatement(GET_BY_USER);
            getByUser.setInt(1, user.getId());
            ResultSet getMusicTypeRS = getByUser.executeQuery();

            while (getMusicTypeRS.next()) {
                MusicType musicType = new MusicType();

                Integer id = getMusicTypeRS.getInt(1);
                String genre = getMusicTypeRS.getString(2);
                musicType.setId(id);
                musicType.setMusicGenre(genre);

                musicList.add(musicType);
            }
            getByUser.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return musicList;
    }

    @Override
    public MusicType getMusicType(Integer id)
    {
        MusicType musicType = new MusicType();
        try {

            PreparedStatement getById = connection.prepareStatement(GET_BY_ID);
            getById.setInt(1, id);

            ResultSet getMusicTypeRS = getById.executeQuery();
            while (getMusicTypeRS.next()) {
                String genre = getMusicTypeRS.getString(2);

                musicType.setId(id);
                musicType.setMusicGenre(genre);

            }
            getById.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return musicType;
    }

    @Override
    public boolean update(MusicType musicType) {
        boolean result = false;

        try {

            PreparedStatement updateMusicType = connection.prepareStatement(UPDATE_MUSICTYPE);
            updateMusicType.setString(1, musicType.getMusicGenre());
            updateMusicType.setInt(2, musicType.getId());

            result = updateMusicType.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(MusicType musicType)
    {
        boolean result = false;

        try {

            PreparedStatement deleteMusicType = connection.prepareStatement(DELETE_MUSICTYPE);
            deleteMusicType.setInt(1, musicType.getId());

            result = deleteMusicType.execute();
            deleteMusicType.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
