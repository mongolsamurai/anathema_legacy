package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TraitGroupCost {

  private final List<Trait> unhandledTraits;
  private final TraitGroup group;
  private int bonusPointsSpent = 0;
  private int dotsSpent = 0;

  public TraitGroupCost(TraitGroup group) {
    this.group = group;
    this.unhandledTraits = new ArrayList<>(Arrays.asList(group.getGroupTraits()));
  }

  public TraitGroup getGroup() {
    return group;
  }

  public Trait[] getTraits() {
    return group.getGroupTraits();
  }

  public void addTraitToCost(Trait trait, ElementCreationCost cost) {
    if (!unhandledTraits.contains(trait)) {
      throw new IllegalArgumentException("Trait " + trait.getType().getId() + " not expected.");
    }
    dotsSpent += cost.getDotsSpent();
    bonusPointsSpent += cost.getBonusPointsSpent();
    unhandledTraits.remove(trait);
  }

  public int getDotsSpent() {
    return dotsSpent;
  }

  public int getBonusPointsSpent() {
    return bonusPointsSpent;
  }
}