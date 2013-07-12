package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttributeImpl;
import net.sf.anathema.character.main.magic.model.charmtree.CharmAttributeRequirement;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GenericAttributeRequirementBuilderTest {

  @Test
  public void testReadGenericAttributeRequirement() throws Exception {
    String xml = "<element><genericCharmAttributeRequirement attribute=\"generic\"/></element>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAttributeRequirementBuilder builder = new GenericAttributeRequirementBuilder();
    builder.setType(AbilityType.Investigation);
    IndirectCharmRequirement[] indirectRequirements = builder.getCharmAttributeRequirements(rootElement);
    assertTrue(ArrayUtils.contains(indirectRequirements, new CharmAttributeRequirement(new MagicAttributeImpl("genericInvestigation", false), 1)));
  }
}