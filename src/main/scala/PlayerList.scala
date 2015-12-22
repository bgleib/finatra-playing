package com.bleib.finatra_playing

case class Parameters(LeagueId: String, Season: String, IsOnlyCurrentSeason: String)

case class ResultSet(name: String, headers: List[String], rowSet: List[List[String]])

case class PlayerList(resource: String, parameters: Parameters, resultSets: List[ResultSet])