package fabio.ngo.fyp.Models;

public class Grammar {
  private boolean isAbstract;
  private String lhs;
  public String getLhs() {
    return lhs;
  }

  public void setLhs(String lhs) {
    this.lhs = lhs;
  }

  public String getRhs() {
    return rhs;
  }

  public void setRhs(String rhs) {
    this.rhs = rhs;
  }

  public Grammar(String lhs, String rhs, boolean isAbstract) {
    this.lhs = lhs.trim();
    this.rhs = rhs.trim();
    this.isAbstract = isAbstract;
  }

  private String rhs;

  public boolean isAbstract() {
    return isAbstract;
  }

  public void setAbstract(boolean anAbstract) {
    isAbstract = anAbstract;
  }
}
