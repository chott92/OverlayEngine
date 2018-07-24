package de.chott.overlayengine.service.database;

import de.chott.overlayengine.model.database.RunInformation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class RunInformationService {

	@PersistenceContext
	private EntityManager em;

	public RunInformation save(RunInformation information) {
		return em.merge(information);
	}

	public List<RunInformation> findAll() {
		return em.createNamedQuery(RunInformation.FIND_ALL)
				.getResultList();
	}

	public RunInformation findById(long id) {
		return em.find(RunInformation.class, id);
	}

}
