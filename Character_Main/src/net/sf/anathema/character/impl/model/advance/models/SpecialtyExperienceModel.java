package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.model.abilities.AbilityModel;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class SpecialtyExperienceModel extends AbstractIntegerValueModel {

  private Hero hero;
  private final AbilityModel abilityModel;

  public SpecialtyExperienceModel(Hero hero) {
    super("Experience", "Specialties");
    this.hero = hero;
    this.abilityModel = AbilityModelFetcher.fetch(hero);
  }

  @Override
  public Integer getValue() {
    return getSpecialtyCosts();
  }

  private int getSpecialtyCosts() {
    int experienceCosts = 0;
    for (Trait ability : abilityModel.getAll()) {
      experienceCosts += getExperienceDots(ability) * getCostPerSpecialtyDot(ability);
    }
    return experienceCosts;
  }

  private int getExperienceDots(Trait ability) {
    ISubTraitContainer specialtiesContainer = getSpecialtyContainer(ability);
    return specialtiesContainer.getExperienceDotTotal();
  }

  private ISubTraitContainer getSpecialtyContainer(Trait ability) {
    ISpecialtiesConfiguration specialtyConfiguration = abilityModel.getSpecialtyConfiguration();
    return specialtyConfiguration.getSpecialtiesContainer(ability.getType());
  }

  private double getCostPerSpecialtyDot(Trait ability) {
    IExperiencePointCosts experienceCost = hero.getTemplate().getExperienceCost();
    boolean casteOrFavored = ability.getFavorization().isCasteOrFavored();
    return experienceCost.getSpecialtyCosts(casteOrFavored);
  }
}