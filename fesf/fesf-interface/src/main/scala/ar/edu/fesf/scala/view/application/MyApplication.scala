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
import org.apache.wicket.Page
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils
import ar.edu.fesf.scala.view.pages.SignIn
import ar.edu.fesf.scala.view.pages.UpsPage
import org.apache.wicket.settings.IExceptionSettings
import org.springframework.security.core.context.SecurityContextHolder
import ar.edu.fesf.security.FakeAuthentication
import ar.edu.fesf.scala.view.pages.SignInLdap


@SerialVersionUID(1L)
class MyApplication(authenticationMode: AuthenticationMode) extends AuthenticatedWebApplication {

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

    this.getApplicationSettings().setInternalErrorPage(classOf[UpsPage])
    this.getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE)

    this.mountUrl("home", classOf[ScalaHome])
    this.mountUrl("login", this.loginPage())
  }
  
  private def mountUrl(mountPath: String, pageClass: Class[_ <: WebPage]) {
    this.mounterURL.mount(mountPath, pageClass, "");
  }

  override def getWebSessionClass(): Class[_ <: AuthenticatedWebSession] = this.securitySession()

  override def getSignInPageClass(): Class[_ <: WebPage] = this.loginPage()

  override def getHomePage(): Class[_ <: Page] = classOf[ScalaHome]

  def getSpringContext(): WebApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext())

  def getContextPath(): String = this.getServletContext().getContextPath()
  
  def loginPage(): Class[_ <: WebPage] = this.authenticationMode.loginPage()
  
  def securitySession(): Class[_ <: AuthenticatedWebSession] = this.authenticationMode.securitySession()



}