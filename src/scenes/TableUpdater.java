package scenes;

import java.util.stream.Stream;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Customer;
import services.ServiceSubscriber;

public class TableUpdater<S> implements ServiceSubscriber<S> {
    TableView<S> tableElement;

    /**
     * class Constructor
     * @param tableElement
     */
    TableUpdater(TableView<S> tableElement) {
        this.tableElement = tableElement;
    }

    /**
     * builds the columns of the table
     * @param key
     * @return
     */
    TableColumn<S, String> columnFactory(String key) {
        TableColumn<S, String> tableColumn = new TableColumn<S, String>(key);
        tableColumn.setCellValueFactory(
                new PropertyValueFactory<S, String>(key));

        return tableColumn;
    }

    /**
     * initColumns
     * add the columns to the table
     * Note: a lambda function is used here to build each column based on what exists in the model
     */
    public void initColumns(Stream<String> keys) {
        this.tableElement.getColumns().clear();
        this.tableElement.getColumns().addAll(keys.flatMap(key -> Stream.of(columnFactory(key))).toList());
    }

    /**
     * requestUpdate
     * refreshes the values in the table
     */
    public ObservableList<S> requestUpdate(ObservableList<S> items) {
        this.tableElement.setItems(items);
        this.tableElement.refresh();

        return items;
    }

    /**
     * predicate to determine if the selection is null
     * @param <T>
     * @param table
     * @return
     */
    public static <T> boolean isNullSelection(TableView<T> table) {
        return getSelected(table) == null;
    }

    /**
     * gets current selection from the table
     * @param <T>
     * @param table
     * @return
     */
    public static <T> T getSelected(TableView<T> table) {
        return table.getSelectionModel().getSelectedItem();
    }

}
