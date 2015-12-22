package com.bleib.finatra_playing

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.finatra.response.Mustache

class PlayingController @Inject()(nbaService: NBAService, mapper: FinatraObjectMapper) extends Controller {

  @Mustache("home")
  case class HomeView(name: String, points: String)

  get("/home") { request: Request =>
    nbaService.get().map { points =>
      info(s"Points are $points")
      HomeView("Steph Curry", points.toString)
    }
  }
}
