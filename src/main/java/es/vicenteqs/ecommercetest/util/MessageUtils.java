package es.vicenteqs.ecommercetest.util;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import es.vicenteqs.ecommercetest.constant.ExceptionCodes;

@Component
public class MessageUtils {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setFallbackToSystemLocale(false);

		return messageSource;
	}

	public String getMessage(String code) {
		return this.messageSource().getMessage(code, null, LocaleContextHolder.getLocale());
	}

	public String getExceptionMessage(String code) {
		return this.messageSource().getMessage(ExceptionCodes.PREFIX + code, null, LocaleContextHolder.getLocale());
	}

	public String getMessage(String code, Object... param) {
		String template = this.messageSource().getMessage(code, null, LocaleContextHolder.getLocale());
		return String.format(template, param);
	}

}
