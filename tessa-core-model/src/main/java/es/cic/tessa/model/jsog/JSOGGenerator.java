package es.cic.tessa.model.jsog;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;

/**
 * Jackson {@link ObjectIdGenerator} that produces {@link JSOGRef} instances as JSOG identifiers.
 *
 * <p>This replicates the behaviour of {@code com.voodoodyne.jackson.jsog.JSOGGenerator} for
 * Jackson 3.x ({@code tools.jackson}). The JSOG protocol assigns a numeric string id to each
 * object on first encounter and emits a reference object on repeated encounters:
 *
 * <ul>
 *   <li>First occurrence: {@code {"@id": "1", ...other fields...}}</li>
 *   <li>Repeated reference: {@code {"@ref": "1"}}</li>
 * </ul>
 *
 * <p>Decoupled from the original {@code jackson-jsog} library dependency — only uses APIs
 * available in {@code jackson-annotations} and {@code tools.jackson.databind} (Spring Boot 4+).
 *
 * <p>Usage: annotate the base DTO/entity class with:
 * <pre>
 *   {@code @JsonIdentityInfo(generator = JSOGGenerator.class, property = "@id")}
 * </pre>
 */
public class JSOGGenerator extends ObjectIdGenerator<JSOGRef> {

    private static final long serialVersionUID = 1L;

    protected final Class<?> _scope;
    protected transient int _nextValue;

    /** No-arg constructor required by Jackson for instantiation via reflection. */
    public JSOGGenerator() {
        this(null, -1);
    }

    public JSOGGenerator(Class<?> scope, int nextValue) {
        this._scope = scope;
        this._nextValue = nextValue;
    }

    // -------------------------------------------------------------------------
    // ObjectIdGenerator contract
    // -------------------------------------------------------------------------

    @Override
    public Class<?> getScope() {
        return _scope;
    }

    @Override
    public boolean canUseFor(ObjectIdGenerator<?> gen) {
        return gen.getClass() == getClass() && gen.getScope() == _scope;
    }

    @Override
    public ObjectIdGenerator<JSOGRef> forScope(Class<?> scope) {
        return (_scope == scope) ? this : new JSOGGenerator(scope, _nextValue);
    }

    @Override
    public ObjectIdGenerator<JSOGRef> newForSerialization(Object context) {
        return new JSOGGenerator(_scope, 1);
    }

    @Override
    public IdKey key(Object key) {
        return new IdKey(getClass(), _scope, key);
    }

    @Override
    public JSOGRef generateId(Object forPojo) {
        int id = _nextValue++;
        return new JSOGRef(id);
    }

    // -------------------------------------------------------------------------
    // JSOG-specific overrides
    // -------------------------------------------------------------------------

    /**
     * Returns {@code true} so that Jackson knows the id value may be serialized as a JSON object
     * (when it is a reference). This is what triggers the {@link #isValidReferencePropertyName}
     * check instead of treating the value as a plain scalar.
     */
    @Override
    public boolean maySerializeAsObject() {
        return true;
    }

    /**
     * Returns {@code true} only for the {@value JSOGRef#REF_KEY} property name, which is the
     * field used to encode a back-reference: {@code {"@ref": "1"}}.
     * Jackson uses this to detect whether an inlined object is actually a JSOG reference.
     */
    @Override
    public boolean isValidReferencePropertyName(String name, Object parser) {
        return JSOGRef.REF_KEY.equals(name);
    }
}
