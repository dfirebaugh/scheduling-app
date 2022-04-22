package scenes;

import java.util.stream.Stream;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableUpdater<S> {
    TableView<S> tableElement;
    TableUpdater(TableView<S> tableElement) {
        this.tableElement = tableElement;
    }

    TableColumn<S, String> columnFactory(String key) {
        TableColumn<S, String> tableColumn = new TableColumn<S, String>(key);
        tableColumn.setCellValueFactory(
                new PropertyValueFactory<S, String>(key));

        return tableColumn;
    }

    /**
     * initColumns
     * add the columns to the table
     */
    public void initColumns(Stream<String> keys) {
        this.tableElement.getColumns().addAll(keys.flatMap(key -> Stream.of(columnFactory(key))).toList());
    }

    /**
     * requestUpdate
     * refreshes the values in the table
     */
    public void requestUpdate(ObservableList<S> items) {
        this.tableElement.setItems(items);
        this.tableElement.refresh();
    }
}
