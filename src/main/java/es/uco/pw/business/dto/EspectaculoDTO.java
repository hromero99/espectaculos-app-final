package es.uco.pw.business.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The type Espectaculo.
 */
public class EspectaculoDTO {

    private int id;
    protected String titulo;
    protected String descripcion;
    protected String categoria;
    protected List<PaseEspectaculoDTO> pases = new ArrayList<>();
    private int localidades;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo.equalsIgnoreCase("puntual") || tipo.equalsIgnoreCase("multiple") ||tipo.equalsIgnoreCase("temporada")) {
            this.tipo = tipo;
        }
    }

    public EspectaculoDTO() {
    }
    /**
     * Instantiates a new Espectaculo.
     *
     * @param titulo      the titulo
     * @param descripcion the descripcion
     * @param categoria   the categoria
     * @param pases       the pases
     * @param localidades the localidades
     */
    public EspectaculoDTO(int id,String titulo, String descripcion, String categoria, Optional<List<PaseEspectaculoDTO>> pases, int localidades){
        this.setId(id);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.pases = new ArrayList<PaseEspectaculoDTO>();
        if (pases !=null){
            this.pases = pases.get();
        }
        this.localidades = localidades;

    }
    public EspectaculoDTO(String titulo, String descripcion, String categoria, Optional<List<PaseEspectaculoDTO>> pases, int localidades){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.pases = new ArrayList<PaseEspectaculoDTO>();
        if (pases !=null){
            this.pases = pases.get();
        }
        this.localidades = localidades;
        this.setId(id);
        this.setTipo(tipo);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets localidades.
     *
     * @param localidades the localidades
     */
    public void setLocalidades(int localidades) {
        this.localidades = localidades;
    }

    /**
     * Instantiates a new Espectaculo.
     *
     * @param csv_line the csv line
     */
    public EspectaculoDTO(String csv_line){
        csv_line = csv_line.replaceAll("\n","");
        String[] data = csv_line.split(",");
        this.setTitulo(data[0]);
        this.setDescripcion(data[1]);
        this.setCategoria(data[2]);
        this.setLocalidades(Integer.parseInt(data[3]));
    }

    /**
     * Gets titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets titulo.
     *
     * @param titulo the titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets categoria.
     *
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets categoria.
     *
     * @param categoria the categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Gets localidades.
     *
     * @return the localidades
     */
    public int getLocalidades() {
        return localidades;
    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCsv(){
        return  String.format("%d,%s,%s,%s,%d",getId(),getTitulo(),getDescripcion(),getCategoria(),getLocalidades());
    }

    /**
     * Get pase estado boolean.
     *
     * @param FechaHora the fecha hora
     * @return the boolean
     */
    public boolean getPaseEstado(String FechaHora){
        for (PaseEspectaculoDTO it: this.pases){
            if (it.getFechaHora().equals(FechaHora)){
                return it.getEstado();
            }
        }
        return false;
    }

    /**
     * Sets pase estado.
     *
     * @param estado    the estado
     * @param fechaHora the fecha hora
     * @return the pase estado
     */
    public boolean setPaseEstado(boolean estado, String fechaHora) {
        for (PaseEspectaculoDTO it: this.pases){
            if (it.getFechaHora().equals(fechaHora)){
                 it.setEstado(estado);
                 return true;
            }
        }

        return false;
    }

    /**
     * Add pase boolean.
     *
     * @param pase the pase
     * @return the boolean
     */
    public boolean addPase(PaseEspectaculoDTO pase) {
        // Counter loop
        int i = 0;
        for (PaseEspectaculoDTO it: this.pases ){
            if (pase.getFechaHora().equals(it.getFechaHora()) && (i<=this.localidades)){
                i++;
            }
        }
        // Comprobamos si el numero de pases es mayor a la localidad
        if (i >= this.localidades){
            return false;
        }
        this.pases.add(pase);
        return true;
    }

    /**
     * Remove pase boolean.
     *
     * @param pase the pase
     * @return the boolean
     */
    public boolean removePase(PaseEspectaculoDTO pase) {
        for (PaseEspectaculoDTO it: this.pases){
            if (it.getFechaHora().equals(pase.getFechaHora())){
                this.pases.remove(it);
                return true;
           }
        }

        return false;
    }

    /**
     * Gets pases.
     *
     * @return the pases
     */
    public List<PaseEspectaculoDTO> getPases() {
        return pases;
    }

    /**
     * Sets pases.
     *
     * @param pases the pases
     */
    public void setPases(List<PaseEspectaculoDTO> pases) {
        this.pases = pases;
    }

    public int getPasesVendidos(String fechaHora){
        int counter = 0;
        for (PaseEspectaculoDTO it: this.pases){
            if ( it.getFechaHora().equals(fechaHora) ){
                counter ++;
            }
        }
        return counter;
    }

    public String returnFormatString(){
        return String.format(
         "Titulo: %s\n Descripcion: %s\n Categoria: %s\n Localidades: %d\n",this.getTitulo(),this.getDescripcion(),this.getCategoria(),this.getLocalidades()
        );
    }
}
