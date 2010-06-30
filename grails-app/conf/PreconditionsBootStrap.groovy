import com.google.common.base.Preconditions;
class BootStrap {
 def grailsApplication

  def init = { servletContext ->
     grailsApplication.controllerClasses.each{ controllerClass ->
     	controllerClass.metaClass.checkArgument= { conditionObject, message ->
     		boolean condition = conditionObject ? true: false
     		Preconditions.checkArgument(condition, message)     		
         }
     }
  }
  def destroy = {
  }
}