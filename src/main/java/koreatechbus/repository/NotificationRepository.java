package koreatechbus.repository;

import org.springframework.data.repository.Repository;

import koreatechbus.domain.Notification;

public interface NotificationRepository extends Repository<Notification, Long> {

    Notification save(Notification notification);
}
