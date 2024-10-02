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
public class Club {
    private ArrayList<Partner> members;
    
    public Club() {
        members = new ArrayList<>();
    }
    
    public boolean existMember(String id) {
        for (int i = 0; i < members.size(); i++) {
            Partner m = members.get(i);
            if (m.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addMember(Partner m) {
        if (members.size() >= 35) {
            JOptionPane.showMessageDialog(null,"El club ya alcanzo el limite de 35 socios");
            return false;
        }
        
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(m.getId())) {
                JOptionPane.showMessageDialog(null, "Ya existe un socio con esa cedula");
                return false;
            }
        }

        int vipCount = 0;
        for (Partner i : members) {
            if (i.getTypeSubscription().equals("VIP")) {
                vipCount++;
            }
        }
        
        if (m.getTypeSubscription().equals("VIP") && vipCount >= 3) {
            JOptionPane.showMessageDialog(null,"No se pueden agregar mas de 3 socios VIP");
            return false;
        }
        
        members.add(m);
        return true;
    }
    
    public ArrayList<Partner> getMembers() {
        return members;
    }
}
