package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.framework.environment.Resources;

public abstract class OffensiveStatisticsProperties extends AbstractProperties {

  public OffensiveStatisticsProperties(Resources resources) {
    super(resources);
  }

  public String getAccuracyLabel() {
    return getLabelString("Equipment.Stats.Long.Accuracy");
  }

  public String getSpeedLabel() {
    return getLabelString("Equipment.Stats.Long.Speed");
  }

  public String getRateLabel() {
    return getLabelString("Equipment.Stats.Long.Rate");
  }
}