package br.com.gods.mathcalendar;

import java.io.Serializable;

/**
 * Created by Evandro on 11/02/2016.
 */
public class PersonData implements Serializable {

    private String id;
    private String name;
    private String birthday;
    private String email;
    private String gender;
    private String link;
    private String timezone;
    private String cover;

    public PersonData() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {

        String s = "";

        s += s + id + "\n";
        s += s + name + "\n";
        s += s + birthday + "\n";
        s += s + email + "\n";
        s += s + gender + "\n";
        s += s + link + "\n";
        s += s + timezone + "\n";
        s += s + cover;

        return s;
    }
}
