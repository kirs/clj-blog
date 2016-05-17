(ns clj-blog.web
  (:require
            [clj-blog.views :as views]
            [clj-blog.posts :as posts]
            [clj-time.core :as time]
            [clj-time.format :as time-format]
            [clj-time.local :as l]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.middleware.resource :as rmr]
            [ring.util.response :refer [redirect response]])
  (:gen-class))

(defn list-handler
  [request]
  ; (str (:params request)))
  ; (if-let [draft (contains? (:params request) :draft)]
  ;   (views/list-posts (posts/all))
    (views/list-posts (posts/all-published)))

(defroutes app-routes
  (GET "/" [] list-handler)
  (GET "/:year/:month/:day/:post" [year month day post] (views/get-post year month day post)))

(def app
  (-> app-routes
    (rmr/wrap-resource "public")))

(defn -main
  [& args]
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (ring/run-jetty app {:port port})))
