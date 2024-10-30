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
    private ArrayList<Partner> members; // Lista para almacenar los socios del club
    
    public Club() {
        members = new ArrayList<>();// Inicializar la lista de socios
    }
    
    public boolean existMember(String id) { // Método para verificar si un socio ya existe en el club
        for (int i = 0; i < members.size(); i++) {
            Partner m = members.get(i); // Obtener el socio en la posición i
            if (m.getId().equals(id)) { // Comparar el ID del socio con el ID proporcionado
                return true;
            }
        }
        return false;
    }
    
    public Partner findMemberById(String id) { // Método para buscar un socio por Id
        for (Partner m : members) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }
    
    public boolean addMember(Partner m) { // Método para agregar un miembro
        if (members.size() >= 35) {
            JOptionPane.showMessageDialog(null,"El club ya alcanzo el limite de 35 socios");
            return false;
        }
        
        for (int i = 0; i < members.size(); i++) { // Verificar si ya existe un socio con el mismo ID
            if (members.get(i).getId().equals(m.getId())) {
                JOptionPane.showMessageDialog(null, "Ya existe un socio con esa cedula");
                return false;
            }
        }

        int vipCount = 0;
        for (Partner i : members) { // Contar cuántos socios VIP ya existen en el club
            if (i.getTypeSubscription().equals("VIP")) {
                vipCount++;
            }
        }
        
        if (m.getTypeSubscription().equals("VIP") && vipCount >= 3) { // Verificar si se intenta agregar un socio VIP y ya hay 3 socios VIP
            JOptionPane.showMessageDialog(null,"No se pueden agregar mas de 3 socios VIP");
            return false;
        }
        
        members.add(m); //Agregar el nuevo socio a la lista
        return true;
    }
     
    public ArrayList<Partner> getMembers() { // Método para obtener la lista de socios
        return members;
    }
    
    public boolean addInvoiceToMember(String memberId, String concept, double value) { // Método para registrar factura al socio
        Partner member = findMemberById(memberId);
        if (member != null) {
            return member.registerConsumption(concept, value);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un socio con la cédula " + memberId);
            return false;
        }
    }
    
    public boolean payMemberInvoice(String memberId, Invoice invoice) { // Método para pagar una factura
        Partner member = findMemberById(memberId); //Validacion si existe el socio
        if (member != null) {
            return member.payInvoice(invoice);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un socio con la cédula " + memberId);
            return false;
        }
    }
    
    public boolean increaseMemberFunds(String memberId, double amount) { // Método para incrementar fondos
        Partner member = findMemberById(memberId);
        if (member != null) {
            return member.increaseFunds(amount);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un socio con la cédula " + memberId);
            return false;
        }
    }
    
    public boolean removeMember(String id) { // Método para eliminar un socio
        for (Partner partner : members) {
            if (partner.getId().equals(id)) {
                // Validación: El socio no debe ser de tipo VIP
                if ("VIP".equals(partner.getTypeSubscription())) {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar un socio VIP.");
                    return false;
                }

                // Validación: El socio no debe tener más de una persona autorizada
                if (partner.getPersonAuthorized().size() > 1) {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar un socio con más de una persona autorizada.");
                    return false;
                }

                // Validación: El socio no debe tener facturas pendientes
                for (Invoice invoice : partner.getPendingInvoices()) {
                    if (!invoice.isPaid()) {
                        JOptionPane.showMessageDialog(null, "El socio tiene facturas pendientes. No se puede eliminar.");
                        return false;
                    }
                }

                members.remove(partner);
                JOptionPane.showMessageDialog(null, "Socio eliminado exitosamente.");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró un socio con esa cédula.");
        return false;
    }

    public boolean addAuthorizedConsumption(String memberId, String authorizedId, String concept, double value) { // Método para consumo persona autorizada
        Partner member = findMemberById(memberId);
        if (member != null) {
            return member.registerAuthorizedConsumption(authorizedId, concept, value);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un socio con la cédula " + memberId);
            return false;
        }
    }
    
    public void showInformation() { // Método mostrar informacion del sistema
        if (members.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay socios registrados en el club.");
            return;
        }

        for (Partner partner : members) {
            String info = "Socio ID: " + partner.getId() + "\n" +
                          "Nombre: " + partner.getName() + "\n" +
                          "Tipo de Suscripción: " + partner.getTypeSubscription() + "\n" +
                          "Fondos: $" + partner.getFunds() + "\n\n" +
                          "Personas Autorizadas:\n";

            if (partner.getPersonAuthorized().isEmpty()) {
                info += "- No hay personas autorizadas\n";
            } else {
                for (AuthorizedPerson authorized : partner.getPersonAuthorized()) {
                    info += "  - ID: " + authorized.getId() + ", Nombre: " + authorized.getName() + "\n";
                }
            }

            info += "\nFacturas Pendientes:\n";
            if (partner.getPendingInvoices().isEmpty()) {
                info += "- No hay facturas pendientes\n";
            } else {
                for (Invoice invoice : partner.getPendingInvoices()) {
                    info += "  - Concepto: " + invoice.getConcept() +
                            ", Valor: $" + invoice.getValue() +
                            ", Pagada: " + (invoice.isPaid() ? "Sí" : "No") + "\n";
                }
            }

            JOptionPane.showMessageDialog(null, info);
        }
    }
}