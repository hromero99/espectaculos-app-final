package es.uco.pw.business.dto;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Pase espectaculo.
 */
public class PaseEspectaculoDTO {
    private String diaSemana;



    /**
     * The Espectaculo.
     */
    int espectaculo;
    /**
     * The Fecha hora.
     */
    String fechaHora;
    /**
     * The Estado.
     */
    boolean estado;

    /**
     * Instantiates a new Pase espectaculo.
     *
     * @param espectaculo the espectaculo
     * @param fechaHora   the fecha hora
     * @param estado      the estado
     */
    public PaseEspectaculoDTO(int espectaculo, String fechaHora, boolean estado) {
        this.espectaculo = espectaculo;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    /**
     * Instantiates a new Pase espectaculo.
     *
     * @param csvLine the csv line
     */
    public PaseEspectaculoDTO(String csvLine) {
        String data[] = csvLine.split(",");
        this.setEspectaculo(Integer.parseInt(data[0]));
        this.setFechaHora(data[1]);
        this.setEstado(Boolean.parseBoolean(data[2]));

    }

    public PaseEspectaculoDTO() {

    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCsv(){
        return String.format("%s,%s,%b",this.getEspectaculo(),this.getFechaHora(),this.getEstado());
    }

    /**
     * Gets espectaculo.
     *
     * @return the espectaculo
     */
    public int getEspectaculo() {
        return espectaculo;
    }

    /**
     * Sets espectaculo.
     *
     * @param espectaculo the espectaculo
     */
    public void setEspectaculo(int espectaculo) {
        this.espectaculo = espectaculo;
    }


    /**
     * Get fecha hora string.
     *
     * @return the string
     */
    public String getFechaHora(){
        return this.fechaHora;
    }

    /**
     * Sets fecha hora.
     *
     * @param fechaHora the fecha hora
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Is estado boolean.
     *
     * @return the boolean
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Sets estado.
     *
     * @param estado the estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Gets estado.
     *
     * @return the estado
     */
    public boolean getEstado() {
        return this.estado;
    }

    public Date getFecha(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(this.fechaHora.split(" ")[0]);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Date getHora(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        try {
            return formatter.parse(this.fechaHora.split(" ")[1]);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDiaSemana(String buffer) {
        this.diaSemana = buffer;
    }
    public String getDiaSemana() {
        return diaSemana;
    }
}
