package controllers

import javax.inject.Inject
import play.api.db.Database
import play.api.mvc.{BaseController, ControllerComponents}

/**
 * 获取MYSQL数据库链接
 *
 * @author 梦境迷离
 * @version 1.0, 2019-04-1
 * @param db
 * @param controllerComponents
 */
class ScalaControllerInject @Inject()(db: Database, val controllerComponents: ControllerComponents) extends BaseController {


    /**
     * 使用非default数据库@NamedDatabase("orders") db: Database
     */
    def testDb = Action {
        var outString = "Number is "
        val conn = db.getConnection()

        try {
            val stmt = conn.createStatement
            val rs = stmt.executeQuery("SELECT 9 as testkey ")

            while (rs.next()) {
                outString += rs.getString("testkey")
            }
        } finally {
            conn.close()
        }
        Ok(outString)
    }

    // access "default" database
    db.withConnection { conn =>
        conn.close()
    }

}