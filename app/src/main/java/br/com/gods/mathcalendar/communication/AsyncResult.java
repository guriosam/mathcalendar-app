package br.com.gods.mathcalendar.communication;

import org.json.JSONObject;

/**
 * Created by Evandro on 16/02/2016.
 */
public interface AsyncResult {

    void onResult(JSONObject object);
}
