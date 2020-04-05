package change.language;

import android.app.Application;
import android.content.Context;

import change.language.Helper.LocaleHelper;

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.setLocale(base, "en"));
    }
}
