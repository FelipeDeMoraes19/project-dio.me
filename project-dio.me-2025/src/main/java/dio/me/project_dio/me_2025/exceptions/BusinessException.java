package dio.me.project_dio.me_2025.exceptions;

import dio.me.project_dio.me_2025.util.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends RuntimeException {
    public BusinessException(String key, Object... args) {
        super(MessageUtils.getMessage(key, args));
    }
}