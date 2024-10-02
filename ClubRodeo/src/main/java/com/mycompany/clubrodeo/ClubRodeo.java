/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clubrodeo;
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
                        + "3. Salir";
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

                    Partner member = new Partner(id, name, typeSubscription);
                    if (club.addMember(member)) {
                        JOptionPane.showMessageDialog(null, "Socio afiliado con éxito: " + name);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo afiliar al socio: " + name);
                    }
                    break;

                case 2:
                    String memberId = JOptionPane.showInputDialog("Ingrese la cédula del socio:");
                    boolean memberExists = false;

                    for (int i = 0; i < club.getMembers().size(); i++) {
                        if (club.getMembers().get(i).getId().equals(memberId)) {
                            memberExists = true;

                            String authorizedPersonId = JOptionPane.showInputDialog("Ingrese la cédula de la persona autorizada:");
                            String authorizedPersonName = JOptionPane.showInputDialog("Ingrese el nombre de la persona autorizada:");

                            AuthorizedPerson authorizedPerson = new AuthorizedPerson(authorizedPersonId, authorizedPersonName);

                            club.getMembers().get(i).addAuthorizedPerson(authorizedPerson);

                            break;
                        }
                    }

                    if (memberExists == false) 
                        JOptionPane.showMessageDialog(null, "No existe un socio con la cédula proporcionada.");

                    break;
                    
                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        } while (option != 3);
    }
}