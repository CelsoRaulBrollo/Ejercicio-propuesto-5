import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Ejercicio5 {
    public static void main(String[] args)throws IOException {
            List<Empleado> empleados = new ArrayList<>();
            String path = "Lista_empleados.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String linea = br.readLine();
                
                while(linea != null) { 
                    String[] listaEmp = linea.split(",");
                    Empleado emp = new Empleado(listaEmp[0], listaEmp[1], listaEmp[2], listaEmp[3]);
                    empleados.add(emp);
                    linea = br.readLine();
                }
                out.println(empleados); 
            }
            String letra = "s";
            String letraMay = letra.toUpperCase();
            
            out.println("Apellido comienza con la letra: " + letraMay);
            comienzaCon(empleados, letraMay); 

            out.println("Empleado con mayor edad: ");
            mayorEdad(empleados);

            out.println("Empleado con menor edad: ");
            menorEdad(empleados);

            out.println("Empleado que más gana: ");
            ganaMas(empleados);

            out.println("Empleado que menos gana: ");
            ganaMenos(empleados);
        
        }

    public static void comienzaCon(List<Empleado> empleados, String letraMay) {
    for(Empleado e:empleados) {
        if(e.apellido.startsWith(letraMay)){
            out.println(e);
        }
    }
    }

    public static void mayorEdad(List<Empleado> empleados) {
        int mayorEdad = empleados.get(0).calcularEdad();
        for (Empleado e:empleados) {
            if(e.calcularEdad() > mayorEdad) {
                mayorEdad = e.calcularEdad();
            }
        }
        List <Empleado> listaMayor = new ArrayList<>();
        for (Empleado e:empleados) {
            if(e.calcularEdad() == mayorEdad){
                listaMayor.add(e);
            }
        }
        out.println(listaMayor);
    }

    public static void menorEdad(List<Empleado> empleados) {
        int menorEdad = empleados.get(0).calcularEdad();
        for (Empleado e:empleados) {
            if(e.calcularEdad() < menorEdad) {
                menorEdad = e.calcularEdad();
            }
        }
        List <Empleado> listaMenor = new ArrayList<>();
        for (Empleado e:empleados) {
            if(e.calcularEdad() == menorEdad){
                listaMenor.add(e);
            }
        }
        out.println(listaMenor);
    }

    public static void ganaMas(List<Empleado> empleados) {
        int sueldoMas = empleados.get(0).sueldo.intValue();
        for(Empleado e:empleados) {
            if(e.sueldo.intValue() > sueldoMas) {
                sueldoMas = e.sueldo.intValue();
            }
        }
        List<Empleado> listaMas = new ArrayList<>();
        for(Empleado e:empleados) {
            if(e.sueldo.intValue() == sueldoMas) {
                listaMas.add(e);
            }
        }
        out.println(listaMas);
    }

    public static void ganaMenos(List<Empleado> empleados) {
        int sueldoMenos = empleados.get(0).sueldo.intValue();
        for(Empleado e:empleados) {
            if(e.sueldo.intValue() < sueldoMenos) {
                sueldoMenos = e.sueldo.intValue();
            }
        }
        List<Empleado> listaMenos = new ArrayList<>();
        for (Empleado e:empleados) {
            if(e.sueldo.intValue() == sueldoMenos) {
                listaMenos.add(e);
            }
        }
        out.println(listaMenos);
    }
}

class Empleado {
    private String nombre;
    public String apellido;
    private LocalDate fechaNacimiento;
    public BigDecimal sueldo;

    public Empleado(String nombre, String apellido, String fechaNacimiento, String sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sueldo = new BigDecimal(sueldo);
        DateTimeFormatter fechaNacInput = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento, fechaNacInput);   
    }

    public int calcularEdad() {
        int edad = 0;
        LocalDate hoy = LocalDate.now();
        int diaHoy = hoy.getDayOfMonth();
        int añoHoy = hoy.getYear();
        int mesHoy = hoy.getMonthValue();
        int diaNac = fechaNacimiento.getDayOfMonth();
        int añoNac = fechaNacimiento.getYear();
        int mesNac = fechaNacimiento.getMonthValue();
        if (mesHoy > mesNac || mesHoy == mesNac && diaHoy >=diaNac) {
            edad = (añoHoy - añoNac);    
        } else if (mesHoy < mesNac || mesHoy == mesNac && diaHoy < diaNac) {
            edad = (añoHoy - añoNac) - 1;
        } 
        return edad;
    }

    @Override
    public String toString() {
        return("Nombre: " + nombre + " - Apellido: " + apellido + " - Fecha de Nacimiento: " + fechaNacimiento
        + " - Sueldo: " + sueldo);
    }
}