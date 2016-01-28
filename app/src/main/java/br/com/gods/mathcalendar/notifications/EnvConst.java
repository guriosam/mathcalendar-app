package br.com.gods.mathcalendar.notifications;

import java.util.ResourceBundle;

/**
 * Created by Caio on 24/01/2016.
 */
public class EnvConst {

    public static final ParseProperties PROPERTIES = new ParseProperties();


    public static final String PARSE_APPLICATION_ID = PROPERTIES.getString("PARSE_APPLICATION_ID");
    public static final String PARSE_REST_API_KEY = PROPERTIES.getString("PARSE_REST_API_KEY");

    public static class ParseProperties {

        private final ResourceBundle mBundle = ResourceBundle.getBundle("parsepush");

        public String getString(final String key) {
            return mBundle.getString(key);
        }
    }
}