package ar.edu.fesf.model;

import java.util.List;

public class NotificationManager {

    private List<OverdueNotification> pendingNotifications;

    public void notifyOverdue(final Loan loan) {
        this.getNotifications().add(new OverdueNotification(loan));
    }

    public void sendPendingNotifications() {

        for (OverdueNotification notif : this.getPendingNotifications()) {
            this.sendNotification(notif);
        }
    }

    public void sendNotification(final OverdueNotification overdueNotification) {
        // TODO
        // Verifica si aun corresponde enviar el mail
        // Intenta enviarla
        // - si puede, registra el exito en la notificacion, y la elimina de las pendientes
    }

    /* Accessors */

    public List<OverdueNotification> getNotifications() {
        return this.pendingNotifications;
    }

    public void setNotifications(final List<OverdueNotification> notifications) {
        this.pendingNotifications = notifications;
    }

    public void setPendingNotifications(final List<OverdueNotification> pendingNotifications) {
        this.pendingNotifications = pendingNotifications;
    }

    public List<OverdueNotification> getPendingNotifications() {
        return this.pendingNotifications;
    }

}
