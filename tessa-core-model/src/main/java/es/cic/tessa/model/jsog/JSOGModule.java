package es.cic.tessa.model.jsog;

import tools.jackson.core.Version;
import tools.jackson.databind.module.SimpleModule;

/**
 * Modulo Jackson 3.x ({@code tools.jackson}) que registra {@link JSOGRefSerializer}
 * para {@link JSOGRef}.
 *
 * <p>El registro via modulo es necesario porque Jackson no invoca la anotacion
 * {@code @JsonSerialize} cuando serializa el Object Id producido por
 * {@link JSOGGenerator}: en ese path, Jackson pasa directamente el valor del id
 * al generador de escritura sin pasar por las anotaciones de la clase.
 *
 * <p>Este modulo se auto-registra por dos mecanismos complementarios:
 * <ol>
 *   <li><b>SPI de Jackson 3.x</b>: {@code META-INF/services/tools.jackson.databind.JacksonModule}
 *       — activo cuando la propiedad {@code spring.jackson.find-and-add-modules=true}
 *       (valor por defecto en Spring Boot 4).</li>
 *   <li><b>Bean de Spring</b>: {@code JsogJacksonConfig} expone este modulo como
 *       {@code @Bean} de tipo {@code tools.jackson.databind.JacksonModule}, que
 *       {@code JacksonAutoConfiguration} de Spring Boot 4 recoge automaticamente.</li>
 * </ol>
 */
public class JSOGModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JSOGModule() {
        super("JSOGModule", Version.unknownVersion());
        addSerializer(JSOGRef.class, new JSOGRefSerializer());
        addDeserializer(JSOGRef.class, new JSOGRefDeserializer());
    }
}
