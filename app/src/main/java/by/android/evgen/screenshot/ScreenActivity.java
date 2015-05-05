package by.android.evgen.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

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

                int width =  container.getWidth() ;
                int height =  container.getHeight() ;
                int screenshotSize = width * height ;
                ByteBuffer bb =  ByteBuffer. allocateDirect(screenshotSize * 4);
                bb . order ( ByteOrder. nativeOrder());
                EGL10 egl = (EGL10) EGLContext.getEGL();
                GL10 gl = (GL10) egl.eglGetCurrentContext().getGL();
                gl.glReadPixels(0, 0, width, height, GL10.GL_RGBA,
                        GL10.GL_UNSIGNED_BYTE, bb);
                int pixelsBuffer []  =  new  int [ screenshotSize ];
                bb . asIntBuffer (). get ( pixelsBuffer );
                bb =  null ;
                Bitmap bitmap =  Bitmap . createBitmap ( width , height ,
                        Bitmap . Config . RGB_565 );
                bitmap . setPixels ( pixelsBuffer , screenshotSize - width ,  - width ,  0 ,
                        0 , width , height );
                pixelsBuffer =  null ;

                short sBuffer []  =  new  short [ screenshotSize ];
                ShortBuffer sb =  ShortBuffer . wrap ( sBuffer );
                bitmap . copyPixelsToBuffer ( sb );


                for  ( int i =  0 ; i < screenshotSize ;  ++ i )  {
                    short v = sBuffer [ i ];
                    sBuffer [ i ]  =  ( short )  ((( v &  0x1f )  <<  11 )  |  ( v &  0x7e0 )  |  (( v &  0xf800 )  >>  11 ));
                }
                sb . rewind ();
                bitmap . copyPixelsFromBuffer ( sb );
//                container . captureBmp = bitmap . copy ( Bitmap . Config . ARGB_8888 , false );
//                MainImageProcessingActivity . capture = false ;
//
//
//
//
//
//                Bitmap bitmap;
//                View v1 = ScreenActivity.this.getWindow().getDecorView();
//                v1.setDrawingCacheEnabled(true);
//                bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//                screenIcon.setImageBitmap(bitmap);
                screenIcon.setImageBitmap(bitmap);
            }
        });

    }
}
