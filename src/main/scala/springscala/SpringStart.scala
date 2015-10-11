package springscala

import java.io.File
import javax.annotation.PostConstruct
import javax.inject.Inject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.templateresolver.FileTemplateResolver

@EnableAutoConfiguration
@ComponentScan
@Configuration
class SpringStart {

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
  app.setShowBanner(false)
  val ctx = app.run()
  // ctx.getBeanDefinitionNames.sorted.foreach(println)
}
