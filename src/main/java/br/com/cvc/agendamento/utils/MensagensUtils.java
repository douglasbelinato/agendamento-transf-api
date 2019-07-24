package br.com.cvc.agendamento.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Componente utilitário para recuperar mensagens parametrizadas na aplicação.
 *
 */
@Component
public class MensagensUtils {

    @Autowired
    private MessageSource messageSource;

    public String get(String code, String[] params) {
        return messageSource.getMessage(code, params, new Locale("pt", "BR"));
    }
}
