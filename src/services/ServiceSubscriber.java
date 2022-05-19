package services;

import javafx.collections.ObservableList;

/**
 * interface for building listener/subscribers
 * for updating scenes reactively
 */
public interface ServiceSubscriber<S> {
    public ObservableList<S>  requestUpdate(ObservableList<S> customers);
}
