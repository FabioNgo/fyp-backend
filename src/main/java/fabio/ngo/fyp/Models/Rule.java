package fabio.ngo.fyp.Models;

public class Rule {
  private String keyword;
  private String token;
  private boolean isIdentifier;
  private String javaType;
  public boolean isIgnore() {
    return ignore;
  }

  public void setIgnore(String ignore) {
    if("true".equals(ignore)){
      this.ignore = true;
    }else{
      this.ignore = false;
    }
  }

  private boolean ignore;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keword) {
    this.keyword = keword;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token.trim();
  }


  public boolean isIdentifier() {
    return isIdentifier;
  }

  public void setIsIdentifier(String identifier) {
    if (identifier.equals("true")) {
      this.isIdentifier = true;
    }else{
      this.isIdentifier = false;
    }
  }

  public String getJavaType() {
    return javaType;
  }

  public void setJavaType(String javaType) {
    if(javaType == null){
      this.javaType = "";
    }else{
      this.javaType = javaType;
    }

  }
}
