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
		if (information.getId() == 0 && information.getOrderIndex() == 0) {
			int newOrderIndex = findAll().stream().mapToInt(RunInformation::getOrderIndex)
					.max().getAsInt() + 1;
			information.setOrderIndex(newOrderIndex);
		}
		return em.merge(information);
	}

	public List<RunInformation> findAll() {
		return em.createNamedQuery(RunInformation.FIND_ALL)
				.getResultList();
	}

	public RunInformation findById(long id) {
		return em.find(RunInformation.class, id);
	}

	public List<RunInformation> findRunsAfterOrderIndex(int orderIndex) {
		return em.createNamedQuery(RunInformation.FIND_AFTER_ORDER_INDEX, RunInformation.class)
				.setParameter("paramIndex", orderIndex)
				.getResultList();
	}

	public void deleteById(long id) {
		RunInformation information = findById(id);
		em.remove(information);
	}

}
