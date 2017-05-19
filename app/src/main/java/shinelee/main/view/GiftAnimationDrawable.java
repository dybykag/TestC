package shinelee.main.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * Created by shine on 2017/4/27.
 */

public class GiftAnimationDrawable extends AnimationDrawable {
    private final static String TAG = GiftAnimationDrawable.class.getSimpleName();
    private int duration;
    private String dirPath;

    public GiftAnimationDrawable() {

    }

    public GiftAnimationDrawable(Context context, String path, int duration) {
        this.dirPath = path;
        this.duration = duration;

        initAnim(context);
    }

    public void setDirPath(Context context, String path, int duration) {
        this.dirPath = path;
        this.duration = duration;

        if (!TextUtils.isEmpty(path) && !path.equals(dirPath)) {
            initAnim(context);
        }
    }

    private void initAnim(final Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            String[] framePaths = assetManager.list(dirPath);
            int frameDuration = duration / framePaths.length;
            for (String frame : framePaths) {
                String framePath = dirPath + "/" + frame;
                Bitmap frameBmp = BitmapFactory.decodeStream(assetManager.open(framePath));
                addFrame(new BitmapDrawable(context.getResources(), frameBmp), frameDuration);
            }
            addFrame(getFrame(0), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void recycle() {
        if (TextUtils.isEmpty(dirPath)) {
            return;
        }
        try {
            for (int i = 0; i < getNumberOfFrames(); i++) {
                Drawable frame = getFrame(i);
                if (frame instanceof BitmapDrawable) {
                    Bitmap bmp = ((BitmapDrawable) frame).getBitmap();
                    if (bmp != null && !bmp.isRecycled()) {
                        bmp.recycle();
                    }
                }
                frame.setCallback(null);
            }
            setCallback(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dirPath = null;
    }
}
