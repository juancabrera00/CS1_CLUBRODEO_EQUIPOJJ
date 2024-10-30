/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clubrodeo;

/**
 *
 * @author jcabrera
 */
public interface Payable {
    boolean payInvoice(Invoice invoice);
    boolean registerConsumption(String concept, double value);
}
