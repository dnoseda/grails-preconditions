import com.google.common.base.Preconditions;
class PreconditionsBootStrap {
	def grailsApplication
	def genericCheckArgument = { conditionObject, message ->
		boolean condition = conditionObject ? true: false
		Preconditions.checkArgument(condition, message)     		
	}
	def init = { servletContext ->
		(grailsApplication.controllerClasses + grailsApplication.serviceClasses).each{ controllerClass ->
			controllerClass.metaClass.checkArgument=genericCheckArgument 
		}
	}
	def destroy = {
	}
}