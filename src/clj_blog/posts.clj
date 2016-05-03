(ns clj-blog.posts
  (:require [markdown.core :as md]
            [clj-blog.frontmatter :as fm]))

(def directory (clojure.java.io/file "posts/"))
(def files (file-seq directory))
(def posts-files (filter #(.isFile %) files))

(defn parse-post
  [content]
  (fm/parse content))
  ; (md/md-to-html-string content))

(defn open-post
  [path]
  (parse-post (slurp path)))

(defn all
  []
  (map open-post posts-files))
