package springscala

import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.templateresolver.FileTemplateResolver

import java.io.File
import javax.annotation.PostConstruct
import javax.inject.Inject

@EnableAutoConfiguration
@ComponentScan
@Configuration
class SpringStart {

  @Bean
  def module: DefaultScalaModule = {
    val module = new DefaultScalaModule
    module
  }
}

@Configuration
class ThmeleafExtension {

  @Inject
  val templateEngine: SpringTemplateEngine = null

  @PostConstruct
  def addFileResolver() {
    val resolver = new FileTemplateResolver()
    val templateFolder = new File(".").toPath
      .resolveSibling("src/main/resources/templates/").toFile.getAbsoluteFile
    if (templateFolder.isDirectory) {
      resolver.setPrefix(templateFolder.getAbsolutePath + "/")
      resolver.setSuffix(".html")
      resolver.setTemplateMode("HTML5")
      val end = templateEngine.getTemplateResolvers.size
      resolver.setOrder(end)
      resolver.setCacheable(false)
      templateEngine.addTemplateResolver(resolver)
    }
  }
}

object SpringStart extends App {
  // $ mvn spring-boot:run -Dserver.port=8090 # http://localhost:8090/
  val app = new SpringApplication(classOf[SpringStart])
  val ctx = app.run()
  // ctx.getBeanDefinitionNames.sorted.foreach(println)
}
