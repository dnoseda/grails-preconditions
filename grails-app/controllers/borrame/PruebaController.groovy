package borrame

class PruebaController {

    def index = {
    	checkArgument(params.id, "Params no existe")
    }
}
