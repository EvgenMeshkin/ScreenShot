package by.android.evgen.screenshot;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.InstrumentationInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.List;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
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
//        Intent service = new Intent(this, ScreenService.class);
//        startService(service);
        btnScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*int width = container.getWidth();
                int height = container.getHeight();
                int screenshotSize = width * height;
                ByteBuffer bb = ByteBuffer.allocateDirect(screenshotSize * 4);
                bb.order(ByteOrder.nativeOrder());
                EGL10 egl = (EGL10) EGLContext.getEGL();
                GL10 gl = (GL10) egl.eglGetCurrentContext().getGL();
                gl.glReadPixels(0, 0, width, height, GL10.GL_RGBA,
                        GL10.GL_UNSIGNED_BYTE, bb);
                int pixelsBuffer[] = new int[screenshotSize];
                bb.asIntBuffer().get(pixelsBuffer);
                bb = null;
                Bitmap bitmap = Bitmap.createBitmap(width, height,
                        Bitmap.Config.RGB_565);
                bitmap.setPixels(pixelsBuffer, screenshotSize - width, -width, 0,
                        0, width, height);
                pixelsBuffer = null;

                short sBuffer[] = new short[screenshotSize];
                ShortBuffer sb = ShortBuffer.wrap(sBuffer);
                bitmap.copyPixelsToBuffer(sb);

                for (int i = 0; i < screenshotSize; ++i) {
                    short v = sBuffer[i];
                    sBuffer[i] = (short) (((v & 0x1f) << 11) | (v & 0x7e0) | ((v & 0xf800) >> 11));
                }
                sb.rewind();
                bitmap.copyPixelsFromBuffer(sb);

                screenIcon.setImageBitmap(bitmap);*/

                runTests();

            }
        });

    }

    private void runTests() {
        final String packageName = "by.android.evgen.testapplication";//getPackageName();
        final List<InstrumentationInfo> list =
                getPackageManager().queryInstrumentation(packageName, 0);
        if ( list.isEmpty() ) {
            Toast.makeText(this, "Cannot find instrumentation for " + packageName,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        final InstrumentationInfo instrumentationInfo = list.get(0);
        final ComponentName componentName =
                new ComponentName(instrumentationInfo.packageName,
                        instrumentationInfo.name);
        if ( !startInstrumentation(componentName, null, null) ) {
            Toast.makeText(this, "Cannot run instrumentation for " + packageName,
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}
