package koreatechbus.service;

import org.springframework.stereotype.Service;


import koreatechbus.FCMClient;
import koreatechbus.domain.Notification;
import koreatechbus.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final FCMClient fcmClient;

    public void push(Notification notification) {
        notificationRepository.save(notification);
        String deviceToken = notification.getUser().getDeviceToken();
        fcmClient.sendMessage(
            deviceToken,
            notification.getTitle(),
            notification.getMessage()
        );
    }
}
