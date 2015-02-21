package color.com.textcoloradapter;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Utils {
    public static final String BLACK = "black";
    public static final String WHITE = "white";

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static String getContrast50(String hexColor) {
        return (Integer.parseInt(hexColor, 16) > 0xFFFFFF/2 ? BLACK : WHITE);
    }

    public static String getHexStringAverageColor (Bitmap bitmap) {
        long redBucket = 0;
        long greenBucket = 0;
        long blueBucket = 0;
        int pixelCount = 0;

        for (int y = 0; y < bitmap.getHeight(); y+=4) {
            for (int x = 0; x < bitmap.getWidth(); x+=4) {
                int color = bitmap.getPixel(x, y);

                pixelCount++;
                redBucket += Color.red(color);
                greenBucket += Color.green(color);
                blueBucket += Color.blue(color);
            }
        }
        return Integer.toHexString(Color.argb(0, (int)(redBucket / pixelCount),
                (int)(greenBucket / pixelCount), (int)(blueBucket / pixelCount)));
    }
}
