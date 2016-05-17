(ns clj-blog.views
  (:require [clj-blog.posts :as posts])
  (:use [hiccup.core]))

(defn wrap-page-title
  [title]
  (str title " &middot; Kir Shatrov blog"))

(defn page-sidebar
  []
  (html
    [:input {:type "checkbox", :class "sidebar-checkbox", :id "sidebar-checkbox"}]
    [:div {:class "sidebar", :id "sidebar"}
      [:div {:class "sidebar-item"}
        [:p {} "{{ site.description }}"]]
      [:nav {:class "sidebar-nav"}
        [:a {:class "sidebar-nav-item active", :href "/"} "Home"]
        [:a {:class "sidebar-nav-item", :href "http://iempire.ru"} "About"]
        [:div {:class "sidebar-item sidebar-item-separator"}
          [:p {} "Social"]

          [:nav {:class "sidebar-nav"}
            [:a {:class "sidebar-nav-item", :href "https://twitter.com/kirshatrov"} "@kirshatrov"]
            [:a {:class "sidebar-nav-item", :href "https://github.com/kirs"} "Github"]]]
      [:div {:class "sidebar-item"}
        [:p {}
         "Theme based on "
         [:a {:href "http://lanyon.getpoole.com/", :rel "nofollow"} "Lanyon"]
         " by "
         [:a {:rel "nofollow", :href "https://twitter.com/mdo"} "@mdo"]]
        [:p {} "© Kir Shatrov. All rights reserved."]]]]))

(defn basepath
  []
  "/")

(defn page-masthead
  []
  (html
    [:div {:class "masthead"}
     [:div {:class "container"}
      [:h3 {:class "masthead-title"}
       [:a {:href (basepath), :title "Home"} "Kir Shatrov blog"]]]]))

(defn page-head
  [title]
  (html
   [:head
    [:link {:href "http://gmpg.org/xfn/11", :rel "profile"}]
    [:meta {:http-equiv "X-UA-Compatible", :content "IE=edge"}]
    [:meta {:http-equiv "content-type", :content "text/html; charset=utf-8"}]
    [:meta {:name "viewport", :content "width=device-width, initial-scale=1.0, maximum-scale=1"}]
    [:title (wrap-page-title title)]
    [:link {:rel "stylesheet", :href (str (basepath) "public/css/poole.css")}]
    [:link {:rel "stylesheet", :href (str (basepath) "public/css/syntax.css")}]
    [:link {:rel "stylesheet", :href (str (basepath) "public/css/lanyon.css")}]
    [:link {:rel "stylesheet", :href "https://fonts.googleapis.com/css?family=PT+Serif:400,400italic,700%7CPT+Sans:400"}]
    [:link {:rel "stylesheet", :href (str (basepath) "public/rrssb/rrssb.css")}]
    [:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"}]
    [:script {:src (str (basepath) "public/rrssb/rrssb.min.js")}]
    [:link {:rel "apple-touch-icon-precomposed", :sizes "144x144", :href (str (basepath) "public/apple-touch-icon-precomposed.png")}]
    [:link {:rel "shortcut icon", :href (str (basepath) "public/favicon.ico")}]
    [:link {:rel "alternate", :type "application/rss+xml", :title "RSS", :href "/atom.xml"}]]))

(defn single-post-page
  [post-data]
  (html
    (page-head (:title post-data))
    (page-sidebar)
    [:div {:class "wrap"}
     (page-masthead)
     [:div {:class "container content"}
      [:h3 {} (get post-data :title)]
      (get post-data :body)]]
    [:label {:for "sidebar-checkbox", :class "sidebar-toggle"}]))

(defn single-post-page-v2
  [post-data]
  (html
    (page-head (:title post-data))
    (page-sidebar)
    [:div {:class "wrap"}
     (page-masthead)
     [:div {:class "container content"}
      [:div {:class "post"}
       [:h1 {:class "post-title"} (:title post-data)]
       [:span {:class "post-date"} (:date post-data)]
       (:body post-data)]
      [:div {:class "follow-me"}
       "Follow me on Twitter to get more updates: "
       [:a {:href "https://twitter.com/kirshatrov"} "@kirshatrov"]]]]))

(defn get-post
  [year month day post]
  (let [post (posts/open-post (posts/get-post-file year month day post))]
    (single-post-page-v2 post)))

(defn list-posts
  [collection]
  (html
    (page-head "All posts")
    (page-sidebar)
    [:div {:class "wrap"}
     (page-masthead)
     [:div {:class "container content"}
       [:div {:class "posts"}
         (for [x collection]
           [:div {:class "post"}
            [:h1 { :class "post-title" }
              [:a { :href (:permalink x) } (:title x)]]])]]]
    [:label {:for "sidebar-checkbox", :class "sidebar-toggle"}]))
