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
    private ArrayList<AuthorizedPerson> Peopleauthorized; // Lista de personas autorizadas por el socio
    private ArrayList<Invoice> pendingInvoices; // Lista de facturas pendientes
    
    public Partner(String id, String name, String typeSubscription) {
        super(id,name);
        this.typeSubscription = typeSubscription;
        if (typeSubscription.equals("VIP")){
            this.funds = 100000;
        } else {
            this.funds = 50000;
        }
        
        this.Peopleauthorized = new ArrayList<>();// Inicializar la lista de personas autorizadas
        this.pendingInvoices = new ArrayList<>();
    }
    
    public boolean registerConsumption(String concept, double value) { // Método para registrar un consumo
        if (this.funds >= value) {
            Invoice invoice = new Invoice(concept, value, this.getName(), false);
            this.pendingInvoices.add(invoice);
            JOptionPane.showMessageDialog(null, "Consumo registrado exitosamente.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Fondos insuficientes para registrar el consumo.");
            return false;
        }
    }
    
    
    public boolean payInvoice(Invoice invoice) { // Método para pagar una factura
        if (this.funds >= invoice.getValue() && !invoice.isPaid()) {
            this.funds -= invoice.getValue();
            invoice.setPaid(true);
            this.pendingInvoices.remove(invoice);
            JOptionPane.showMessageDialog(null, "Factura pagada exitosamente.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Fondos insuficientes para pagar la factura.");
            return false;
        }
    }
    
    public boolean increaseFunds(double amount) {
        double maxLimit = this.typeSubscription.equals("Regular") ? 1000000 : 5000000;
        if ((this.funds + amount) <= maxLimit) {
            this.funds += amount;
            JOptionPane.showMessageDialog(null, "Fondos aumentados exitosamente.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se puede aumentar, el monto excede el límite permitido.");
            return false;
        }
    }
    
    public ArrayList<Invoice> getPendingInvoices() {
        return pendingInvoices;
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
    
    
    public boolean registerAuthorizedConsumption(String authorizedId, String concept, double value) {
        for (AuthorizedPerson person : Peopleauthorized) {
            if (person.getId().equals(authorizedId)) {
                if (this.funds >= value) {
                    Invoice invoice = new Invoice(concept, value, person.getName(), false);
                    this.pendingInvoices.add(invoice);
                    JOptionPane.showMessageDialog(null, "Consumo registrado exitosamente para la persona autorizada: " + person.getName());
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Fondos insuficientes para registrar el consumo.");
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Persona autorizada no encontrada.");
        return false;
    }
} 