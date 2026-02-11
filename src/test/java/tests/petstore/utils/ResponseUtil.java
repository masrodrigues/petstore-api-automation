package tests.petstore.utils;

/**
 * Centraliza constantes usadas em validações de response.
 */
public final class ResponseUtil {

    private ResponseUtil() {
        // Evita instanciação
    }

    // ===============================
    // Campos comuns de resposta
    // ===============================

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_MESSAGE = "message";

    // ===============================
    // Mensagens comuns
    // ===============================

    public static final String MESSAGE_PET_NOT_FOUND = "Pet not found";
}
