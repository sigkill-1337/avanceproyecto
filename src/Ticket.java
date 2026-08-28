public class Ticket {
    int id;
    String descripcion;
    String departamento;
    String prioridad; 
    String estado; 

    Ticket(int id, String descripcion, String departamento, String prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.prioridad = prioridad;
        this.estado = "Pendiente";
    }

    @Override
    public String toString() {
        return "Ticket #" + id + " | " + departamento + " | " + prioridad + " | " + estado + " | " + descripcion;
    }
}