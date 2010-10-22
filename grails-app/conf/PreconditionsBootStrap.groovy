class PreconditionsBootStrap {
	def grailsApplication
	def genericCheckArgument = { conditionObject, message ->
		boolean condition = conditionObject ? true: false
		if(!condition){
			throw new IllegalArgumentException( messag)
		}     		
	}
	def init = { servletContext ->
		grailsApplication.controllerClasses.each{ controllerClass ->
			controllerClass.metaClass.checkArgument=genericCheckArgument 
		} 
		grailsApplication.serviceClasses.each{ controllerClass ->
			controllerClass.metaClass.checkArgument=genericCheckArgument 
		}
	}
	def destroy = {
	}
}