import sbt._
import Keys._

object UnfilteredOAuth2Build extends Build {
  lazy val root = Project(id = "unfiltered-oauth2-root", base = file(".")) aggregate(mac, oauth2)

  lazy val mac = Project(id = "unfiltered-mac", base = file("mac")).settings(
    libraryDependencies ++= Seq(
    	"net.databinder" %% "unfiltered" % "0.6.4",
    	"net.databinder" %% "unfiltered-spec" % "0.6.4",
    	"org.scala-tools.testing" % "specs_2.9.1" % "1.6.9" % "test"
    )
  )

  lazy val oauth2 = Project(id = "unfiltered-oauth2", base = file("oauth2"))
  	.dependsOn(mac)
  	.settings(
		libraryDependencies ++= Seq(
			"net.databinder" %% "unfiltered-filter" % "0.6.4",
			"net.databinder" %% "unfiltered-jetty" % "0.6.4",
			"net.databinder" %% "unfiltered-spec" % "0.6.4",
			"org.scala-tools.testing" % "specs_2.9.1" % "1.6.9" % "test"
		)
	)
}
