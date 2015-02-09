package old;



import org.nocrala.tools.texttablefmt.Table;

public class Basic {

  public static void main(final String[] args) {
    Table t = new Table(4);
    t.addCell("Porco");
    t.addCell("dio");
    t.addCell("Year");
    t.addCell("Jamiroquai");
    t.addCell("Emergency on Planet Earth");
    t.addCell("1993");
    t.addCell("Jamiroquai");
    t.addCell("The Return of the Space Cowboy");
    t.addCell("1994");
    String render = t.render();
    System.out.println(render);
  }

}
