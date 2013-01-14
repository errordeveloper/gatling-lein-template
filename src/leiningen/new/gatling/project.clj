(let
  ;; You can upgrade to newer version of Gatling by editing this string
  [gatling-version "{{gatling-version}}"]
  (defproject {{name}} "0.0.1-SNAPSHOT"

    :description "Gatling load-test suite"

    :min-lein-version "{{lein-version}}"

    ;; Override location of the local maven repository. Relative to project root.
    ;:local-repo "/usr/local/lib/m2"

    :repositories [
                   ["excilys.com" "http://repository.excilys.com/content/groups/public"]
                  ]

    :dependencies [
                   [com.excilys.ebi.gatling/gatling-app ~gatling-version]
                   [com.excilys.ebi.gatling.highcharts/gatling-charts-highcharts ~gatling-version]
                   ;; We need to bundle Clojure itself just for Leiningen to work, but it doesn't matter
                   [org.clojure/clojure "{{clojure-version}}"]
                  ]

    :plugins [
              [lein-scalac "{{lein-scalac-version}}"]
             ]

    :scala-source-path "simulations"

    :resource-paths ["request-bodies" "data" "conf"]

    :prep-tasks ["scalac"]

    :jvm-opts [
               "-server"
               "-XX:+UseThreadPriorities"
               "-XX:ThreadPriorityPolicy=42"
               "-Xms512M"
               "-Xmx512M"
               "-Xmn100M"
               "-Xss2M"
               "-XX:+HeapDumpOnOutOfMemoryError"
               "-XX:+AggressiveOpts"
               "-XX:+OptimizeStringConcat"
               "-XX:+UseFastAccessorMethods"
               "-XX:+UseParNewGC"
               "-XX:+UseConcMarkSweepGC"
               "-XX:+CMSParallelRemarkEnabled"
               "-XX:+CMSClassUnloadingEnabled"
               "-XX:SurvivorRatio=8"
               "-XX:MaxTenuringThreshold=1"
               "-XX:CMSInitiatingOccupancyFraction=75"
               "-XX:+UseCMSInitiatingOccupancyOnly"
              ]

    :main com.excilys.ebi.gatling.app.Gatling))
