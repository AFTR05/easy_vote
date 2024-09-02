package co.edu.cue.easy_vote.infrastructure.util;


public class Validation {

    /**
     * Valida si el campo esta vacio o nulo
     *
     * @param value String que voy a evaluar
     * @return Un Boolean que me dice si esta vacio o nulo
     */
    public static Boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static Boolean isNull(Object value) {
        return value == null;
    }
}
