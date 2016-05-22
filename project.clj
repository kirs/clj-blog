(defproject clj-blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljfmt "0.5.3"]]
  :main ^:skip-aot clj-blog.web
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.4.0"]
                 [markdown-clj "0.9.89"]
                 [compojure "1.3.4"]
                 [clj-time "0.11.0"]
                 [hiccup "1.0.5"]
                 [circleci/clj-yaml "0.5.3"]
                 [hickory "0.6.0"]
                 [ring/ring-jetty-adapter "1.4.0"]])
