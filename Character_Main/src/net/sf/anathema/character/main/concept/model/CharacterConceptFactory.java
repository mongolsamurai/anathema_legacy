package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.CharacterModelFactory;
import net.sf.anathema.character.main.model.template.TemplateFactory;

@CharacterModelAutoCollector
public class CharacterConceptFactory extends SimpleModelTreeEntry implements CharacterModelFactory {

  public CharacterConceptFactory() {
    super(CharacterConcept.ID);
  }

  @Override
  public CharacterConceptImpl create(TemplateFactory templateFactory) {
    return new CharacterConceptImpl();
  }
}