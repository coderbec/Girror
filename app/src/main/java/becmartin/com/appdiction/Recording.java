package becmartin.com.appdiction;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sexybexy on 10/25/14.
 */
@ParseClassName("Recording")
public class Recording extends ParseObject {

    /*happy_score: Number
    frustrated_score: Number
    alert_score: Number
    enthusiasm_score: Number
    nervous_score: Number
    quiz: Quiz*/

    public int getHappyScore() {
        return getInt("happy_score");
    }

    public void setHappyScore(int happyScore) {
        put("happy_score", happyScore);
    }

    public void setFrustratedScore(int frustratedScore) {
        put("frustrated_score", frustratedScore);
    }

    public int getFrustratedScore() {
        return getInt("frustrated_score");
    }

    public void setAlertScore(int alertScore) {
        put("alert_score", alertScore);
    }

    public int getAlertScore() {
        return getInt("alert_score");
    }

    public int getEnthusiasmScore() {
        return getInt("enthusiasm_score");
    }

    public void setEnthusiasmScore(int enthusiasmScore) {
        put("enthusiasm_score", enthusiasmScore);
    }

    public int getNervousScore() {
        return getInt("enthusiasm_score");
    }

    public void setNervousScore(int nervousScore) {
        put("nervous_score", nervousScore);
    }

    public ParseObject getQuiz() {
        return getParseObject("quiz");
    }


    public void setAlcohol(Boolean alcohol) {
        put("alcohol", alcohol);
    }

    public Boolean getAlcohol() {
        return getBoolean("alcohol");
    }

    public void setWhatDoing(String whatDoing) {
        put("what_doing", whatDoing);
    }

    public String getWhatDoing() {
        return getString("what_doing");
    }

    public void setOther(Boolean other) {
        put("other", other);
    }

    public Boolean getOther() {
        return getBoolean("other");
    }

    public void setFriends(Boolean friends) {
        put("friends", friends);
    }

    public Boolean getFriends() {
        return getBoolean("friends");
    }

    public void setColleagues(Boolean colleagues) {
        put("colleagues", colleagues);
    }

    public Boolean getColleagues() {
        return getBoolean("colleagues");
    }

    public void setOtherFamily(Boolean otherFamily) {
        put("other_family", otherFamily);
    }

    public Boolean getOtherFamily() {
        return getBoolean("other_family");
    }

    public void setChildren(Boolean children) {
        put("children", children);
    }

    public Boolean getChildren() {
        return getBoolean("children");
    }

    public void setPartner(Boolean partner) {
        put("partner", partner);
    }

    public Boolean getPartner() {
        return getBoolean("partner");
    }

    public void setAlone(Boolean alone) {
        put("alone", alone);
    }

    public Boolean getAlone() {
        return getBoolean("alone");
    }
    //response_times: Array
   // alcohol: Boolean
    //what_doing: String
    //other: Boolean
   // friends: Boolean
   // colleagues: Boolean
   // other_family: Boolean
    //children: Boolean
    //partner: Boolean
   // alone: Boolean

    public void setQuiz(ParseObject quiz) {
        put("quiz", quiz);
    }

    public static ParseQuery<Recording> getQuery() {
        return ParseQuery.getQuery(Recording.class);
    }
}