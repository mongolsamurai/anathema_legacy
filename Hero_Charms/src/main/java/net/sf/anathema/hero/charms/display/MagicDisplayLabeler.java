package net.sf.anathema.hero.charms.display;

import net.sf.anathema.character.magic.basic.Magic;
import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.traits.model.TraitType;

public class MagicDisplayLabeler {
  private Resources resources;

  public MagicDisplayLabeler(Resources resources) {
    this.resources = resources;
  }

  public String getLabelForMagic(Magic magic) {
    if (magic instanceof Charm && ((Charm) magic).isInstanceOfGenericCharm()) {
      TraitType charmType = ((Charm) magic).getPrimaryTraitType();
      String baseCharmId = getGenericCharmBaseId((Charm) magic);
      return resources.getString(baseCharmId, resources.getString(charmType.getId()));
    }
    return resources.getString(magic.getId());
  }

  public String getGenericLabelForMagic(Magic magic) {
    return resources.getString(magic.getId());
  }

  public boolean supportsMagic(Magic magic) {
    if (magic == null) {
      return false;
    }
    if (magic instanceof Charm && ((Charm) magic).isInstanceOfGenericCharm()) {
      String baseCharmId = getGenericCharmBaseId((Charm) magic);
      return resources.supportsKey(baseCharmId);
    }
    return resources.supportsKey(magic.getId());
  }

  private String getGenericCharmBaseId(Charm charm) {
    return charm.getId().substring(0, charm.getId().lastIndexOf('.'));
  }
}
