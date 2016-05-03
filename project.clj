(defproject clj-blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljfmt "0.5.3"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.4.0"]
                 [markdown-clj "0.9.89"]
                 [hiccup "1.0.5"]
                 [circleci/clj-yaml "0.5.3"]
                 [hickory "0.6.0"]
                 [ring/ring-jetty-adapter "1.4.0"]])
