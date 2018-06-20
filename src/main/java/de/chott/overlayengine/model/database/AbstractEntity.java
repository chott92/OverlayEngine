package de.chott.overlayengine.model.database;

import de.chott.overlayengine.model.util.LocalDateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Convert(converter = LocalDateConverter.class)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDate createdDate;

	public long getId() {
		return id;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

}
