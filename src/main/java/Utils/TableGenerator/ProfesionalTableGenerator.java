package Utils.TableGenerator;

import Managers.MontoManager;
import Objetos.*;

import javax.swing.*;

import Objetos.Monto;
import java.util.List;

public class ProfesionalTableGenerator {
    private static ProfesionalTableGenerator profesionalTableGenerator;

    public static ProfesionalTableGenerator getInstance(){
        if(profesionalTableGenerator ==null){
            profesionalTableGenerator= new ProfesionalTableGenerator();
        }
        return profesionalTableGenerator;
    }

    private  ProfesionalTableGenerator(){

    }



    public List <Monto> getMontoByIdProfesional(int idProfesional){

        List <Monto> montoList = MontoManager.getInstance().getMontoByIdProfesional(idProfesional);

        return montoList;

    }

    
    }
