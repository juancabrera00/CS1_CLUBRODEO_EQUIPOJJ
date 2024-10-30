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
                        + "4. Registrar consumo de persona autorizada\n"
                        + "5. Pagar factura de un socio\n"
                        + "6. Aumentar fondos de un socio\n"
                        + "7. Eliminar un socio\n"
                        + "8. Mostrar todos los datos\n"
                        + "9. Salir";
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (option) {
                case 1: // Afiliar socio
                    String id;
                    do {
                        id = JOptionPane.showInputDialog("Ingrese la cédula del socio (solo números):");
                    } while (!id.matches("\\d+")); // Verifica que la cédula contenga solo números

                    if (club.existMember(id)) {
                        JOptionPane.showMessageDialog(null, "Ya existe un socio con la cédula " + id + ". No se puede registrar nuevamente.");
                        break;
                    }

                    String name = JOptionPane.showInputDialog("Ingrese el nombre del socio:");
                    String typeSubscription = JOptionPane.showInputDialog("Ingrese el tipo de suscripción (VIP o Regular):");

                    Partner member = new Partner(id, name, typeSubscription);
                    if (club.addMember(member)) {
                        JOptionPane.showMessageDialog(null, "Socio afiliado con éxito: " + name);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo afiliar al socio: " + name);
                    }
                    break;

                case 2: // Agregar persona autorizada
                    Partner memberForAuthorized = findMemberById(club, JOptionPane.showInputDialog("Ingrese la cédula del socio:"));
                    if (memberForAuthorized != null) {
                        String authorizedPersonId = JOptionPane.showInputDialog("Ingrese la cédula de la persona autorizada:");
                        String authorizedPersonName = JOptionPane.showInputDialog("Ingrese el nombre de la persona autorizada:");

                        AuthorizedPerson authorizedPerson = new AuthorizedPerson(authorizedPersonId, authorizedPersonName);
                        memberForAuthorized.addAuthorizedPerson(authorizedPerson);
                    }
                    break;

                case 3: // Registrar consumo
                    Partner memberForConsumption = findMemberById(club, JOptionPane.showInputDialog("Ingrese la cédula del socio:"));
                    if (memberForConsumption != null) {
                        String concept = JOptionPane.showInputDialog("Ingrese el concepto del consumo:");
                        double value = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del consumo:"));
                        club.addInvoiceToMember(memberForConsumption.getId(), concept, value);
                    }
                    break;

                case 4: // Registrar consumo de persona autorizada
                    Partner memberForAuthConsumption = findMemberById(club, JOptionPane.showInputDialog("Ingrese la cédula del socio:"));
                    if (memberForAuthConsumption != null) {
                        String authorizedId = JOptionPane.showInputDialog("Ingrese la cédula de la persona autorizada:");
                        String conceptAuthConsumption = JOptionPane.showInputDialog("Ingrese el concepto del consumo:");
                        double valueAuthConsumption = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del consumo:"));
                        club.addAuthorizedConsumption(memberForAuthConsumption.getId(), authorizedId, conceptAuthConsumption, valueAuthConsumption);
                    }
                    break;

                case 5: // Pagar factura
                    Partner memberForInvoice = findMemberById(club, JOptionPane.showInputDialog("Ingrese la cédula del socio:"));
                    if (memberForInvoice != null) {
                        ArrayList<Invoice> pendingInvoices = memberForInvoice.getPendingInvoices();
                        if (!pendingInvoices.isEmpty()) {
                            Invoice invoiceToPay = pendingInvoices.get(0); // Selección de la primera factura pendiente
                            club.payMemberInvoice(memberForInvoice.getId(), invoiceToPay);
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay facturas pendientes para el socio.");
                        }
                    }
                    break;

                case 6: // Aumentar fondos
                    Partner memberForFunds = findMemberById(club, JOptionPane.showInputDialog("Ingrese la cédula del socio:"));
                    if (memberForFunds != null) {
                        double amount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad a aumentar:"));
                        club.increaseMemberFunds(memberForFunds.getId(), amount);
                    }
                    break;

                case 7: // Eliminar socio
                    String idToRemove = JOptionPane.showInputDialog("Ingrese la cédula del socio a eliminar:");
                    club.removeMember(idToRemove);
                    break;

                case 8: // Mostrar información
                    club.showInformation();
                    break;

                case 9: // Salida del sistema
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        } while (option != 9);
    }

    // Método auxiliar para buscar un socio por ID y validar su existencia
    private static Partner findMemberById(Club club, String id) {
        for (Partner member : club.getMembers()) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró un socio con esa cédula.");
        return null;
    }
}