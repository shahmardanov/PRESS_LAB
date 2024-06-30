package com.example.press_lab.exception.error;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class ErrorMessages {

    private static final Map<String, Map<String, String>> messages = new HashMap<>();

    static {
        Map<String, String> defaultMessages = new HashMap<>();
        defaultMessages.put("error.news.not_found", "News not found.");
        defaultMessages.put("error.category.not_found", "Category not found.");
        defaultMessages.put("error.subcategory.not_found", "Subcategory not found.");
        messages.put("en", defaultMessages);

        Map<String, String> azMessages = new HashMap<>();
        azMessages.put("error.news.not_found", "Xəbər tapılmadı.");
        azMessages.put("error.category.not_found", "Kateqoriya tapılmadı.");
        azMessages.put("error.subcategory.not_found", "Subkateqoriya tapılmadı.");
        messages.put("az", azMessages);

        Map<String, String> ruMessages = new HashMap<>();
        ruMessages.put("error.news.not_found", "Новости не найдены.");
        ruMessages.put("error.category.not_found", "Категория не найдена.");
        ruMessages.put("error.subcategory.not_found", "Подкатегория не найдена.");
        messages.put("ru", ruMessages);
    }

    public String getMessage(String code, Locale locale) {
        Map<String, String> localeMessages = messages.get(locale.getLanguage());
        if (localeMessages != null && localeMessages.containsKey(code)) {
            return localeMessages.get(code);
        } else {
            return messages.get("en").get(code);
        }
    }
}