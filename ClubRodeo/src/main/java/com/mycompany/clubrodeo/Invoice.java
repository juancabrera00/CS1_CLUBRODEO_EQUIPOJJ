/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubrodeo;

/**
 *
 * @author jcabrera
 */
public class Invoice {
    private String concept;
    private double value;
    private String partnername;
    private boolean paid;

    public Invoice(String concept, double value, String partnername, boolean paid) {
        this.concept = concept;
        this.value = value;
        this.partnername = partnername;
        this.paid = paid;
    }

    /**
     * @return the concept
     */
    public String getConcept() {
        return concept;
    }

    /**
     * @param concept the concept to set
     */
    public void setConcept(String concept) {
        this.concept = concept;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return the partnername
     */
    public String getPartnername() {
        return partnername;
    }

    /**
     * @param partnername the partnername to set
     */
    public void setPartnername(String partnername) {
        this.partnername = partnername;
    }

    /**
     * @return the paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    public void pagarFactura(double funds) {
        if (funds >= value) {
            this.paid = true;
        }
    }
}
