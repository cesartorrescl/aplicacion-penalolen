package com.example.cesar.muni;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import java.io.Console;

/**
 * Created by Cesar on 06-12-2017.
 */

public class login_controller {

    public boolean iniciar_sesion(String user,String pass){
        if(user.equals("usuario") && pass.equals("clave")){
            return true;
        }
        else{
            return false;
        }
    }



}
