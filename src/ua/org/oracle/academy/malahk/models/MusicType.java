package ua.org.oracle.academy.malahk.models;

/**
 * Created by Admin on 08.07.2015.
 */
public class MusicType {

    private Integer id;
    private String musicGenre;

    public MusicType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicType)) return false;

        MusicType musicType = (MusicType) o;

        if (!getId().equals(musicType.getId())) return false;
        return getMusicGenre().equals(musicType.getMusicGenre());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getMusicGenre().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MusicType{" +
                "id=" + id +
                ", musicGenre='" + musicGenre + '\'' +
                '}';
    }
}
