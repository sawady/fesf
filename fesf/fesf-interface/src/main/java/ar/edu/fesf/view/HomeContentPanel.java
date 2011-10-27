package ar.edu.fesf.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.fesf.controllers.AjaxReplacePanel;
import ar.edu.fesf.controllers.IAjaxCallback;
import ar.edu.fesf.controllers.PanelServiceToForm;
import ar.edu.fesf.model.Book;
import ar.edu.fesf.model.Loan;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.services.BookService;
import ar.edu.fesf.services.PersonService;
import ar.edu.fesf.services.dtos.PersonDTO;

public class HomeContentPanel extends Panel {

    private static final String CONTENT = "content";

    private static final long serialVersionUID = 1L;

    private RankingPanel rankingPanel;

    private BookSearchResultPanel bookSearchResultPanel;

    @SpringBean
    private PersonService personService;

    @SpringBean
    private BookService bookService;

    public HomeContentPanel(final String id) {
        super(id);
        this.initialize();
    }

    private AjaxLazyLoadPanel lazyPanel(final String id, final Panel panel) {
        return new AjaxLazyLoadPanel(id) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getLazyLoadComponent(final String arg0) {
                return panel;
            }
        };
    }

    private void addLazy(final Panel panel) {
        this.add(this.lazyPanel(CONTENT, panel));
    }

    private void initialize() {
        this.setRankingPanel(new RankingPanel(CONTENT, this.changeToMoreInfoPanel()));
        this.getRankingPanel().setOutputMarkupId(true);
        this.addLazy(this.getRankingPanel());
        this.setBookSearchResultPanel(new BookSearchResultPanel(CONTENT, new ArrayList<Book>(), this
                .changeToMoreInfoPanel()));
        this.getBookSearchResultPanel().setOutputMarkupId(true);

        this.add(new HomeUserbarPanel("userbar", this.changeToRakingPanel()) {

            private static final long serialVersionUID = 1L;

            @Override
            public void loansCallback(final AjaxRequestTarget target) {
                HomeContentPanel.this.changeToLoaneeInfoPanel().callback(target,
                        ((MyWebSession) this.getSession()).getPerson());
            }

            @Override
            public void profileCallback(final AjaxRequestTarget target) {
                new AjaxReplacePanel<Person>(HomeContentPanel.this) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public Panel getNewPanel(final AjaxRequestTarget target, final Person person) {
                        return (Panel) new GenericFormPanel<PersonDTO>(CONTENT) {

                            private static final long serialVersionUID = 1L;

                            @Override
                            public PanelServiceToForm<PersonDTO> getFieldsPanel(final String id) {
                                return new PersonFormFieldsPanel(id, new PersonDTO(HomeContentPanel.this
                                        .getPersonService().initializePersonInfo(person)), HomeContentPanel.this
                                        .changeToPersonInfo());
                            }
                        }.setOutputMarkupId(true);
                    }

                }.callback(target, ((MyWebSession) this.getSession()).getPerson());
            }

            @Override
            public void signUpCallback(final AjaxRequestTarget target) {
                new AjaxReplacePanel<Person>(HomeContentPanel.this) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public Panel getNewPanel(final AjaxRequestTarget target, final Person person) {
                        return (Panel) new GenericFormPanel<PersonDTO>(CONTENT) {

                            private static final long serialVersionUID = 1L;

                            @Override
                            public PanelServiceToForm<PersonDTO> getFieldsPanel(final String id) {
                                return new PersonFormFieldsPanel(id, new PersonDTO(), HomeContentPanel.this
                                        .changeToPersonInfo());
                            }
                        }.setOutputMarkupId(true);
                    }

                }.callback(target, ((MyWebSession) this.getSession()).getPerson());
            }

            @Override
            public Panel getBookSearchPanel(final String id) {
                return new BookSearchPanel(id, HomeContentPanel.this.changeToResultsPanel());
            }

            @Override
            public void signInCallback(final AjaxRequestTarget target, final IAjaxCallback<Person> succedCallback) {
                Component signInPanel = new GenericFormPanel<UserInfo>(CONTENT) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public PanelServiceToForm<UserInfo> getFieldsPanel(final String id) {
                        return new SignInFieldsPanel(id, new UserInfo(), succedCallback);
                    }

                }.setOutputMarkupId(true);
                HomeContentPanel.this.replace(signInPanel);
                target.add(signInPanel);
            }

            @Override
            public void successfullSignInCallback(final AjaxRequestTarget target) {
                HomeContentPanel.this.changeToRakingPanel().callback(target, null);
            }

        });

        this.add(new CategoriesSidebar("sidebar", this.changeToResultsPanel()));
    }

    public IAjaxCallback<?> changeToRakingPanel() {
        return new AjaxReplacePanel<Object>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Object book) {
                // Para que no cargue de nuevo el panel cuando ya está cargado
                if (HomeContentPanel.this.contains(HomeContentPanel.this.getRankingPanel(), true)) {
                    return (Panel) HomeContentPanel.this.get(CONTENT);
                }
                HomeContentPanel.this.getRankingPanel().updateTop20(target); // Porque sino no se actualiza el top20
                HomeContentPanel.this.getRankingPanel().updateRecentlyAvailable(target);

                return HomeContentPanel.this.lazyPanel(CONTENT, HomeContentPanel.this.getRankingPanel());
            }

        };
    }

    public IAjaxCallback<Book> changeToMoreInfoPanel() {
        return new AjaxReplacePanel<Book>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Book book) {
                BookInfoPanel bookInfo = new BookInfoPanel(CONTENT, HomeContentPanel.this.getBookService()
                        .initializeBookInfo(book), HomeContentPanel.this.changeToLoaningFormPanel(),
                        HomeContentPanel.this.changeToMoreInfoPanel(), HomeContentPanel.this.changeToLoaneeInfoPanel());
                bookInfo.setOutputMarkupId(true);
                return bookInfo;
            }

        };
    }

    public IAjaxCallback<Book> changeToLoaningFormPanel() {
        return new IAjaxCallback<Book>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void callback(final AjaxRequestTarget target, final Book book) {

                HomeContentPanel.this.getBookService().initializeFields(book, "availableCopies");

                if (book.hasAvailableCopy()) {
                    LoaningFormPanel loaningFormPanel = new LoaningFormPanel(CONTENT, book,
                            HomeContentPanel.this.backToMoreInfoPanel());
                    loaningFormPanel.setOutputMarkupId(true);
                    HomeContentPanel.this.replace(loaningFormPanel);
                    target.add(loaningFormPanel);
                } else {
                    target.appendJavaScript("alert('You cannot motherfucker')");
                }
            }
        };

    }

    public IAjaxCallback<Loan> backToMoreInfoPanel() {
        return new AjaxReplacePanel<Loan>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Loan loan) {
                BookInfoPanel bookInfo = new BookInfoPanel(CONTENT, HomeContentPanel.this.getBookService()
                        .initializeBookInfo(loan.getBook()), HomeContentPanel.this.changeToLoaningFormPanel(),
                        HomeContentPanel.this.changeToMoreInfoPanel(), HomeContentPanel.this.changeToLoaneeInfoPanel());
                bookInfo.setOutputMarkupId(true);
                return bookInfo;
            }

        };
    }

    public IAjaxCallback<List<Book>> changeToResultsPanel() {
        return new AjaxReplacePanel<List<Book>>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final List<Book> list) {
                HomeContentPanel.this.getBookSearchResultPanel().replaceTable(target, list);
                return HomeContentPanel.this.lazyPanel(CONTENT, HomeContentPanel.this.getBookSearchResultPanel());
            }

        };
    }

    public IAjaxCallback<Person> changeToPersonInfo() {
        return new AjaxReplacePanel<Person>(this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Person person) {
                return new PersonInfoPanel(CONTENT, HomeContentPanel.this.getPersonService().initializePersonInfo(
                        person));
            }
        };
    }

    private AjaxReplacePanel<Person> changeToLoaneeInfoPanel() {
        return new AjaxReplacePanel<Person>(HomeContentPanel.this) {

            private static final long serialVersionUID = 1L;

            @Override
            public Panel getNewPanel(final AjaxRequestTarget target, final Person loanee) {
                return (Panel) new LoaneeInfoPanel(CONTENT, HomeContentPanel.this.getPersonService()
                        .initializeLoaneeInfo(loanee)).setOutputMarkupId(true);

            }

        };
    }

    /* Accessors */

    public void setRankingPanel(final RankingPanel rankingPanel) {
        this.rankingPanel = rankingPanel;
    }

    public RankingPanel getRankingPanel() {
        return this.rankingPanel;
    }

    public BookSearchResultPanel getBookSearchResultPanel() {
        return this.bookSearchResultPanel;
    }

    public void setBookSearchResultPanel(final BookSearchResultPanel bookSearchResultPanel) {
        this.bookSearchResultPanel = bookSearchResultPanel;
    }

    public void setBookService(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return this.bookService;
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService() {
        return this.personService;
    }

}
