package fabio.ngo.fyp.Controllers;

import fabio.ngo.fyp.Managers.FrontEndContentManager;
import fabio.ngo.fyp.Models.FrontEndBodyRequest;
import fabio.ngo.fyp.Ultilities.ProcessResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class FrontEndController {
  @RequestMapping(name="/create", method = RequestMethod.POST)
  public ProcessResult create(@RequestBody FrontEndBodyRequest requestWrapper){
    FrontEndContentManager.getInstance().setFrontEndContentRequest(requestWrapper.getContent());
//    ArrayList<Terminal> terminals = FrontEndContentManager.getInstance().getFrontEndContentRequest().getTerminals();
//    for (Terminal terminal: terminals){
//      terminal.addSemanticAction();
//      System.out.println(terminal.getSemanticActionString());
//    }
    return new ProcessResult("","","");
  }
}
