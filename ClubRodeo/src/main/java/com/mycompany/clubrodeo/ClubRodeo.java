/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clubrodeo;
import javax.swing.JOptionPane;
import java.util.List;

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
                        + "3. Ver miembros agregados\n"
                        + "4. Salir";
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (option) {
                case 1:
                    String id;
                    do {
                        id = JOptionPane.showInputDialog("Ingrese la cédula del socio (solo números):");
                        if (!id.matches("\\d+")) { // Verifica que solo contenga dígitos
                            JOptionPane.showMessageDialog(null, "Cédula inválida. Ingrese solo números.");
                        }
                    } while (!id.matches("\\d+")); // Repite mientras no se ingresen solo números

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
                    String memberId;
                    do {
                        memberId = JOptionPane.showInputDialog("Ingrese la cédula del socio (solo números):");
                        if (!memberId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Cédula inválida. Ingrese solo números.");
                        }
                    } while (!memberId.matches("\\d+"));

                    boolean memberExists = false;

                    for (int i = 0; i < club.getMembers().size(); i++) {
                        if (club.getMembers().get(i).getId().equals(memberId)) {
                            memberExists = true;

                            String authorizedPersonId;
                            do {
                                authorizedPersonId = JOptionPane.showInputDialog("Ingrese la cédula de la persona autorizada (solo números):");
                                if (!authorizedPersonId.matches("\\d+")) {
                                    JOptionPane.showMessageDialog(null, "Cédula inválida. Ingrese solo números.");
                                }
                            } while (!authorizedPersonId.matches("\\d+"));

                            String authorizedPersonName = JOptionPane.showInputDialog("Ingrese el nombre de la persona autorizada:");

                            AuthorizedPerson authorizedPerson = new AuthorizedPerson(authorizedPersonId, authorizedPersonName);

                            club.getMembers().get(i).addAuthorizedPerson(authorizedPerson);

                            break;
                        }
                    }

                    if (!memberExists) {
                        JOptionPane.showMessageDialog(null, "No existe un socio con la cédula proporcionada.");
                    }

                    break;

                case 3:
                    List<Partner> members = club.getMembers();
                    if (members.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay socios registrados.");
                    } else {
                        StringBuilder memberList = new StringBuilder("Lista de socios:\n");
                        for (Partner p : members) {
                            memberList.append("Cédula: ").append(p.getId()).append(", Nombre: ").append(p.getName())
                                      .append(", Suscripción: ").append(p.getTypeSubscription()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, memberList.toString());
                    }
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        } while (option != 4);
    }
}
