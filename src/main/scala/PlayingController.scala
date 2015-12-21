import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.response.Mustache

class PlayingController extends Controller {
  get("/hello") { request: Request =>
    info("Hi was called")
    "Hello Monica!"
  }

  @Mustache("home")
  case class HomeView(message: String)

  get("/home") { request: Request =>
    HomeView("Blah blah")
  }

  get("/pic") { request: Request =>
    response.ok.file("/bleib.jpg")
  }
}
