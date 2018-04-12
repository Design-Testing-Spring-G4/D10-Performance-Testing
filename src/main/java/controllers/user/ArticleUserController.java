
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
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


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Article> articles;

		articles = ((User) this.actorService.findByPrincipal()).getArticles();

		result = new ModelAndView("article/list");
		result.addObject("newspapers", articles);
		result.addObject("requestURI", "article/list.do");

		return result;
	}

	@RequestMapping(value = "/listPublished", method = RequestMethod.GET)
	public ModelAndView listPublished() {
		final ModelAndView result;
		Collection<Article> articles;
		final User user = ((User) this.actorService.findByPrincipal());
		articles = this.userService.articlesPublishedPerUser(user.getId());

		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/list.do");

		return result;
	}

}
