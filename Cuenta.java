import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cuenta
{
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas = 0;
    
    public Cuenta(String nombreCuentaHabiente, double pSaldo) {
        this(pSaldo);
        this.nombreCuentaHabiente = nombreCuentaHabiente;
    }
    
    public Cuenta(double pSaldo) {
        saldo = pSaldo;
        fechaCreacion = establecerFechaCreacionCuenta();
        cantCuentasCreadas++;
        codCuenta += cantCuentasCreadas;
    }
    
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta() {
        return codCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public double depositar(double monto) {
        saldo += monto;
        cantDepositosRealizados++;
        return saldo;
    }
    
    public double retirar(double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados++;
        }
        return saldo;
    }
    
    private boolean validarRetiro(double monto) {
        if (saldo >= monto) 
            return true;
        else
            return false;
    }
    
    public static int getCantCuentasCreadas() {
        return cantCuentasCreadas; 
    }
    
    public String toString() {
        String msg = "";
        msg += "======== Estado de Cuenta ========\n";
        msg += "Código de cuenta: " + codCuenta + "\n";
        msg += "Saldo actual: " + saldo + "\n";
        msg += "Nombre del propietario: " + nombreCuentaHabiente + "\n";
        msg += "Fecha de creación: " + fechaCreacion + "\n";
        msg += "-------- Movimientos exitósoso --------\n";
        msg += "Cantidad de depósitos realizados: " + cantDepositosRealizados+ "\n";
        msg += "Cantidad de retiros efectuados: " + cantRetirosExitososRealizados + "\n";
        return msg;
    }
    
    private String establecerFechaCreacionCuenta() {
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");
        return formatoFecha.format(fecha);
    }
}