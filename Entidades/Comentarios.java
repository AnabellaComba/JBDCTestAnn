package Entidades;

public class Comentarios{

  private int idComentario;
  private int idCasa;
  private String comentario;

  public Comentarios(int idComentario, int idCasa, String comentario) {
    this.idComentario = idComentario;
    this.idCasa = idCasa;
    this.comentario = comentario;
  }

  public Comentarios(int idCasa, String comentario) {
    this.idCasa = idCasa;
    this.comentario = comentario;
  }

  public Comentarios() {}

  public int getIdComentario() {
    return idComentario;
  }

  public void setIdComentario(int idComentario) {
    this.idComentario = idComentario;
  }

  public int getIdCasa() {
    return idCasa;
  }

  public void setIdCasa(int idCasa) {
    this.idCasa = idCasa;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  @Override
  public String toString() {
    return "Comentarios [idComentario="
        + idComentario
        + ", idCasa="
        + idCasa
        + ", comentario="
        + comentario
        + "]";
  }
}
