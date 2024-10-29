/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clubrodeo;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author jcabrera
 */
public class ClubRodeo {

    public static void main(String[] args) {
        Club club = new Club();

        int option;
        do {
            String menu = "BIENVENIDO AL CLUB RODEO\n"
                        + "1. Afiliar un socio\n"
                        + "2. Registrar una persona autorizada por un socio\n"
                        + "3. Registrar consumo para un socio\n"
                        + "4. Pagar factura de un socio\n"
                        + "5. Aumentar fondos de un socio\n"
                        + "6. Eliminar un socio\n"
                        + "7. Mostrar todos los datos\n"
                        + "8. Salir";
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (option) {
                case 1:
                    String id = JOptionPane.showInputDialog("Ingrese la cédula del socio:");

                    if (club.existMember(id)) {
                        JOptionPane.showMessageDialog(null, "Ya existe un socio con la cédula " + id + ". No se puede registrar nuevamente.");
                        break;
                    }

                    String name = JOptionPane.showInputDialog("Ingrese el nombre del socio:");
                    String typeSubscription = JOptionPane.showInputDialog("Ingrese el tipo de suscripción (VIP o Regular):");

                    Partner member = new Partner(id, name, typeSubscription); // Crear un nuevo objeto Partner (socio) con los datos ingresados
                    if (club.addMember(member)) { // Intentar agregar el nuevo socio al club
                        JOptionPane.showMessageDialog(null, "Socio afiliado con éxito: " + name);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo afiliar al socio: " + name);
                    }
                    break;

                case 2:
                    String memberId = JOptionPane.showInputDialog("Ingrese la cédula del socio:");
                    boolean memberExists = false;

                    for (int i = 0; i < club.getMembers().size(); i++) { // Iterar sobre la lista de miembros para encontrar al socio con la cédula ingresada
                        if (club.getMembers().get(i).getId().equals(memberId)) { // Verificar si el ID del socio coincide con el ID ingresado
                            memberExists = true;

                            String authorizedPersonId = JOptionPane.showInputDialog("Ingrese la cédula de la persona autorizada:");
                            String authorizedPersonName = JOptionPane.showInputDialog("Ingrese el nombre de la persona autorizada:");

                            AuthorizedPerson authorizedPerson = new AuthorizedPerson(authorizedPersonId, authorizedPersonName); // Crear un nuevo objeto AuthorizedPerson (persona autorizada)

                            club.getMembers().get(i).addAuthorizedPerson(authorizedPerson); // Agregar la persona autorizada al socio correspondiente

                            break;
                        }
                    }

                    if (memberExists == false) 
                        JOptionPane.showMessageDialog(null, "No existe un socio con la cédula proporcionada.");

                    break;
                    
                case 3:
                    String memberIdForConsumption = JOptionPane.showInputDialog("Ingrese la cédula del socio:");
                    String concept = JOptionPane.showInputDialog("Ingrese el concepto del consumo:");
                    double value = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del consumo:"));

                    club.addInvoiceToMember(memberIdForConsumption, concept, value);
                    break;

                case 4:
                    String memberIdForInvoicePayment = JOptionPane.showInputDialog("Ingrese la cédula del socio:");
                    Partner memberForInvoice = club.findMemberById(memberIdForInvoicePayment);

                    if (memberForInvoice != null) {
                        ArrayList<Invoice> pendingInvoices = memberForInvoice.getPendingInvoices();
                        if (!pendingInvoices.isEmpty()) {
                            Invoice invoiceToPay = pendingInvoices.get(0); // Selección de la primera factura pendiente
                            club.payMemberInvoice(memberIdForInvoicePayment, invoiceToPay);
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay facturas pendientes para el socio.");
                        }
                    }
                    break;

                case 5:
                    String memberIdForFunds = JOptionPane.showInputDialog("Ingrese la cédula del socio:");
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad a aumentar:"));

                    club.increaseMemberFunds(memberIdForFunds, amount);
                    break;
                    
                case 6:
                    String idToRemove = JOptionPane.showInputDialog("Ingrese la cedula del socio a eliminar:");
                        club.removeMember(idToRemove);
                    break;
                    
                case 7:
                    club.showInformation();
                
                case 8:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        } while (option != 6);
    }
    
    private static Partner getMemberById(Club club, String id) {
        for (Partner member : club.getMembers()) {
            if(member.getId().equals(id)) {
                return member;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro un socio con esa cedula");
        return null;
    }
}