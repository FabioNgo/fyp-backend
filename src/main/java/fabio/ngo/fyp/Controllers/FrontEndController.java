package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Managers.FrontEndContentManager;
import fabio.ngo.fyp.Managers.GrammarsManager;
import fabio.ngo.fyp.Managers.TerminalsManager;
import fabio.ngo.fyp.Models.FrontEndBodyRequest;
import fabio.ngo.fyp.Ultilities.Constants;
import fabio.ngo.fyp.Ultilities.ProcessResult;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin("http://localhost:4200")
public class FrontEndController implements Constants{
  @RequestMapping(name="/create", method = RequestMethod.POST)
  public ProcessResult create(@RequestBody FrontEndBodyRequest requestWrapper){
    File src = new File(RESOURCE_DIR_ROOT);
    File dst = new File(WORKING_DIR+"\\"+requestWrapper.getToken());
    dst.mkdir();

    FrontEndContentManager.getInstance().setFrontEndContentRequest(requestWrapper.getContent());
    TerminalsManager.getInstance().generateSemanticAction();
    GrammarsManager.getInstance().generateGrammarRule();
    //copy files
    try {
      FileSystemUtils.copyRecursively(src,dst);
      JflexFacade.getInstance().writeFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\src\\");
      JflexFacade.getInstance().generateLexerFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\");
      BeaverFacade.getInstance().writeFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\src\\");
      BeaverFacade.getInstance().generateBeaverFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\");
      GrammarFacade.getInstance().writeFile(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\src\\");
      ASTFacade.getInstance().generateAST(WORKING_DIR+"\\"+requestWrapper.getToken()+"\\");

    } catch (IOException e) {
      e.printStackTrace();
    }
//    ArrayList<String> semanticActions = FrontEndContentManager.getInstance().getSemanticAction();
//    for (String semanticRule: semanticActions){
//
////      System.out.println(semanticRule);
//    }
    System.out.println("*****************************************************");
//    ArrayList<String> semanticrules = GrammarsManager.getInstance().getStringContent();
//    for (String semanticRule: semanticrules){
//
////      System.out.println(semanticRule);
//    }
    return new ProcessResult("","","");
  }
}
