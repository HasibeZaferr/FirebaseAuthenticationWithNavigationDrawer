package gelecegiyazanlar.com.gykfirebaseauthentication.models;

import android.graphics.Bitmap;

public class PhotoModel {
    Bitmap bitmap;

    public PhotoModel() {
    }

    public PhotoModel(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
