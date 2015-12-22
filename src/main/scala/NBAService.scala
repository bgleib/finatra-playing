package com.bleib.finatra_playing

import com.google.inject.Inject
import com.twitter.finatra.httpclient.{RequestBuilder, HttpClient}
import com.twitter.util.Future

class NBAService @Inject()(httpClient: HttpClient) {
  lazy val playerList: Future[Seq[PlayerInfo]] = {
    val season = "2015-16"
    val request = RequestBuilder.get(s"/stats/commonallplayers?LeagueId=00&IsOnlyCurrentSeason=1&Season=$season")
    request.toString()
    request.host = "stats.nba.com"
    httpClient.executeJson[PlayerList](request).map(_.resultSets.flatMap(_.rowSet.map(PlayerInfo.fromRow)))
  }

  def getPlayerInfo(firstName: String, lastName: String): Future[Option[PlayerInfo]] = {
    val displayName = s"$lastName, $firstName".toLowerCase
    playerList.map(_.find(_.displayName.toLowerCase == displayName))
  }
}
