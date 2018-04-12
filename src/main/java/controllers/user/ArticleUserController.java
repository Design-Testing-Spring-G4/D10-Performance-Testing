
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ArticleService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;
import domain.User;

@Controller
@RequestMapping("article/user")
public class ArticleUserController extends AbstractController {

	//Services

	@Autowired
	private ActorService	actorService;

	@Autowired
	private UserService		userService;

	@Autowired
	private ArticleService	articleService;


	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int varId) {
		final ModelAndView result;
		Article article;
		System.out.println("id: " + varId);
		article = this.articleService.create(varId);
		result = this.createEditModelAndView(article);
		return result;
	}

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Article> articles;

		articles = ((User) this.actorService.findByPrincipal()).getArticles();

		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/list.do");

		return result;
	}

	@RequestMapping(value = "/listByUser", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int varId) {
		final ModelAndView result;
		Collection<Article> articles;

		articles = this.userService.articlesPublishedPerUser(varId);

		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/list.do");

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Article article) {
		ModelAndView result;

		result = this.createEditModelAndView(article, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Article article, final String messageCode) {
		ModelAndView result;
		final Collection<Article> articles;
		articles = ((User) this.actorService.findByPrincipal()).getArticles();
		result = new ModelAndView("article/edit");
		result.addObject("article", article);
		result.addObject("articles", articles);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "article/user/edit.do");

		return result;

	}

}
