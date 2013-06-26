/**
Open Bank Project - Transparency / Social Finance Web Application
Copyright (C) 2011, 2012, TESOBE / Music Pictures Ltd

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

Email: contact@tesobe.com
TESOBE / Music Pictures Ltd
Osloerstrasse 16/17
Berlin 13359, Germany

  This product includes software developed at
  TESOBE (http://www.tesobe.com/)
  by
  Simon Redfern : simon AT tesobe DOT com
  Stefan Bethge : stefan AT tesobe DOT com
  Everett Sochowski : everett AT tesobe DOT com
  Ayoub Benali: ayoub AT tesobe DOT com

 */
package com.tesobe.model

import net.liftweb._
import mongodb._
import util.Props
import com.mongodb.{Mongo, ServerAddress}

object AdminDb extends MongoIdentifier {
  val jndiName = "admin"
}

object MongoConfig {
  def init: Unit = {
    val srvr = new ServerAddress(
       Props.get("mongo.host", "localhost"),
       Props.getInt("mongo.port", 27017)
    )
    val defaultDatabase =
      Props.mode match {
        case Props.RunModes.Test  => "test"
        case _ => "OBP006"
      }

    MongoDB.defineDb(DefaultMongoIdentifier, new Mongo(srvr), Props.get("mongo.dbName", defaultDatabase))
    MongoDB.defineDb(AdminDb, new Mongo(srvr), "admin")
  }
}
