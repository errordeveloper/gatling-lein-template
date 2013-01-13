(ns leiningen.new.gatling
  (:require [lancet.core :as lancet])
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "gatling"))

(defn gatling
  "Generate a new Gatling load-testing (sub-)project"
  [name]
  (let [data {:name name
              :clojure-version "1.4.0"
              :lein-version "2.0.0"
              :lein-scalac-version "0.1.0"
              :gatling-version "1.4.1"
              :sanitized (name-to-path name)}]
    (->files data
             ["conf/gatling.conf" (render "gatling.conf" data)]
             ["conf/logback.xml" (render "logback.xml" data)]
             [".gitignore" (render "gitignore" data)]
             ["project.clj" (render "project.clj" data)]))
  (println "Generated new Gatling load-test boilerplate in" name "directory.")
  (doseq [subdir [ "simulations" "request-bodies" "data" ]]
    (lancet/mkdir {:dir (format "%s/%s" name subdir)})))
