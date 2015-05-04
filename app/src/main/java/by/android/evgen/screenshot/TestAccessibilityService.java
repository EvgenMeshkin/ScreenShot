package by.android.evgen.screenshot;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by evgen on 04.05.2015.
 */
public class TestAccessibilityService extends AccessibilityService {

    public static View mCurrentView;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        Log.d("***", "*********" + event);

        AccessibilityNodeInfo source = event.getSource();
        Log.d("***", "*********" + source);

//        List<AccessibilityWindowInfo> windows = getWindows();
//        ArrayList<AccessibilityNodeInfo> nodes =
//                new ArrayList<AccessibilityNodeInfo>();
//        if (windows.size() > 0) {
//            for (AccessibilityWindowInfo window : windows) {
//                nodes.add(window.getRoot());
//            }
//        }
//        View v1 = source.getWindow().getRoot().;

//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        Drawable icon = inflater.getContext().getResources().getDrawable();
//
//        Instrumentation.ActivityMonitor solo = waitForActivity("com.botskool.DialogBox.DialogBox", ACTIVITY_WAIT_MILLIS);

//        Parcelable data = event.getParcelableData();
//        if(data !=null) {
//            Notification notification = (Notification) data;
//            RemoteViews remoteView = notification.contentView;
//            mCurrentView = inflater.inflate(remoteView.getLayoutId(), null);
//            Log.xd(this, "**********************" + mCurrentView);
//        }

//        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
//            if (event.getParcelableData() instanceof Notification) {
//                Notification notification = (Notification) event.getParcelableData();
//                RemoteViews views = notification.contentView;
//                LinearLayout ll = new LinearLayout(getApplicationContext());
//                mCurrentView = views.apply(getApplicationContext(), ll);
//            }
//        }

        String mPackageName = event.getPackageName().toString();
        final Context remotePackageContext;
        try {
            remotePackageContext = getApplicationContext().createPackageContext(mPackageName, 0);
//            Drawable icon = remotePackageContext.getResources().getDrawable();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//       source.getChild(source.getChildCount() - 1);
        // Grab the parent of the view that fired the event.
//        AccessibilityNodeInfo rowNode = AccessibilityNodeInfo.getListItemNodeInfo(source);
//        if (rowNode == null) {
//            return;
//        }
//
//        // Using this parent, get references to both child nodes, the label and the checkbox.
//        AccessibilityNodeInfo labelNode = rowNode.getChild(0);
//        if (labelNode == null) {
//            rowNode.recycle();
//            return;
//        }
//
//        AccessibilityNodeInfo completeNode = rowNode.getChild(1);
//        if (completeNode == null) {
//            rowNode.recycle();
//            return;
//        }

//        String eventText = event.getContentDescription().toString();
//        Log.xd(this, "*********" + eventText);
    }

    @Override
    public void onInterrupt() {

    }

}
