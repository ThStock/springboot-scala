import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import scala.collection.JavaConversions._

import scala.beans.BeanProperty

@Controller
@EnableAutoConfiguration
class ExampleController {

  @RequestMapping(Array("/"))
  def home(): ModelAndView = {

    case class Street(@BeanProperty name: String, @BeanProperty number: String)
    val anyStreet = Street("Any Street", "32a")

    view("index", Map("street" -> anyStreet))
  }

  private def view(name: String, model: java.util.Map[String, AnyRef]): ModelAndView = {
    new ModelAndView(name, model)
  }
}

@Configuration
@ComponentScan
object SpringStart extends App {
  // $ mvn spring-boot:run -Dserver.port=8090 # http://localhost:8090/
  SpringApplication.run(classOf[ExampleController])
}
