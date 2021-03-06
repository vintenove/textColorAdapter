package color.com.textcoloradapter;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static boolean LOG = (BuildConfig.DEBUG) ? true : false;
    private static final String UNDERGROUND = "That is my conviction of forty years. I am forty years old now, and you know forty years is a whole lifetime; you know it is extreme old age. To live longer than forty years is bad manners, is vulgar, immoral. Who does live beyond forty? Answer that, sincerely and honestly I will tell you who do: fools and worthless fellows. I tell all old men that to their face, all these venerable old men, all these silver-haired and reverend seniors! I tell the whole world that to its face! I have a right to say so, for I shall go on living to sixty myself. To seventy! To eighty! ... Stay, let me take breath ... ";
    private static String mTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        long start = System.currentTimeMillis();
        final TextView tv = (TextView) findViewById(R.id.texto);
        final Bitmap b = Utils.drawableToBitmap(WallpaperManager.getInstance(getApplicationContext()).getDrawable());

        mTextColor = Utils.getContrast50(Utils.getHexStringAverageColor(b)); // could use a different contrast function

        tv.setText(UNDERGROUND);
        if (mTextColor.equals(Utils.BLACK)) tv.setTextColor(Color.BLACK);
        else tv.setTextColor(Color.WHITE);
        if (LOG) Log.d("MERDA", "" + (System.currentTimeMillis() - start) + " " + tv.getText());

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            return rootView;
        }
    }
}
