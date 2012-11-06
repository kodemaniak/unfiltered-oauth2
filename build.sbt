organization := "net.databinder"

version := "0.6.4"

scalaVersion := "2.9.2"

// Publishing to private repository server for now.
publishTo := Some(Resolver.file("repository", new File("/var/www/repository")))

