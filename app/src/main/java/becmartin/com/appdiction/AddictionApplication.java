package becmartin.com.appdiction;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Application;

/**
 * Created by sexybexy on 10/25/14.
 */
public class AddictionApplication extends Application {

    @Override
    public void onCreate() {
        //make sure all the code in the parent gets called too
        super.onCreate();
        ParseObject.registerSubclass(Quiz.class);
        ParseObject.registerSubclass(Recording.class);
        Parse.initialize(this, "9bLsk8MAgXOWFm4r5ZS2sggfHFchdwyzWkcqxbdV", "5KDBvbDFuLTTHURLd7MPbvO6BpSINUbDV0lxOinf");
    }
}
