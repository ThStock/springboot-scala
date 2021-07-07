package springscala

import org.springframework.http.{MediaType, ResponseEntity}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RequestParam, ResponseBody}
import org.springframework.web.servlet.ModelAndView
import springscala.ExampleController.Street

import java.time.ZonedDateTime
import javax.inject.Inject
import scala.beans.BeanProperty
import scala.util.{Failure, Success, Try}

@Controller
class ExampleController extends ScalaShortcuts {

  @Inject
  val trimmer: StreetTrimmerJava = null

  @RequestMapping(Array("/"))
  def home() = {
    val anyStreet = Street(" Any Street _", "32a")
    val otherStreet = Street("Other Street _", "42b")

    indexView(Seq(anyStreet, otherStreet).map(street ⇒ trimmer.trim(street)))
  }

  @RequestMapping(Array("/street/{name}"))
  def homeWithStreet(@PathVariable(value = "name") name: String): ModelAndView = {

    val anyStreet = trimmer.trim(Street(name, "32a"))

    indexView(Seq(anyStreet))
  }

  @RequestMapping(Array("/empty"))
  def homeEmpty() = {
    indexView(Nil)
  }

  @RequestMapping(value = Array("/json"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  @ResponseBody
  def json(@RequestParam(value = "fail", required = false) fail: String): ResponseEntity[Try[ZonedDateTime]] = {
    if (fail == null || fail.isBlank) {
      val value = Success(ZonedDateTime.now())
      ResponseEntity.ok(value)
    } else {
      ResponseEntity.unprocessableEntity().body(Failure(new IllegalStateException("doof")))
    }
  }

  private def indexView(streets: Seq[Street]) = view("index", Map("streets" → streets))

}

object ExampleController {

  case class Street(@BeanProperty name: String, @BeanProperty number: String)

}
