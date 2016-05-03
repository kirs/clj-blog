(ns clj-blog.views
  (:require [clj-blog.posts :as posts])
  (:use [hiccup.core]))

(defn gen-page-title
  []
  "title from clojure")

(defn page-sidebar
  []
  "")

(defn page-head
  []
  (html
   [:head
    [:link {:href "http://gmpg.org/xfn/11", :rel "profile"}]
    [:meta {:http-equiv "X-UA-Compatible", :content "IE=edge"}]
    [:meta {:http-equiv "content-type", :content "text/html; charset=utf-8"}]
    [:meta {:name "viewport", :content "width=device-width, initial-scale=1.0, maximum-scale=1"}]
    [:title (gen-page-title)]
    [:link {:rel "stylesheet", :href "{{ site.baseurl }}/public/css/poole.css"}]
    [:link {:rel "stylesheet", :href "{{ site.baseurl }}/public/css/syntax.css"}]
    [:link {:rel "stylesheet", :href "{{ site.baseurl }}/public/css/lanyon.css"}]
    [:link {:rel "stylesheet", :href "https://fonts.googleapis.com/css?family=PT+Serif:400,400italic,700%7CPT+Sans:400"}]
    [:link {:rel "stylesheet", :href "{{ site.baseurl }}/public/rrssb/rrssb.css"}]
    [:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"}]
    [:script {:src "{{ site.baseurl }}/public/rrssb/rrssb.min.js"}]
    [:link {:rel "apple-touch-icon-precomposed", :sizes "144x144", :href "{{ site.baseurl }}/public/apple-touch-icon-precomposed.png"}]
    [:link {:rel "shortcut icon", :href "{{ site.baseurl }}/public/favicon.ico"}]
    [:link {:rel "alternate", :type "application/rss+xml", :title "RSS", :href "/atom.xml"}]]))

(defn list-posts
  []
  (html
    (page-head)
    (page-sidebar)
    [:div {:class "wrap"}
     [:div {:class "masthead"}
      [:div {:class "container"}
       [:h3 {:class "masthead-title"}
        [:a {:href "{{ site.baseurl }}/", :title "Home"} "{{ site.title }}"]
        [:small {} "{{ site.tagline }}"] ] ] ]
     [:div {:class "container content"}
      (posts/all)]]
    [:label {:for "sidebar-checkbox", :class "sidebar-toggle"}]))
