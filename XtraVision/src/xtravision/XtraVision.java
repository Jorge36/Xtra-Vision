/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtravision;

import ApplicationClasses.MaintainSMList.Machine;
import Controller.Controller;
import Model.SetUp;
import View.View;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author
 */
public class XtraVision {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      View view = new View();
      SetUp setUp = new SetUp();
         
      List<Machine> machines = setUp.getAllMachines();
      
      List<String> options = new LinkedList<>();
      
      for (Machine m: machines) {
          
          options.add(m.toString());
          
      }
      
      int option = view.chooseOption(options, "From what machine would you like to operate?", "Quit program");

      if (option >= 1 && option <= machines.size()) {
        Controller controller = new Controller(view, machines.get(option - 1).getEircode(), setUp);      
        controller.executeProgram();       
      }
             
     
    }
    
}
