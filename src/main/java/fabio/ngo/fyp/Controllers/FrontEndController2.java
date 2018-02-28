//package fabio.ngo.fyp.Controllers;
//
//import fabio.ngo.fyp.Ultilities.CompilingJavaFiles;
//import fabio.ngo.fyp.Ultilities.Constants;
//import fabio.ngo.fyp.Ultilities.ProcessResult;
//import fabio.ngo.fyp.Ultilities.RunningJavaFiles;
//import org.springframework.web.bind.annotation.*;
//
////@RestController
//@CrossOrigin("http://localhost:4200")
//public class FrontEndController2 implements Constants{
//  @RequestMapping(name="/run", method = RequestMethod.POST)
//  public ProcessResult run (@RequestBody MyRequestBody requestWrapper){
//    String CLONE_ROOT_DIR = WORKING_DIR+"\\"+requestWrapper.getToken()+"\\";
//    ConstantFacade.getInstance().writeFile(CLONE_ROOT_DIR+"src\\ast");
//    String[] jarFiles = {};
//    CompilingJavaFiles compilingJavaFiles = new CompilingJavaFiles(CLONE_ROOT_DIR+"src\\ast", CLONE_ROOT_DIR+"lib", "",jarFiles);
////    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_ROOT_DIR+"src", CLONE_ROOT_DIR+"lib", "",jarFiles);
//    compilingJavaFiles.compile();
//    ConstantFacade.getInstance().writeFile(CLONE_ROOT_DIR+"src\\frontend");
//    compilingJavaFiles = new CompilingJavaFiles(CLONE_ROOT_DIR+"src\\frontend", CLONE_ROOT_DIR+"lib", "",jarFiles);
//    RunningJavaFiles runningJavaFiles = new RunningJavaFiles(CLONE_ROOT_DIR+"src", CLONE_ROOT_DIR+"lib", "",jarFiles);
//    runningJavaFiles.run("frontend.CompilersTest", null);
//    return new ProcessResult("","","");
//  }
//}
