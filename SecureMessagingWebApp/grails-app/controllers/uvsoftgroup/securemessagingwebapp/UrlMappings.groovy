package securemessagingwebapp

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" (controller:'userRegistration',action:'topPage',view:'topPage' )
        // "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
