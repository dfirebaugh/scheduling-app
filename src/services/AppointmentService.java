package services;

import java.sql.SQLException;

import datastore.AppointmentStore;
import javafx.collections.ObservableList;
import models.Appointment;
import models.AppointmentsByTypeReport;
import models.Customer;

/**
 * AppointmentService handles requests to the DB that involve appointments.
 */
public class AppointmentService {
    private boolean isMonth = true;
    private Integer currentSet;

    /**
     * Class Constructor
     */
    public AppointmentService() {
    }

    /**
     * listener is a placeholder that gets set in a scene.  This allows us to trigger updates in a reactive way by calling
     *   `listener.requestUpdate(appointment)`
     */
    public ServiceSubscriber<Appointment> listener;

    /**
     * registerListener is invoked by a scene.  It allows us to pass in a subscriber that will get updated
     * when the data changes.  When we call `listener.requestUpdate` the subscriber will be updated.
     * @param listener
     */
    public void registerListener(ServiceSubscriber<Appointment> listener) {
        this.listener = listener;
    }

    /**
     * returns all appointments in the DB
     * @return
     */
    public ObservableList<Appointment> getAll() {
        try {
            return listener.requestUpdate(AppointmentStore.get());
        } catch (SQLException e) {
            Logger.error(e);
        }
        return null;
    }
    /**
     * Returns the appointments for the current page.
     * @return
     */
    public ObservableList<Appointment> get() {
        try {
            return listener.requestUpdate(AppointmentStore.get(isMonth, currentSet));
        } catch (SQLException e) {
            Logger.error(e);
        }
        return null;
    }
    /**
     * Returns any appointments that will occur in the next 15 minutes.
     * > note: this `15 minutes` is configured at the query level.
     * @return
     */
    public Appointment getUpComing() {
        try {
            return AppointmentStore.getUpComing();
        } catch (SQLException e) {
            Logger.error(e);
        }
        return null;
    }
    /**
     * inserts an appointment to the DB.
     * validations should have already occured at the scene level.
     * @param appointment
     */
    public void add(Appointment appointment) {
        try {
            AppointmentStore.add(appointment);
            listener.requestUpdate(AppointmentStore.get(isMonth, currentSet));
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    /**
     * update an existing appointment
     * @param appointment
     */
    public void update(Appointment appointment) {
        try {
            AppointmentStore.update(appointment);
            listener.requestUpdate(AppointmentStore.get(isMonth, currentSet));
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    /**
     * delete an appointment
     * @param appointment
     */
    public void delete(Appointment appointment) {
        try {
            AppointmentStore.delete(appointment);
            listener.requestUpdate(AppointmentStore.get(isMonth, currentSet));
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    /**
     * deletes customer's appointments.  This is necessary to call before we delete a customer.
     * @param customer
     */
    public void deleteAllCustomersAppointments(Customer customer) {
        try {
            AppointmentStore.deleteAllCustomersAppointments(customer);
            listener.requestUpdate(AppointmentStore.get(isMonth, currentSet));
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    /**
     * when this is called we toggle to month view
     * @param isMonth
     */
    public void setIsMonth(boolean isMonth) {
        this.isMonth = isMonth;
    }
    /**
     * currentSet represents the page that we are viewing
     * @param currentSet
     */
    public void setCurrentSet(Integer currentSet) {
        this.currentSet = currentSet;
    }
    /**
     * get the list of appointments for the current page
     * @return
     */
    public Integer getCurrentSet() {
        return currentSet;
    }
    /**
     * returns appropriate label based on month view or week view
     * @return
     */
    public String getTableLabel() {
        if (isMonth) {
            return "Month: " + currentSet;
        }
        return "Week: " + currentSet;
    }

    /**
     * fetches relavent report data from DB related to Appointments by type
     * @return
     */
    public ObservableList<AppointmentsByTypeReport> getAppointmentsByTypeReport() {
        try {
            return AppointmentStore.getAppointmentsByTypeReport();
        } catch (SQLException e) {
            Logger.error(e);
        }
        return null;
    }
}
