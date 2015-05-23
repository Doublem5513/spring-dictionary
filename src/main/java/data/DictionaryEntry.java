package data;

import javax.persistence.*;

/**
 * Created by mmatosevic on 22.5.2015.
 */
@Entity
@Table(name = "DICTIONARY_ENTRIES")
public class DictionaryEntry {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "ENGLISH")
    private String english;
    @Column(name = "CROATIAN")
    private String croatian;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getCroatian() {
        return croatian;
    }

    public void setCroatian(String croatian) {
        this.croatian = croatian;
    }

    @Override
    public String toString() {
        return "DictionaryEntry{" +
                "id=" + id +
                ", english='" + english + '\'' +
                ", croatian='" + croatian + '\'' +
                '}';
    }
}
