package springscala

import org.springframework.web.servlet.ModelAndView

import scala.collection.JavaConverters._

trait ScalaShortcuts {

  def view(name: String, model: Map[String, Any]): ModelAndView = {

    def convertToJavaValues(entry: (String, Any)): (String, Any) = {
      entry._2 match {
        case in: Seq[Any] => (entry._1, in.asJava)
        case _ => entry
      }
    }

    new ModelAndView(name, model.map(convertToJavaValues).asJava)
  }

}
