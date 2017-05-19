package shinelee.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import shinelee.main.view.GiftAnimationDrawable;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private GiftAnimationDrawable giftDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
