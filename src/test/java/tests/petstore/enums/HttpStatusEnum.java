package tests.petstore.enums;

import lombok.Getter;

/**
 * Enum que representa os principais status HTTP usados nos testes.
 */
@Getter
public enum HttpStatusEnum {

    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),

    INTERNAL_SERVER_ERROR(500);

    private final int code;

    HttpStatusEnum(int code) {
        this.code = code;
    }
}