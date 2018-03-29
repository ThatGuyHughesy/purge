(defproject purge "0.1.0-SNAPSHOT"
  :description "Deletes all GitLab forks for a given user."
  :url "https://github.com/ThatGuyHughesy/purge"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :author "Conor Hughes <hello@conorhughes.me>"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.7.1"]
                 [clj-http "3.4.1"]
                 [slingshot "0.12.2"]]
  :dev-dependencies [[lein-clojars "0.9.1"]]
  :plugins [[lein-exec "0.3.7"]])