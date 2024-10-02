/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubrodeo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jcabrera
 */
public class Partner extends Person{
    private double funds;
    private String typeSubscription;
    private ArrayList<AuthorizedPerson> Peopleauthorized;

    public Partner(String id, String name, String typeSubscription) {
        super(id,name);
        this.typeSubscription = typeSubscription;
        if (typeSubscription.equals("VIP")){
            this.funds = 100000;
        } else {
            this.funds = 50000;
        }
        
        this.Peopleauthorized = new ArrayList<>();
    }
    
    /**
     * @return the funds
     */
    public double getFunds() {
        return funds;
    }

    /**
     * @param funds the funds to set
     */
    public void setFunds(double funds) {
        this.funds = funds;
    }

    /**
     * @return the typeSubscription
     */
    public String getTypeSubscription() {
        return typeSubscription;
    }

    /**
     * @param typeSubscription the typeSubscription to set
     */
    public void setTypeSubscription(String typeSubscription) {
        this.typeSubscription = typeSubscription;
    }
    
    public boolean addAuthorizedPerson(AuthorizedPerson person) {
        if(Peopleauthorized.size() < 10) {
            Peopleauthorized.add(person);
            JOptionPane.showMessageDialog(null, "Persona autorizada agregada con exito");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas de 10 personas autorizadas");
            return false;
        }
    }
    
    public ArrayList<AuthorizedPerson> getPersonAuthorized(){
        return Peopleauthorized;
    }
}
