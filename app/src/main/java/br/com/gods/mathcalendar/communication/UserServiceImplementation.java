package br.com.gods.mathcalendar.communication;

import java.net.URLEncoder;


public class UserServiceImplementation extends MathCalendarAbstractService implements UserService {

    @Override
    public void userData(String name/*, String age, String email, String vip*/, MathCalendarServiceCallback<Response> callback) {

        String name_field = "entry_68340782=" + URLEncoder.encode(name);
        //String age_field = "entry_673818712=" + URLEncoder.encode(age);
        //String email_field = "entry_1033294711=" + URLEncoder.encode(email);
        //String vip_field = "entry_1609563132" + URLEncoder.encode(vip);

        //System.out.println("USER SERVICE CLASS");
        //System.out.println(name/* + '\n' + email + '\n' + vip*/);
        //System.out.println(name_field/* + '\n' + email_field + '\n' + vip_field*/);

        //getApiClient().userData(name_field/*, age_field, email_field, vip_field*/, new DefaultApiCallback<Response>(callback));
    }
}
