package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.model.Hero;

public class GreatCurseFetcher {

  public static VirtueFlawModel fetch(Hero hero) {
    return hero.getModel(VirtueFlawModel.ID);
  }
}
