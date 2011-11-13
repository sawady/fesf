package ar.edu.fesf.scala.view.application
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
import scala.reflect.BeanProperty
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.fesf.services.SpringInitializedService
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.apache.wicket.markup.html.WebPage
import ar.edu.fesf.wicket.application.MounterURL
import ar.edu.fesf.scala.view.pages.ScalaHome
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import ar.edu.fesf.view.pages.signin.SignIn
import org.apache.wicket.Page
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

@SerialVersionUID(1L)
class MyApplication extends AuthenticatedWebApplication {

  @SpringBean
  @BeanProperty
  var springInitializedService: SpringInitializedService = _

  val mounterURL: MounterURL = new MounterURL(this)

  override def init() = {
    super.init()
    this.getMarkupSettings().setCompressWhitespace(true)
    this.getMarkupSettings().setStripWicketTags(true)

    this.getComponentInstantiationListeners().add(new SpringComponentInjector(this, this.getSpringContext(), true))

    this.getSpringInitializedService().initialize()

    this.mountUrl("home", classOf[ScalaHome])
    this.mountUrl("login", classOf[SignIn])
//    this.mountUrl("librarian", classOf[LibrarianHome])

  }

  private def mountUrl(mountPath: String, pageClass: Class[_ <: WebPage]) {
    this.mounterURL.mount(mountPath, pageClass, "");
  }

  override def getWebSessionClass(): Class[_ <: AuthenticatedWebSession] = classOf[SecuritySession]

  override def getSignInPageClass(): Class[_ <: WebPage] = classOf[SignIn]

  override def getHomePage(): Class[_ <: Page] = classOf[ScalaHome]

  def getSpringContext(): WebApplicationContext =
    WebApplicationContextUtils.getWebApplicationContext(this.getServletContext())

  def getContextPath(): String = this.getServletContext().getContextPath()

}