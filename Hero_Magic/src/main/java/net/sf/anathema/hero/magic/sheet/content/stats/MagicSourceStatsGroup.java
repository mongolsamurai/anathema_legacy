package net.sf.anathema.hero.magic.sheet.content.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.main.magic.sheet.content.IMagicStats;
import net.sf.anathema.hero.sheet.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class MagicSourceStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final Resources resources;

  public MagicSourceStatsGroup(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    String text = stats == null ? null : stats.getSourceString(resources);
    table.addCell(createTextCell(font, text));
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{2.0f};
  }

  @Override
  public String getTitle() {
    return resources.getString("Sheet.Magic.Source");
  }
}
