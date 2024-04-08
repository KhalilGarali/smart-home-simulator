package main.java.logic.modules;

import main.java.logic.MediatorPattern.Component;

import java.util.Objects;

public class Mediator implements main.java.logic.MediatorPattern.Mediator {
    SHC shc = SHC.getIntance();
    SHS shs = SHS.getInstance();
    SHP shp = SHP.getInstance(shc);
    SHH shh = SHH.getInstance(shc);


    public void notifying(Component c, String message) {
        if( c instanceof SHH){
            if(Objects.equals(message, "")){

            }
            if(Objects.equals(message, "")){

            }
        }
        if( c instanceof SHC){
            if(Objects.equals(message, "")){

            }
            if(Objects.equals(message, "")){

            }
        }
        if( c instanceof SHP){
            if(Objects.equals(message, "HouseIsEmpty")){
                closeAllOpenings();
            }
            if(Objects.equals(message, "HouseIsNotEmpty")){
                openAllOpenings();
            }
        }
        if( c instanceof SHS){
            if(Objects.equals(message, "Empty House")){
                houseIsEmpty();
            }
            if(Objects.equals(message, "Not Empty House")){
                houseIsNotEmpty();
            }
        }
    }

    private void openAllOpenings() {
        shc.openAllOpenings();
    }

    private void closeAllOpenings() {
        shc.closeAllOpenings();
    }

    private void houseIsEmpty() {
        shp.houseIsEmpty();
    }
    private void houseIsNotEmpty() {
        shp.houseIsNotEmpty();
    }

}
