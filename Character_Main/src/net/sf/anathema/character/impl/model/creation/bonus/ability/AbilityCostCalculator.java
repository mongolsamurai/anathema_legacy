package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.impl.model.creation.bonus.additional.IAdditionalBonusPointManagment;
import net.sf.anathema.character.impl.model.creation.bonus.additional.IAdditionalSpecialtyBonusPointManagement;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.AbstractFavorableTraitCostCalculator;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.character.main.model.abilities.AbilityModel;

import java.util.ArrayList;
import java.util.List;

public class AbilityCostCalculator extends AbstractFavorableTraitCostCalculator implements IAbilityCostCalculator {

  private final IAdditionalSpecialtyBonusPointManagement additionalPools;
  private final AbilityModel abilityModel;
  private final AbilityPointCosts costs;
  private int specialtyBonusPointCosts;
  private int specialtyDotSum;
  private SpecialtyCalculator specialtyCalculator;

  public AbilityCostCalculator(AbilityModel abilityModel, IFavorableTraitCreationPoints points, int specialtyPoints,
                               AbilityPointCosts costs, IAdditionalBonusPointManagment additionalPools) {
    super(additionalPools, points, costs.getMaximumFreeAbilityRank(), abilityModel.getAll());
    this.abilityModel = abilityModel;
    this.costs = costs;
    this.additionalPools = additionalPools;
    this.specialtyCalculator = new SpecialtyCalculator(abilityModel, specialtyPoints);
  }

  @Override
  public void calculateCosts() {
    super.calculateCosts();
    calculateSpecialtyCosts();
  }

  private void calculateSpecialtyCosts() {
    IGenericSpecialty[] specialties = createGenericSpecialties();
    specialtyDotSum = specialtyCalculator.getSpecialtyPointsSpent(specialties);
    specialtyBonusPointCosts = specialtyCalculator.getSpecialtyCosts(specialties);
    additionalPools.spendOn(specialties, costs);
  }

  @Override
  protected void clear() {
    super.clear();
    specialtyDotSum = 0;
    specialtyBonusPointCosts = 0;
  }

  private IGenericSpecialty[] createGenericSpecialties() {
    List<IGenericSpecialty> specialties = new ArrayList<>();
    for (Trait ability : getTraits()) {
      ISpecialtiesConfiguration specialtyConfiguration = abilityModel.getSpecialtyConfiguration();
      for (Specialty specialty : specialtyConfiguration.getSpecialtiesContainer(ability.getType()).getSubTraits()) {
        for (int index = 0; index < specialty.getCalculationValue(); index++) {
          specialties.add(new GenericSpecialty(ability));
        }
      }
    }
    return specialties.toArray(new IGenericSpecialty[specialties.size()]);
  }

  @Override
  protected int getCostFactor(Trait trait) {
    ITraitFavorization favorization = trait.getFavorization();
    return costs.getAbilityCosts(favorization.isCasteOrFavored()).getRatingCosts(trait.getCalculationValue());
  }

  @Override
  public int getSpecialtyBonusPointCosts() {
    return specialtyBonusPointCosts;
  }

  @Override
  public int getFreeSpecialtyPointsSpent() {
    return specialtyDotSum;
  }
}