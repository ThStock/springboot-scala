package springscala

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

@EnableAutoConfiguration
@ComponentScan
@Configuration
class SpringStart {

}
object SpringStart extends App {
  // $ mvn spring-boot:run -Dserver.port=8090 # http://localhost:8090/
  val ctx = SpringApplication.run(classOf[SpringStart])
  // ctx.getBeanDefinitionNames.sorted.foreach(println)
}
