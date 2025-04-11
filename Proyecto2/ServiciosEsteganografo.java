public interface ServiciosEsteganografo{
    /**
    * Toma la n- ́e sima letra de cada palabra para obtener el mensaje
    * oculto .
    *
    * @param original El mensaje original .
    * @param n El  ́ı ndice que se tiene que tomar de cada palabra .
    * @return El mensaje oculto , sin espacios .
    */
    public String descifraNulo ( String original , int n );
    /**
    * Toma la n- ́e sima letra de cada palabra para obtener el mensaje
    * oculto .
    *
    * @param original El mensaje original con n espacios al final .
    * @return El mensaje oculto , sin espacios .
    */
    public String descifraNulo ( String original );
    /**
    * Busca un nombre oculto en un texto arbitrario ignorando
    * espacios , signos de puntuaci  ́on y sin hacer distinciones
    * entre may  ́u sculas y min  ́u sculas .
    *
    * @param mensaje Una cadena arbitraria donde se har ́a la
    * b ́u squeda .
    * @param nombre El nombre que se buscar  ́a en el texto .
    * @return true si el mensaje est  ́a contenido , false en otro
    * caso .
    */
    public boolean contieneNombre ( String mensaje , String nombre );
    /**
    * Reconstruye el mensaje oculto a partir de las palabras
    * especiales que se obtienen al comparar dos textos .
    *
    * @param m Un texto arbitrario .
    * @param e Un texto similar al primero , pero con algunas
    * palabras especiales .
    * @return El mensaje oculto .
    */
    public String descifraPalabrasMarcadas ( String m , String e );
    /**
    * Reconstruye el mensaje oculto a partir de las letras
    * especiales que se obtienen al comparar dos textos .
    *
    * @param m Un texto arbitrario .
    * @param e Un texto similar al primero , pero con algunas
    * letras especiales .
    * @return El mensaje oculto .
    */
    public String descifraLetrasMarcadas ( String m , String e );
}
