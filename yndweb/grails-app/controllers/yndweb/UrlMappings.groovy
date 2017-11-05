package yndweb

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')

        "/todos"(resources:"todo")
        "/pendingTodos"(controller: 'todo', action: 'pending')
        "/"(controller: 'user',action: 'home')
        "/login"(view:"/signin")
        "/comingsoon"(view:"/comingsoon")
        "/home"(controller: 'user',action: 'home')
        "/register"(view:"/signup")
        "/verifyUser"(view:"/verifyUser")
        "/error"(view:"/error")
    }
}
