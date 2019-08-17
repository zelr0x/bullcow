package io.github.zelr0x.bullcow.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Base entity class.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    /**
     * Get an ID of an entity.
     *
     * @return a Long object containing the ID of the entity.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set an ID of an entity.
     *
     * @param id a Long object containing the ID of the entity.
     */
    public void setId(final Long id) {
        this.id = id;
    }
}
