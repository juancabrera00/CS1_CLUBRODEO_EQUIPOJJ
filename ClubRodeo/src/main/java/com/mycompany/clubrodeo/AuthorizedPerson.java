/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubrodeo;

import javax.swing.JOptionPane;

/**
 *
 * @author YerKi
 */
public class AuthorizedPerson extends Person implements Consumable {
    public AuthorizedPerson(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean registerConsumption(String concept, double value) { // Lógica de consumo para una persona autorizada si se requiere
        JOptionPane.showMessageDialog(null, "Registro de consumo para persona autorizada: " + getName());
        return true;
    }

    public void showInfo() {
        JOptionPane.showMessageDialog(null, "Persona Autorizada ID: " + getId() + ", Nombre: " + getName());
    }
}
