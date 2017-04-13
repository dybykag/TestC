package shinelee.main.camera;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.TextureView;

import java.io.IOException;

import shinelee.main.R;

/**
 * Created by shine on 2017/2/27.
 */

public class CameraActivity extends Activity {
    private final static int WIDTH = 640;
    private final static int HEIGHT = 480;
    private android.hardware.Camera camera;

    public static int getYuvBuffer(int width, int height) {
        // stride = ALIGN(width, 16)
        int stride = (int) Math.ceil(width / 16.0) * 16;
        // y_size = stride * height
        int y_size = stride * height;
        // c_stride = ALIGN(stride/2, 16)
        int c_stride = (int) Math.ceil(width / 32.0) * 16;
        // c_size = c_stride * height/2
        int c_size = c_stride * height / 2;
        // size = y_size + c_size * 2
        return y_size + c_size * 2;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        TextureView sufaceTexture = (TextureView) findViewById(R.id.camera_texture);

        sufaceTexture.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Log.d("lll", "onSurfaceTextureAvailable  width:" + width + "  height:" + height);

                startCamera(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Log.d("lll", "onSurfaceTextureSizeChanged  width:" + width + "  height:" + height);

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

    }

    private void startCamera(SurfaceTexture surface) {
        surface.setDefaultBufferSize(HEIGHT, WIDTH);

        Log.d("lll", "open camera begin");
        camera = android.hardware.Camera.open(android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK);
        android.hardware.Camera.Parameters params = camera.getParameters();
        params.setPreviewSize(WIDTH, HEIGHT);
        params.setPreviewFormat(ImageFormat.YV12);
        camera.setParameters(params);

        camera.setDisplayOrientation(90);
        try {
            camera.setPreviewTexture(surface);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final boolean[] flag = {true};
        byte[] buffer = new byte[getYuvBuffer(WIDTH, HEIGHT)];
        for (int i = 0; i < 2; i++) {
            camera.addCallbackBuffer(buffer);
        }
        camera.setPreviewCallbackWithBuffer(new android.hardware.Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, android.hardware.Camera camera) {
                if (flag[0]) {
                    flag[0] = false;
                    Log.d("lll", "open camera end");
                }
                camera.addCallbackBuffer(data);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.release();
    }
}
