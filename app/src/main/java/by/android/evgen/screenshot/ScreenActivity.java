package by.android.evgen.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by evgen on 04.05.2015.
 */
public class ScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_activity);
        View btnScreen = findViewById(R.id.btn_screen);
        final ImageView screenIcon = (ImageView) findViewById(R.id.screen_icon);
        final RelativeLayout container = (RelativeLayout) findViewById(R.id.container);

        btnScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                solo.waitForActivity("com.botskool.DialogBox.DialogBox", ACTIVITY_WAIT_MILLIS);

                Bitmap bitmap;
                View v1 = ScreenActivity.this.getWindow().getDecorView();
                v1.setDrawingCacheEnabled(true);
                bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//                screenIcon.setImageBitmap(bitmap);
                screenIcon.setImageBitmap(bitmap);
            }
        });

    }
}
