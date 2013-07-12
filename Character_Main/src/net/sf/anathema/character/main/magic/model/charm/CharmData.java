package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.charm.duration.IDuration;
import net.sf.anathema.character.main.magic.model.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.magic.model.combos.IComboRestrictions;
import net.sf.anathema.character.main.magic.model.magic.IMagicData;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.type.ICharacterType;

import java.util.List;
import java.util.Set;

public interface CharmData extends IMagicData {

  String FAVORED_CASTE_PREFIX = "FavoredCaste.";

  ICharacterType getCharacterType();

  IDuration getDuration();

  ValuedTraitType getEssence();

  ValuedTraitType[] getPrerequisites();

  TraitType getPrimaryTraitType();

  String getGroupId();

  IComboRestrictions getComboRules();

  IndirectCharmRequirement[] getAttributeRequirements();

  Set<Charm> getParentCharms();
  
  List<String> getParentSubEffects();

  ICharmTypeModel getCharmTypeModel();
  
  boolean isInstanceOfGenericCharm();
}