package services;

import javafx.collections.ObservableList;

public interface ServiceSubscriber<S> {
    public ObservableList<S>  requestUpdate(ObservableList<S> customers);
}
