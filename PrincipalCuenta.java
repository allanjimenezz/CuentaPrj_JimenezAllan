import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class PrincipalCuenta {
    private static Double leerDouble(Scanner sc, String movimiento) {
        System.out.print("\nIndique el monto a " + movimiento + ": ");
        String s = sc.nextLine().trim();
        return Double.parseDouble(s);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int cuentaActual = -1;

        boolean salir = false;
        while (!salir) {
            System.out.println("\n==========================================");
            System.out.println("Menú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Ingresar nombre del títular");
            System.out.println("8) Consultar saldo");
            System.out.println("9) Consultar estado (toString)");
            System.out.println("0) Salir");
            String op = sc.nextLine().trim();
            
            switch (op) {
                case "1": { // Crear la cuenta 
                    System.out.print("\nMonto inicial: ");
                    String saldoInicialS = sc.nextLine().trim();
                    double saldoInicial = Double.parseDouble(saldoInicialS);
                    
                    System.out.print("Nombre del títular de la cuenta (enter para saltar): ");
                    String nombreCuentaHabiente = sc.nextLine().trim();
                    
                    Cuenta c;
                    
                    if (nombreCuentaHabiente.isEmpty()) 
                        c = new Cuenta(saldoInicial);
                    else 
                        c = new Cuenta(nombreCuentaHabiente, saldoInicial);
                        
                    cuentas.add(c);
                    cuentaActual = cuentas.size() - 1;
                    System.out.println("\nCuenta creada y seleccionada");
                    System.out.print("Índice " + cuentaActual + "\n");
                    break;
                }
                case "2": { // Cantidad de cuentas creadas
                    System.out.println(("\nTotal de cuentas creadas: ") + Cuenta.getCantCuentasCreadas());
                    break;
                }
                case "3": { // Listado de cuentas
                    if (cuentas.isEmpty()) {
                        System.out.println("\nNo hay cuentas creadas.");
                    } else {
                        System.out.println("\nÍndice | Código | Saldo");
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    | %s | %.2f%n",
                                    i,
                                    c.getCodCuenta(),
                                    c.getSaldo());
                        }
                    }
                    break;
                }
                case "4": { // Seleccionar una cuenta 
                    if (cuentas.isEmpty()) {
                        System.out.println("\nCree al menos una cuenta.");
                        break;
                    }
                    System.out.print("\nÍndice de la cuenta a seleccionar: ");
                    String idxS = sc.nextLine().trim();
                    try {
                        int idx = Integer.parseInt(idxS);
                        if (idx >= 0 && idx < cuentas.size()) {
                            cuentaActual = idx;
                            System.out.println("Cuenta de índice " + cuentaActual + " seleccionada.");
                        } else {
                            System.out.println("\nÍndice fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\nÍndice inválido.");
                    }
                    break;
                }
                case "5": { // Depositar
                    if (cuentas.isEmpty()) {
                        System.out.println("\nCree al menos una cuenta.");
                        break;
                    }
                    double monto = leerDouble(sc, "depositar");
                    System.out.println("Su monto actual corresponde ahora a ₡" + cuentas.get(cuentaActual).depositar(monto));
                    break;
                }
                case "6": { // Retirar
                    if (cuentas.isEmpty()) {
                        System.out.println("\nCree al menos una cuenta.");
                        break;
                    }
                    double monto = leerDouble(sc, "retirar");
                    System.out.println("Su monto actual corresponde ahora a ₡" + cuentas.get(cuentaActual).retirar(monto));
                    break;
                }
                case "7": { // Agregar nombreCuentaHabiente
                    if (cuentas.isEmpty()) {
                        System.out.println("\nCree al menos una cuenta.");
                        break;
                    }
                    System.out.print("\nIngrese el nombre del títular: ");
                    String nombreCuentaHabiente = sc.nextLine().trim();
                    cuentas.get(cuentaActual).setNombreCuentaHabiente(nombreCuentaHabiente);
                    break;
                }
                case "8": { // Consultar saldo
                    if (cuentas.isEmpty()) {
                        System.out.println("\nCree al menos una cuenta.");
                        break;
                    }
                    System.out.println("\nSu saldo actual es de: " + cuentas.get(cuentaActual).getSaldo());
                    break;
                }
                case "9": { // Consultar estado
                    if (cuentas.isEmpty()) {
                        System.out.println("\nCree al menos una cuenta.");
                        break;
                    }
                    System.out.println("\n" + cuentas.get(cuentaActual).toString());
                    break; 
                }
                case "0": {
                    salir = true;
                    System.out.println("\nMenú principal cerrado");
                    break;
                }
                default:
                    System.out.println("\nOpción inválida.");
            }
        }
        sc.close();
    }
}