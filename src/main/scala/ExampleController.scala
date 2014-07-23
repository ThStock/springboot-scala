import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

object SpringStart extends App {
  // $ mvn spring-boot:run # http://localhost:8080/
  SpringApplication.run(classOf[ExampleController])
}

@RestController
@EnableAutoConfiguration
class ExampleController {

  @RequestMapping(Array("/"))
  def home() = "Hello World with scala 2.11!";
}