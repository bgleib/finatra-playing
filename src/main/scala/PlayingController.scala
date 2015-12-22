package com.bleib.finatra_playing

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.finatra.response.Mustache
import com.twitter.util.Future

class PlayingController @Inject()(nbaService: NBAService, mapper: FinatraObjectMapper) extends Controller {

  @Mustache("player_info")
  case class HomeView(name: String, startYear: String)

  get("/player_info/:*") { request: Request =>
    request.params("*").split("_") match {
      case Array(firstName, lastName) =>
        nbaService.getPlayerInfo(firstName, lastName).map { playerInfoOpt =>
          playerInfoOpt.map { playerInfo =>
            HomeView(s"${playerInfo.firstName} ${playerInfo.lastName} (${playerInfo.id})", playerInfo.startYear.toString)
          }.getOrElse(response.badRequest(s"Couldn't find ${request.params("*").replace("_", " ")}"))
        }
      case _ => Future.value(response.badRequest("Please specify firstName_lastName"))
    }
  }
}
