class PreconditionsGrailsPlugin {
	// the plugin version
	def version = "0.6"
	// the version or versions of Grails the plugin is designed for
	def grailsVersion = "1.3.2 > *"
	// the other plugins this plugin depends on
	def dependsOn = [:]
	// resources that are excluded from plugin packaging
	def pluginExcludes = [
		"grails-app/views/error.gsp"
	]

	// TODO Fill in these fields
	def author = "Damian Noseda"
	def authorEmail = "dnoseda@gmail.com"
	def title = "Google Preconditions in Grails"
	def description = '''\\
	Inject method checkArgument of Google Preconditions in controllers and services
	'''

	// URL to the plugin's documentation
	def documentation = "http://grails.org/plugin/preconditions"

	def doWithWebDescriptor = { xml ->
		// TODO Implement additions to web.xml (optional), this event occurs before 
	}

	def doWithSpring = {
		// TODO Implement runtime spring config (optional)
	}

	def doWithDynamicMethods = { ctx ->
		grailsApplication.controllerClasses.each{ controllerClass ->
			controllerClass.metaClass.checkArgument=genericCheckArgument 
		} 
		grailsApplication.serviceClasses.each{ serviceClass ->
			serviceClass.metaClass.checkArgument=genericCheckArgument 
		}
	}

	def doWithApplicationContext = { applicationContext ->
		// TODO Implement post initialization spring config (optional)
	}

	def onChange = { event ->
		grailsApplication.controllerClasses.each{ controllerClass ->
			controllerClass.metaClass.checkArgument=genericCheckArgument 
		} 
		grailsApplication.serviceClasses.each{ serviceClass ->
			serviceClass.metaClass.checkArgument=genericCheckArgument 
		}
	}

	def onConfigChange = { event ->
		// TODO Implement code that is executed when the project configuration changes.
		// The event is the same as for 'onChange'.
	}
	def genericCheckArgument = { conditionObject, message ->
		boolean condition = conditionObject ? true: false
		if(!condition){
			throw new IllegalArgumentException( message)
		}     		
	}
}
