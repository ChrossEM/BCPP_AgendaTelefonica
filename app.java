import java.util.*;

class Contact {
    String name;
    int phone;

    Contact(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phone;
    }
}

public class app {
    private static final int MAX_CONTACTS = 10;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        List<Contact> contactsList = new ArrayList<>();
        Contact nullContact = new Contact(".N/A", 0);

        System.out.println("AGENDA TELEFONICA\n");

        boolean menu = true;
        while (menu) {
            displayMenu();
            int option = input.nextInt();

            switch (option) {
                case 1:
                    addContact(input, contactsList, nullContact);
                    break;
                case 2:
                    listContacts(contactsList);
                    break;
                case 3:
                    searchContact(input, contactsList);
                    break;
                case 4:
                    modifyContact(input, contactsList);
                    break;
                case 5:
                    deleteContact(input, contactsList);
                    break;
                case 6:
                    deleteAllContacts(input, contactsList, nullContact);
                    break;
                case 7:
                    menu = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        input.close();
    }

    private static void displayMenu() {
        System.out.println("MENU");
        System.out.println("1. Nuevo Contacto");
        System.out.println("2. Lista de contactos");
        System.out.println("3. Busqueda de contacto");
        System.out.println("4. Modificar contacto");
        System.out.println("5. Borrar contacto");
        System.out.println("6. Borrar todo");
        System.out.println("7. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private static void addContact(Scanner input, List<Contact> contactsList, Contact nullContact) {
        if (contactsList.size() >= MAX_CONTACTS) {
            System.out.println("No hay suficiente espacio.");
            return;
        }

        System.out.print("Nombre: ");
        String name = input.next();
        System.out.print("Numero: ");
        int number = input.nextInt();
        Contact contact = new Contact(name, number);

        contactsList.add(contact);
        System.out.println("Contacto agregado exitosamente.\n");
    }

    private static void listContacts(List<Contact> contactsList) {
        contactsList.sort(Comparator.comparing(Contact::getName));
        System.out.printf("%-20s %-15s%n", "Nombre:", "Telefono:");
        for (Contact contact : contactsList) {
            if (!contact.getName().equals(".N/A")) {
                System.out.printf("%-20s %-15s%n", contact.getName(), contact.getPhoneNumber());
            }
        }
        System.out.println();
    }

    private static void searchContact(Scanner input, List<Contact> contactsList) {
        System.out.print("Nombre del contacto a buscar: ");
        String entry = input.next();
        Contact foundContact = searchContactByName(contactsList, entry);

        if (foundContact != null) {
            System.out.println("El número de contacto para " + entry + " es " + foundContact.getPhoneNumber());
        } else {
            System.out.println("El contacto no fue encontrado\n");
        }
    }

    private static void modifyContact(Scanner input, List<Contact> contactsList) {
        System.out.print("Digite el nombre del contacto a modificar: ");
        String entry = input.next();
        Contact contactToModify = searchContactByName(contactsList, entry);

        if (contactToModify != null) {
            System.out.print("Ingrese el nuevo nombre para el contacto: ");
            String newName = input.next();
            System.out.print("Ingrese el nuevo número de teléfono para el contacto: ");
            int newPhone = input.nextInt();
            Contact newContact = new Contact(newName, newPhone);
            int index = contactsList.indexOf(contactToModify);
            contactsList.set(index, newContact);
            System.out.println("Contacto modificado exitosamente.\n");
        } else {
            System.out.println("El contacto no fue encontrado\n");
        }
    }

    private static void deleteContact(Scanner input, List<Contact> contactsList) {
        System.out.print("Digite el nombre del contacto a borrar: ");
        String contactDelete = input.next();
        Contact contactToDelete = searchContactByName(contactsList, contactDelete);

        if (contactToDelete != null) {
            contactsList.remove(contactToDelete);
            System.out.println("Contacto borrado exitosamente.\n");
        } else {
            System.out.println("El contacto no fue encontrado\n");
        }
    }

    private static void deleteAllContacts(Scanner input, List<Contact> contactsList, Contact nullContact) {
        System.out.print("¿Está seguro de que desea eliminar todos los contactos? (S/N): ");
        String confirmDelete = input.next();
        if (confirmDelete.equalsIgnoreCase("S")) {
            contactsList.clear();
            contactsList.addAll(Collections.nCopies(MAX_CONTACTS, nullContact));
            System.out.println("Todos los contactos han sido eliminados.\n");
        } else {
            System.out.println("Operación cancelada.\n");
        }
    }

    private static Contact searchContactByName(List<Contact> contactsList, String name) {
        for (Contact contact : contactsList) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }
}
