public class Ticket {
    int id; //atributos
    String descripcion;
    String departamento;
    String prioridad; 
    String estado; 
//constructor
    Ticket(int id, String descripcion, String departamento, String prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.prioridad = prioridad;
        this.estado = "Pendiente";
    }
//override para mostrar info del ticket
    @Override
    public String toString() {
        return "Ticket #" + id + " | " + departamento + " | " + prioridad + " | " + estado + " | " + descripcion;
    }
}