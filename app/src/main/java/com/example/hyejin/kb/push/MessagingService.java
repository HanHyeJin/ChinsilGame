package com.example.hyejin.kb.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.example.hyejin.kb.R;
import com.example.hyejin.kb.activity.multiplay.play.MultiPlayActivity;
import com.example.hyejin.kb.app.App;
import com.example.hyejin.kb.dto.Card;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

/**
 * Created by Taehoon Yoo
 * User  : NOEP
 * Date  : 2016. 12. 2.
 * Time  : 오전 12:53
 * Page  : http:noep.github.io
 * Email : noep@naver.com
 * Desc  :
 */
public class MessagingService extends FirebaseMessagingService {

    private static final String TAG = "MessagingService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        /**
         * 데이터 : 앱이 깔려만 있으면 다 날라감
         */
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            sendNotification("data push", "data received");
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        /**
         * 노티 : 앱이 켜져 있어야 날라감, 대부분 이걸로 만들어야 할듯
         */
        // Check if message contains a notification payload.
        Card.Response card = parseNotification(remoteMessage.getNotification());
        if (card != null) {

            if (card.getGameRoomId().equals(App.getInstance().getGameRoom().getId())) {
                App.getInstance().setCard(card);
                sendNotification(remoteMessage.getNotification().getTitle(),
                        card.getContent());

            }

        }
    }

    private Card.Response parseNotification(RemoteMessage.Notification notification) {

        if (notification != null) {
            Log.d(TAG, "Message Notification Body: " + notification.getBody());
            String body = notification.getBody();
            if (!TextUtils.isEmpty(body)) {
                return new Gson().fromJson(body, Card.Response.class);
            }
        }
        return null;
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String title, String messageBody) {
        Intent intent = new Intent(this, MultiPlayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.listbackground)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        /**
         * fixme 여기다가 이런식으로 startActivity를 짱박는게 먹힐줄이야
         * 이거 되게 별로 진짜 별로
         */
        startActivity(intent);
    }


}
