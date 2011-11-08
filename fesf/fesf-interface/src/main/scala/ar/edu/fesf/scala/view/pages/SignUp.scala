package ar.edu.fesf.scala.view.pages

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.request.component.IRequestablePage
import org.apache.wicket.behavior.Behavior
import ar.edu.fesf.view.pages.generic.GenericFormPanel
import ar.edu.fesf.services.dtos.PersonDTO
import ar.edu.fesf.controllers.PanelServiceToForm
import ar.edu.fesf.view.pages.persons.PersonFormFieldsPanel
import ar.edu.fesf.security.SecurityContextHelper
import org.apache.wicket.spring.injection.annot.SpringBean
import scala.reflect.BeanProperty

class SignUp extends WebPage {

  @SpringBean
  @BeanProperty
  var securityContextHelper: SecurityContextHelper = _

  this.initialize()

  private def initialize() = {
    this.add(new GenericFormPanel[PersonDTO]("form") {
      override def getFieldsPanel(id: String): PanelServiceToForm[PersonDTO] = {
        new PersonFormFieldsPanel(id,
          new PersonDTO());
      }
    })
  }

  private def personDTO(): PersonDTO = {
    if (this.securityContextHelper.isAuthenticatedUser()) {
      val authenticatedUser = this.securityContextHelper.getAuthenticatedUser()
      return new PersonDTO(authenticatedUser.getFirstName(), authenticatedUser.getLastName(), authenticatedUser.getEmail())
    } else {
      return new PersonDTO()
    }
  }

}