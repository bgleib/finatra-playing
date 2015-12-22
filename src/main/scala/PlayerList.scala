package com.bleib.finatra_playing

case class Parameters(LeagueID: String, Season: String, IsOnlyCurrentSeason: String)

case class ResultSet(name: String, headers: List[String], rowSet: List[List[String]])

case class PlayerList(resource: String, parameters: Parameters, resultSets: List[ResultSet])

object PlayerInfo {
  def fromRow(row: List[String]): PlayerInfo = {
    row match {
      case List(id, displayName, rosterStatus, startYear, endYear, playerCode, teamId, teamCity, teamName, teamAbbr, teamCode, gamesPlayed) =>
        PlayerInfo(
          id.toLong,
          displayName,
          rosterStatus.toInt,
          startYear.toInt,
          endYear.toInt,
          playerCode,
          teamId.toLong,
          teamCity,
          teamName,
          teamAbbr,
          teamCode,
          gamesPlayed
        )
      case _ => throw new IllegalArgumentException()
    }
  }
}

case class PlayerInfo(
  id: Long,
  displayName: String,
  rosterStatus: Int,
  startYear: Int,
  endYear: Int,
  playerCode: String,
  teamId: Long,
  teamCity: String,
  teamName: String,
  teamAbbr: String,
  teamCode: String,
  gamesPlayed: String
)