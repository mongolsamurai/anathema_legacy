package net.sf.anathema.charmentry.demo.model;

import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.demo.IDurationEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class DurationModel implements IDurationEntryModel {

  private final IConfigurableCharmData charmData;
  private final ChangeControl control = new ChangeControl();

  public DurationModel(ICharmTypeEntryModel typeModel, final IConfigurableCharmData charmData) {
    this.charmData = charmData;
    typeModel.addModelListener(new IChangeListener() {
      public void changeOccured() {
        if (charmData.getCharmTypeModel().getCharmType() == CharmType.Permanent) {
          setDuration("Permanent");
        }
      }
    });
  }

  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public String[] getDurations() {
    return new String[] { DurationType.Instant.getId() };
  }

  public void setDuration(String newValue) {
    charmData.setDuration(Duration.getDuration(newValue));
    control.fireChangedEvent();
  }

  public Duration getDuration() {
    return charmData.getDuration();
  }
}