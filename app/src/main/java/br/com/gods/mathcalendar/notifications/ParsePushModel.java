package br.com.gods.mathcalendar.notifications;

/**
 * Created by Caio on 24/01/2016.
 */
public class ParsePushModel {

    String title;

    String message;

    String url;

    boolean forceSound;

    public static ParsePushModel to() {
        return new ParsePushModel();
    }

    public ParsePushModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public ParsePushModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public ParsePushModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public ParsePushModel setForceSound(boolean forceSound) {
        this.forceSound = forceSound;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public boolean forceSound() {
        return forceSound;
    }
}