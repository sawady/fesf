package ar.edu.fesf.model;

import java.util.ArrayList;

public class NotificationManager {

    private ArrayList<OverdueNotification> pendingNotifications;

    public void notifyOverdue(final Loan loan) {
        this.getNotifications().add(new OverdueNotification(loan));
    }

    public void sendPendingNotifications() {

        for (OverdueNotification notif : this.getPendingNotifications()) {
            this.sendNotification(notif);
        }
    }

    public void sendNotification(final OverdueNotification overdueNotification) {
        // Verifica si aun corresponde enviar el mail
        // Intenta enviarla
        // - si puede, registra el exito en la notificacion, y la elimina de las pendientes
    }

    /* Accessors */

    public ArrayList<OverdueNotification> getNotifications() {
        return this.pendingNotifications;
    }

    public void setNotifications(final ArrayList<OverdueNotification> notifications) {
        this.pendingNotifications = notifications;
    }

    public void setPendingNotifications(final ArrayList<OverdueNotification> pendingNotifications) {
        this.pendingNotifications = pendingNotifications;
    }

    public ArrayList<OverdueNotification> getPendingNotifications() {
        return this.pendingNotifications;
    }

}
