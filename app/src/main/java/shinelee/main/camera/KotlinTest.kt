package shinelee.main.camera

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_kotlin_test.*
import shinelee.main.R

/**
 * Created by shine on 2017/5/18.
 */
class KotlinTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_kotlin_test)


        var h = "hello world"
        kotlin_text.text = h
        kotlin_text.textSize = 30f
        kotlin_text.setTextColor(Color.RED)
        kotlin_text.setOnClickListener { v: View ->
            if (v is TextView) {
                v.text = "asdasdsadasd"
            }
        }

        Log.d("lll", resources.configuration.locale.language)
    }

    fun test(): Int {
        return 1
    }
}