package koreatechbus;

import static com.google.firebase.messaging.AndroidConfig.Priority.HIGH;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FCMClient {

    @Async
    public void sendMessage(
        String targetDeviceToken,
        String title,
        String content
    ) {
        if (targetDeviceToken == null) {
            return;
        }
        log.info("call FcmClient sendMessage: title: {}, content: {}", title, content);
        Notification notification = Notification.builder()
            .setTitle(title)
            .setBody(content)
            .build();
        Message message = Message.builder()
            .setToken(targetDeviceToken)
            .setNotification(notification)
            .setAndroidConfig(AndroidConfig.builder()
                .setPriority(HIGH)
                .build()
            ).build();
        try {
            String result = FirebaseMessaging.getInstance().send(message);
            log.info("FCM 알림 전송 성공: {}", result);
        } catch (Exception e) {
            log.warn("FCM 알림 전송 실패", e);
        }
    }
}
