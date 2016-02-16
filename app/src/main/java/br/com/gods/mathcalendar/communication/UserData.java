package br.com.gods.mathcalendar.communication;

import java.io.Serializable;

/**
 * Created by Evandro on 11/02/2016.
 */
public class UserData implements Serializable {

    private String id;
    private String name;
    private String birthday;
    private String email;
    private String gender;
    private String link;
    private String timezone;
    private String cover;
    private String vip;

    public UserData() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getLink() {
        return link;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getCover() {
        return cover;
    }

    public String getVip() {
        return vip;
    }

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

    public void setVip(String vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {

        String s = "";

        s += s + id + "\n";
        s += s + name + "\n";
        //s += s + birthday + "\n";
        //s += s + email + "\n";
        s += s + gender + "\n";
        s += s + link + "\n";
        s += s + timezone + "\n";
        s += s + cover;
        s += s + vip;

        return s;
    }
}
