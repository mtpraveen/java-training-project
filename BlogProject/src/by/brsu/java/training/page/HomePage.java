package by.brsu.java.training.page;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionButton;

public class HomePage extends BorderedPage {

	private ActionButton bloggersButton;
	private ActionButton allArticlesButton;
	private ActionButton myBlogsButton;
	
	private ActionButton createBlogButton;
	
	private ActionButton createBloggersButton() {
		bloggersButton = new ActionButton("bloggersButton");
		bloggersButton.setLabel("Блогеры");
		bloggersButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/bloggers-page.htm");
				//setRedirect(BloggersPage.class);
				return false;
			}
		});
		return bloggersButton;
	}

	private ActionButton createAllArticlesButton() {
		allArticlesButton = new ActionButton("allArticlesButton");
		allArticlesButton.setLabel("Статьи");
		allArticlesButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/all-articles-page.htm");
				return false;
			}
		});
		return allArticlesButton;
	}
	
	private ActionButton createMyBlogsButton() {
		myBlogsButton = new ActionButton("myBlogsButton");
		myBlogsButton.setLabel("Мои блоги");
		myBlogsButton.setActionListener(new ActionListener() {
			
			public boolean onAction(Control source) {
				setRedirect("/my-blogs-page.htm");
				return false;
			}
		});
		return myBlogsButton;
	}

	private ActionButton createCreateBlogButton() {
		createBlogButton = new ActionButton("createBlogButton");
		createBlogButton.setLabel("Создать блог");
		createBlogButton.setActionListener(new ActionListener() {
			
			public boolean onAction(Control source) {
				setRedirect("/create-blog-page.htm");
				return false;
			}
		});
		return createBlogButton;
		
	}
	
	public HomePage() {
		addControl(createBloggersButton());
		addControl(createAllArticlesButton());
		
		addControl(createMyBlogsButton());
		addControl(createCreateBlogButton());
		
	}

	/* (non-Javadoc)
	 * @see by.brsu.java.training.page.BorderedPage#getTemplate()
	 */
	@Override
	public String getTemplate() {
		// TODO Auto-generated method stub
		return "/home.htm";
	}
	
	
}