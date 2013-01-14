(let
  [gatling-version "{{gatling-version}}"]
  (defproject {{name}} "0.0.1-SNAPSHOT"

    :description "Gatling load-test suite"

    :min-lein-version "{{lein-version}}"

    :repositories [
                   ["excilys.com" "http://repository.excilys.com/content/groups/public"]
                  ]

    :dependencies [
                   [com.excilys.ebi.gatling/gatling-app ~gatling-version]
                   [com.excilys.ebi.gatling.highcharts/gatling-charts-highcharts ~gatling-version]
                   [org.clojure/clojure "{{clojure-version}}"]
                  ]

    :plugins [
              [lein-scalac "{{lein-scalac-version}}"]
             ]

    :scala-source-path "simulations"

    :resource-paths ["request-bodies" "data" "conf"]

    :prep-tasks ["scalac"]

    :main com.excilys.ebi.gatling.app.Gatling))
