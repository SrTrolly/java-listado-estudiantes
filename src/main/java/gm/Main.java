package gm;

import gm.datos.EstudianteDAO;
import gm.dominio.Estudiante;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean salir = false;
        Scanner consola = new Scanner(System.in);

        //Importamos la funcionalidad DAO
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        while (!salir) {
            mostrarMenu();
            salir = ejecutarOpcionesEstudiates(consola, estudianteDAO);
        }



    }

    private static void mostrarMenu() {
        System.out.println("""
                *** Sistemas de Estudiantes ***
                1. Listar Estudiantes
                2. Buscar Estudiantes
                3. Agregar Estudiantes
                4. Modificar Estudiantes
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion: 
                """);
    }

    private static boolean ejecutarOpcionesEstudiates(Scanner consola, EstudianteDAO estudianteDAO){

        int opcion= Integer.parseInt(consola.nextLine());
        boolean salir= false;

        switch (opcion) {
            case 1-> {
                System.out.println("Listado de estudiantes");
                List<Estudiante> estudiates =estudianteDAO.listarEstudiantes();
                System.out.println("Estudiantes Registrados ");
                estudiates.forEach(System.out::println);
            }

            case 2-> {
                System.out.println("Ingrese el id del estudiante");
                int idEstudiante =  Integer.parseInt(consola.nextLine());
                Estudiante estudiateABuscar= new Estudiante(idEstudiante);
                boolean fueEncontrado=  estudianteDAO.buscarEstudiantePorId(estudiateABuscar);

                if (fueEncontrado) {
                    System.out.println("Estudiante encontrado " + estudiateABuscar);
                } else {
                    System.out.println("El estudiante no fue encontrado");
                }

            }
            case 3 -> {
                System.out.println("Introduce el nombre del estudiante a agregar: ");
                String nombre= consola.nextLine();
                System.out.println("Introduce el apellido del estudiante");
                String apellido = consola.nextLine();
                System.out.println("Introduce el telefono del estudiante");
                String telefono = consola.nextLine();
                System.out.println("Introduce el email del estudiante");
                String email= consola.nextLine();

                Estudiante nuevoEstudiante = new Estudiante(nombre,apellido,telefono,email);
                boolean fueAgregado= estudianteDAO.agregarEstudiante(nuevoEstudiante);

                if(fueAgregado) {
                    System.out.println("El estudiante fue agregado " + nuevoEstudiante);
                } else {
                    System.out.println("El estudiante no fue agregado ");
                }

            }

            case 4-> {
                System.out.println("Introduce el id del estudiante a modificar");
                int idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Introduce el nombre del estudiante a modificar: ");
                String nombre= consola.nextLine();
                System.out.println("Introduce el apellido del estudiante");
                String apellido = consola.nextLine();
                System.out.println("Introduce el telefono del estudiante");
                String telefono = consola.nextLine();
                System.out.println("Introduce el email del estudiante");
                String email= consola.nextLine();

                Estudiante estudianteModificar = new Estudiante(idEstudiante,nombre,apellido,telefono,email);
                boolean fueModificado= estudianteDAO.modificarEstudiante(estudianteModificar);

                if(fueModificado) {
                    System.out.println("El estudiante fue modifica " + estudianteModificar);
                } else {
                    System.out.println(" El estudiante no fue modificado");
                }

            }

            case 5-> {
                System.out.println("Ingrese el id del estudiante a eliminar ");
                int idEliminar = Integer.parseInt(consola.nextLine());

                Estudiante estudianteEliminar = new Estudiante(idEliminar);

                boolean fueEliminado= estudianteDAO.eliminarEstudiante(estudianteEliminar);

                if(fueEliminado) {
                    System.out.println(" El estudiante fue eliminado con el id " + estudianteEliminar.getIdEstudiante());
                } else {
                    System.out.println("El estudiante no fue eliminado");
                }
            }

            case 6-> {
                System.out.println("Hasta pronto");
                salir= true;
            }
        }

        return salir;
    }
}