package net.sf.anathema.platform.fx.selection;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.platform.fx.ConfigurableListCellFactory;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class ComboBoxSelectionView<V> implements FxObjectSelectionView<V> {
  private ComboBox<V> comboBox;
  private Label label;
  private MigPane pane;
  private final Announcer<ObjectValueListener> announcer = Announcer.to(ObjectValueListener.class);

  @SuppressWarnings("unchecked")
  public ComboBoxSelectionView(final String description, final AgnosticUIConfiguration<V> ui) {
    comboBox = new ComboBox<>();
    label = new Label(description);
    pane = new MigPane(withoutInsets());
    if (!StringUtilities.isNullOrTrimmedEmpty(description)) {
      pane.add(label);
    }
    pane.add(comboBox);
    ConfigurableListCellFactory<V> factory = new ConfigurableListCellFactory<>(ui);
    comboBox.setButtonCell(factory.call(null));
    comboBox.setCellFactory(factory);
    comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<V>() {
      @Override
      public void changed(ObservableValue<? extends V> observableValue, V oldValue, V newValue) {
        announcer.announce().valueChanged(newValue);
      }
    });
  }

  @Override
  public void setSelectedObject(final V object) {
    comboBox.getSelectionModel().select(object);
  }

  @Override
  public void addObjectSelectionChangedListener(final ObjectValueListener<V> listener) {
    announcer.addListener(listener);
  }

  @Override
  public void removeObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    announcer.removeListener(listener);
  }

  @Override
  public void setObjects(final V[] objects) {
    comboBox.setItems(new ObservableListWrapper<>(Arrays.asList(objects)));
  }

  @Override
  public void setObjects(Collection<V> objects) {
    comboBox.setItems(new ObservableListWrapper<>(new ArrayList<>(objects)));
  }

  @Override
  public V getSelectedObject() {
    return comboBox.getSelectionModel().getSelectedItem();
  }

  @Override
  public void setEnabled(boolean enabled) {
    label.setDisable(!enabled);
    comboBox.setDisable(!enabled);
  }

  @Override
  public Node getNode() {
    return pane;
  }

  public void clearSelection() {
    comboBox.getSelectionModel().clearSelection();
  }

  public void setStyleClass(String styleClass) {
    comboBox.getStyleClass().add(styleClass);
  }

  public void makeEditable() {
    comboBox.setEditable(true);
  }
}