package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;

public class AttributeTemplateFactory implements TypedTraitTemplateFactory {
  private ITraitTemplateFactory traitTemplateFactory;

  public AttributeTemplateFactory(ITraitTemplateFactory traitTemplateFactory) {
    this.traitTemplateFactory = traitTemplateFactory;
  }

  @Override
  public ITraitTemplate create(ITraitType type) {
    return traitTemplateFactory.createAttributeTemplate((AttributeType) type);
  }
}