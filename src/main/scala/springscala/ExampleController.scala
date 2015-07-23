package springscala

import javax.inject.Inject

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping}
import springscala.ExampleController.Street

import scala.beans.BeanProperty

@Controller
class ExampleController extends ScalaShortcuts {

  @Inject
  var trimmer: StreetTrimmerJava = null

  @RequestMapping(Array("/"))
  def home() = {

    val anyStreet = Street(" Any Street _", "32a")
    val otherStreet = Street("Other Street _", "42b")

    indexView(Seq(anyStreet, otherStreet).map(street => trimmer.trim(street)))
  }

  @RequestMapping(Array("/street/{name}"))
  def homeWithStreet(@PathVariable(value = "name") name: String) = {

    val anyStreet = trimmer.trim(Street(name, "32a"))

    indexView(Seq(anyStreet))
  }

  @RequestMapping(Array("/empty"))
  def homeEmpty() = {
    indexView(Nil)
  }

  private def indexView(streets: Seq[Street]) = view("index", Map("streets" -> streets))

}

object ExampleController {

  case class Street(@BeanProperty name: String, @BeanProperty number: String)

}
