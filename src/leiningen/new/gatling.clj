(ns leiningen.new.gatling
  (:require [clojure.java.io :as io])
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "gatling"))

(defn gatling
  "Generate a new Gatling load-testing (sub-)project"
  [name]
  (let [data {:name name
              :clojure-version          "1.4.0"
              :lein-version             "2.0.0"
              :lein-scalac-version      "0.1.0"
              :gatling-version          "1.4.1"
              :sanitized (name-to-path name)}
        dirs ["simulations"
              "request-bodies"
              "data"]]
    (->files data
             ["conf/gatling.conf"   (render "gatling.conf" data)]
             ["conf/logback.xml"    (render "logback.xml" data)]
             [".gitignore"          (render "gitignore" data)]
             ["project.clj"         (render "project.clj" data)])
  (doseq [subdir dirs]
    (.mkdirs (io/file name subdir))))
  (println "Generated new Gatling load-test boilerplate in" name "directory."))
