
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NewspaperRepository;
import domain.Article;
import domain.Customer;
import domain.Newspaper;
import domain.User;

@Service
@Transactional
public class NewspaperService {

	//Managed repository ---------------------------------

	@Autowired
	private NewspaperRepository	newspaperRepository;

	//Supporting services --------------------------------

	@Autowired
	private ActorService		actorService;


	//Simple CRUD Methods --------------------------------

	public Newspaper create() {
		final Newspaper newspaper = new Newspaper();

		newspaper.setArticles(new ArrayList<Article>());
		newspaper.setCustomers(new ArrayList<Customer>());
		newspaper.setPrivate(false);
		final User user = (User) this.actorService.findByPrincipal();
		newspaper.setPublisher(user);

		return newspaper;
	}

	public Collection<Newspaper> findAll() {
		return this.newspaperRepository.findAll();
	}

	public Newspaper findOne(final int id) {
		Assert.notNull(id);

		return this.newspaperRepository.findOne(id);
	}

	public Newspaper save(final Newspaper newspaper) {
		Assert.notNull(newspaper);

		//Assertion that the user deleting this newspaper has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == newspaper.getPublisher().getId());

		final Newspaper saved = this.newspaperRepository.save(newspaper);

		return saved;
	}

	public void delete(final Newspaper newspaper) {
		Assert.notNull(newspaper);

		//Assertion that the user deleting this newspaper has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == newspaper.getPublisher().getId());

		this.newspaperRepository.delete(newspaper);
	}
}
