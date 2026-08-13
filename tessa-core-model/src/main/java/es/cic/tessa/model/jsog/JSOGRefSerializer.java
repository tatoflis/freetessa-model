package es.cic.tessa.model.jsog;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * Serializador Jackson 3.x ({@code tools.jackson}) para {@link JSOGRef}.
 *
 * <p>Implementa el protocolo JSOG:
 * <ul>
 *   <li>Primera llamada ({@code used == false}): escribe el id como cadena escalar
 *       — valor del campo {@code @id}, ej. {@code "@id": "1"}.</li>
 *   <li>Llamadas sucesivas ({@code used == true}): escribe un objeto JSON con un
 *       campo {@code @ref}, ej. {@code {"@ref": "1"}}.</li>
 * </ul>
 *
 * <p>Se registra via {@link JSOGModule}, que a su vez se auto-descubre mediante
 * {@code META-INF/services/tools.jackson.databind.JacksonModule}.
 */
public class JSOGRefSerializer extends ValueSerializer<JSOGRef> {

    @Override
    public void serialize(JSOGRef value, JsonGenerator gen, SerializationContext ctxt)
            throws JacksonException {
        if (value.used) {
            // Caso referencia: {"@ref": "<id>"}
            gen.writeStartObject();
            gen.writeName(JSOGRef.REF_KEY);
            gen.writeString(value.ref);
            gen.writeEndObject();
        } else {
            // Caso declaracion: marcar como usado y emitir el id como cadena escalar
            value.used = true;
            gen.writeString(value.ref);
        }
    }
}
