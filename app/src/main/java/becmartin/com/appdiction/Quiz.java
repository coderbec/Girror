package becmartin.com.appdiction;

/**
 * Created by sexybexy on 10/25/14.
 */

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Quiz")
public class Quiz extends ParseObject {
    public String getStartTime() {
        return getString("start_time");
    }

    public void setStartTime(Date date) {
        put("start_time", date);
    }

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser value) {
        put("user", value);
    }

    public ParseGeoPoint getLocation() {
        return getParseGeoPoint("location");
    }

    public void setLocation(ParseGeoPoint value) {
        put("location", value);
    }


    public static ParseQuery<Quiz> getQuery() {
        return ParseQuery.getQuery(Quiz.class);
    }
}
