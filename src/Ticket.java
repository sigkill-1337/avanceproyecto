public class Ticket {
    int id;
    String descripcion;
    String departamento;

    Ticket(int id, String descripcion, String departamento) {
        this.id = id;
        this.descripcion = descripcion;
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Ticket #" + id + " | Depto: " + departamento + " | " + descripcion;
    }
}