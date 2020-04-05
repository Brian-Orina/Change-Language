package change.language;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import change.language.Helper.LocaleHelper;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    TextView mExample;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase,"en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExample = (TextView)findViewById(R.id.example);

        // Init paper first
        Paper.init(this);

        // Default language is English
        String language = Paper.book().read("language");
        if (language == null){
            Paper.book().write("language", "en");
            updateView((String)Paper.book().read("language"));
        }

    }

    private void updateView(String language) {
        Context context = LocaleHelper.setLocale(this,language);
        Resources resources = context.getResources();

        mExample.setText(resources.getString(R.string.large_text));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_language, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.english) {
            Paper.book().write("language", "en");
            updateView((String)Paper.book().read("language"));
        }
        else if (item.getItemId() == R.id.swahili){
            Paper.book().write("language", "sw");
            updateView((String)Paper.book().read("language"));
        }
        return true;
    }

}
