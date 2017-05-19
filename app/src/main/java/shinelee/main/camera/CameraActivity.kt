package shinelee.main.camera

import android.app.Activity
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import shinelee.main.R
import java.io.IOException

/**
 * Created by shine on 2017/2/27.
 */

class CameraActivity : Activity() {
    private val WIDTH = 640
    private val HEIGHT = 480
    private var camera: Camera? = null

    fun getYuvBuffer(width: Int, height: Int): Int {
        // stride = ALIGN(width, 16)
        val stride = Math.ceil(width / 16.0).toInt() * 16
        // y_size = stride * height
        val y_size = stride * height
        // c_stride = ALIGN(stride/2, 16)
        val c_stride = Math.ceil(width / 32.0).toInt() * 16
        // c_size = c_stride * height/2
        val c_size = c_stride * height / 2
        // size = y_size + c_size * 2
        return y_size + c_size * 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val sufaceTexture = findViewById(R.id.camera_texture) as TextureView

        sufaceTexture.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
                Log.d("lll", "onSurfaceTextureAvailable  width:$width  height:$height")

                startCamera(surface)
            }

            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
                Log.d("lll", "onSurfaceTextureSizeChanged  width:$width  height:$height")

            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

            }
        }

    }

    private fun startCamera(surface: SurfaceTexture) {
        surface.setDefaultBufferSize(HEIGHT, WIDTH)

        Log.d("lll", "open camera begin")
        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK)

        val params = camera?.parameters
        params?.setPreviewSize(WIDTH, HEIGHT)
        params?.previewFormat = ImageFormat.YV12
        camera?.parameters = params

        camera?.setDisplayOrientation(90)
        try {
            camera?.setPreviewTexture(surface)
            camera?.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        var flag = true
        val buffer = ByteArray(getYuvBuffer(WIDTH, HEIGHT))
        for (i in 0..1) {
            camera?.addCallbackBuffer(buffer)
        }
        camera?.setPreviewCallbackWithBuffer { data, camera ->
            if (flag) {
                flag = false
                Log.d("lll", "open camera end")
            }
            camera.addCallbackBuffer(data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        camera?.release()
    }
}
