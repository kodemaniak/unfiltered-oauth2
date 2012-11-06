import sbt._
import Keys._

object UnfilteredOAuth2Build extends Build {
  val repo = Some(Resolver.file("repository", new File("/var/www/repository")))

  def defaultSettings: Seq[Setting[_]] = Seq(
		organization := "net.databinder",
		version := "0.6.4-SNAPSHOT",
		scalaVersion := "2.9.2",
		publishTo := repo
	)


  lazy val root = Project(id = "unfiltered-oauth2-root", base = file("."))
  	.settings(defaultSettings :_*)
  	.aggregate(mac, oauth2)

  lazy val mac = Project(id = "unfiltered-mac", base = file("mac"))
  	.settings(defaultSettings ++ Seq(
    		libraryDependencies ++= Seq(
    			"net.databinder" %% "unfiltered" % "0.6.4",
    			"net.databinder" %% "unfiltered-spec" % "0.6.4" % "test",
    			"org.scala-tools.testing" % "specs_2.9.1" % "1.6.9" % "test"
    		)) :_*
  	)

  lazy val oauth2 = Project(id = "unfiltered-oauth2", base = file("oauth2"))
  	.dependsOn(mac)
  	.settings(defaultSettings ++ Seq(
		libraryDependencies ++= Seq(
			"net.databinder" %% "unfiltered-filter" % "0.6.4",
			"net.databinder" %% "unfiltered-jetty" % "0.6.4",
			"net.databinder" %% "unfiltered-spec" % "0.6.4" % "test",
			"org.scala-tools.testing" % "specs_2.9.1" % "1.6.9" % "test"
		)) :_*
	)
}
