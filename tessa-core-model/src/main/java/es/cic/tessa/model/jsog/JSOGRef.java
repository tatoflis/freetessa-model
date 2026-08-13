package es.cic.tessa.model.jsog;

import java.util.Objects;

/**
 * Represents a JSOG identity/reference.
 *
 * When serialized as the @id value (first encounter) the serializer writes a plain string scalar.
 * When serialized as a reference (repeated encounter) the serializer writes {"@ref": "<id>"}.
 *
 * The {@code used} flag is set to {@code true} after the first serialization, allowing the
 * serializer to distinguish the declaration case from the reference case.
 *
 * <p>La serializacion se gestiona via {@link JSOGModule} registrado como SPI de Jackson,
 * no mediante {@code @JsonSerialize} — esta anotacion no se aplica cuando Jackson
 * serializa el Object Id producido por {@link JSOGGenerator}.
 */
public final class JSOGRef {

    public static final String REF_KEY = "@ref";

    public final String ref;

    /** Set to true after the first (declaration) serialization. */
    public transient boolean used;

    public JSOGRef(int id) {
        this.ref = String.valueOf(id);
    }

    public JSOGRef(String ref) {
        this.ref = ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSOGRef other)) return false;
        return Objects.equals(ref, other.ref);
    }

    @Override
    public int hashCode() {
        return ref == null ? 0 : ref.hashCode();
    }
    
    @Override
    public String toString() {
        return "JSOGRef{ref='" + ref + "'}";
    }
}
