package dio.me.project_dio.me_2025.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    public static String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    // Message keys
    public static final String PRODUCT_NOT_FOUND = "product.not_found";
    public static final String PRODUCT_ALREADY_EXISTS = "product.exists";
    public static final String INVALID_PRICE = "validation.invalid_price";
}