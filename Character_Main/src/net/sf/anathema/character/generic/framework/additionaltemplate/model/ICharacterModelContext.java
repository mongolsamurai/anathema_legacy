package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.model.ICharacter;

import java.util.List;

public interface ICharacterModelContext {

  ICharacter getHero();

  IAdditionalRules getAdditionalRules();

  IBasicCharacterData getBasicCharacterContext();

  IPresentationProperties getPresentationProperties();

  ICharacterListening getCharacterListening();

  ICharmContext getCharmContext();

  IMagicCollection getMagicCollection();

  IGenericTraitCollection getTraitCollection();

  TraitContext getTraitContext();

  IGenericSpecialtyContext getSpecialtyContext();

  IAdditionalModel getAdditionalModel(String id);

  <T> List<T> getAllRegistered(Class<T> interfaceClass);
}