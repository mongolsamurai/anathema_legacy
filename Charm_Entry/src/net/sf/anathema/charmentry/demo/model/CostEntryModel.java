package net.sf.anathema.charmentry.demo.model;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.demo.ICostEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CostEntryModel implements ICostEntryModel {

  private static final String BLANK = ""; //$NON-NLS-1$
  private final IConfigurableCharmData charmData;

  public CostEntryModel(ICharmTypeEntryModel model, final IConfigurableCharmData charmData) {
    this.charmData = charmData;
    model.addModelListener(new IChangeListener() {
      public void changeOccured() {
        if (charmData.getCharmTypeModel().getCharmType() == CharmType.Permanent) {
          setEssenceCostValue(BLANK);
          setEssenceCostText(BLANK);
          setWillpowerCostValue(BLANK);
          setWillpowerCostText(BLANK);
          setHealthCostValue(BLANK);
          setHealthCostText(BLANK);
          setXpCostValue(BLANK);
          setXpCostText(BLANK);
        }
      }
    });
  }

  public void setEssenceCostValue(String newValue) {
    charmData.getTemporaryCost().getEssenceCost().setValue(newValue);
  }

  public void setEssenceCostText(String newValue) {
    charmData.getTemporaryCost().getEssenceCost().setText(newValue);
  }

  public void setWillpowerCostValue(String newValue) {
    charmData.getTemporaryCost().getWillpowerCost().setValue(newValue);
  }

  public void setWillpowerCostText(String newValue) {
    charmData.getTemporaryCost().getWillpowerCost().setText(newValue);
  }

  public void setHealthCostValue(String newValue) {
    charmData.getTemporaryCost().getHealthCost().setValue(newValue);
  }

  public void setHealthCostText(String newValue) {
    charmData.getTemporaryCost().getHealthCost().setText(newValue);
  }

  public void setXpCostValue(String newValue) {
    charmData.getPermanentCost().getXPCost().setValue(newValue);
  }

  public void setXpCostText(String newValue) {
    charmData.getPermanentCost().getXPCost().setText(newValue);
  }

}
