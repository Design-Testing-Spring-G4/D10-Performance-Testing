
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArticleRepository;
import domain.Article;
import domain.Followup;
import domain.Newspaper;

@Service
@Transactional
public class ArticleService {

	//Managed repository ---------------------------------

	@Autowired
	private ArticleRepository	articleRepository;


	//Simple CRUD Methods --------------------------------

	public Article create(final Newspaper newspaper) {
		final Article article = new Article();

		article.setFinalMode(false);
		article.setFollowups(new ArrayList<Followup>());
		article.setPictures(new ArrayList<String>());
		article.setMoment(newspaper.getPublicationDate());

		return article;
	}

	public Collection<Article> findAll() {
		return this.articleRepository.findAll();
	}

	public Article findOne(final int id) {
		Assert.notNull(id);

		return this.articleRepository.findOne(id);
	}

	public Article save(final Article article) {
		Assert.notNull(article);

		final Article saved = this.articleRepository.save(article);

		return saved;
	}

	public void delete(final Article article) {
		Assert.notNull(article);

		this.articleRepository.delete(article);
	}
}
