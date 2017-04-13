package shinelee.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private ViewGroup rootView;
    private int[] colors = {Color.BLACK, Color.BLUE, Color.GREEN,
            Color.RED, Color.YELLOW, Color.GRAY, Color.MAGENTA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
