/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;

/**
 *
 * @author Student
 */
  public class Login extends Registration{
      
      boolean loginStatus;
      
       public boolean loginUser(String name, String password){
        if(name.equals(userName) && password.equals(passWord)){
            loginStatus = true;
            return true;
        }else{
            loginStatus = false;
            return false;
        }
    }       
      
public String returnLoginStatus(boolean loginStatus){
    if(loginStatus){
        return"Login success";
    }else{
        return"Login failed";
    }
    }
}
   
    
    